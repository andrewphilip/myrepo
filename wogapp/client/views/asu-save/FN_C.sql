create or replace function FN_C(p_asucompls t_asu_comp_obj) return T_ASU PIPELINED
as
type asu_cur is ref cursor;
v_cur1 asu_cur;
v_dynaQry varchar2(14000);
v_stateCode state.statecode%TYPE;
v_emp number;
v_unemp number;
v_urate number(4,1);
v_lf number;
v_curpop number;
v_decenpop number;
v_refmonth number;
v_refyear number(4);
v_asucode asu.asucode%TYPE;
v_progyear asu.progyear%TYPE;
v_obj O_ASU;
pragma autonomous_transaction;

begin

  v_obj := O_ASU(null, null, null, null, null, null,null,null,null,null);
  
	v_dynaQry := 'select distinct  t1.refmonth,t1.refyear,t2.statecode, t2.asucode,t2.progyear,
          sum(decode(t2.operation, ''+'',  t1.asu_emp,-1*t1.asu_emp )) over(partition by t1.statecode,t1.progyear,t1.refyear,t1.refmonth) emp, 
          sum(decode(t2.operation, ''+'', t1.asu_unemp, -1*t1.asu_unemp)) over(partition by t1.statecode,t1.progyear,t1.refyear,t1.refmonth) unemp, 
          sum(decode(t2.operation, ''+'', t1.asu_lf, -1*t1.asu_lf)) over(partition by t1.statecode,t1.progyear,t1.refyear,t1.refmonth) lf, 
          round(avg(decode(t2.operation, ''+'', t1.asu_urate, -1*t1.asu_urate)) over(partition by t1.statecode,t1.progyear,t1.refyear,t1.refmonth),1) urate,
          sum(decode(t2.operation, ''+'',  t3.decen_pop,-1*t3.decen_pop )) over(partition by t1.statecode,t1.progyear,t1.refyear,t1.refmonth) decen_pop, 
          sum(decode(t2.operation, ''+'',  t3.cur_pop,-1*t3.cur_pop )) over(partition by t1.statecode,t1.progyear,t1.refyear,t1.refmonth) cur_pop
          from asu_comp_estimate t1 , (select * from table(cast(:asucomps as t_asu_comp_obj))) t2 ,
          (select compcode,statecode,progyear,decen_pop,cur_pop from asu_comp_def) t3 ';
		      
	v_dynaQry := 	v_dynaQry || ' where t1.compcode = trim(t2.compcode)
                                and t1.compcode = t3.compcode
                                and t1.statecode = t2.statecode
                                and t1.progyear = t3.progyear
              		  	            and t1.progyear=t2.progyear
                              	--and t1.refmonth=99
                              	 order by to_number(t1.refmonth)
                              	';

  dbms_output.put_line(v_dynaQry);
	open v_cur1 for v_dynaQry using p_asucompls;
	loop
		fetch v_cur1 into v_refmonth,v_refyear,v_stateCode,v_asucode,v_progyear,v_emp,v_unemp,v_lf,v_urate,v_decenpop,v_curpop;
		exit when v_cur1%NOTFOUND;
	     v_obj.asu_code := v_asucode;
	     v_obj.refmonth := v_refmonth;
	     v_obj.refyear := v_refyear;
	     v_obj.progyear :=  v_progyear;
	     v_obj.asu_emp := v_emp;
	     v_obj.asu_unemp := v_unemp;
	     v_obj.asu_urate := v_urate;
	     v_obj.asu_lf := v_lf;
	     v_obj.asu_curpop :=v_curpop;
	     v_obj.asu_decenpop := v_decenpop;
     
    -- dbms_output.put_line('ASU_EMP:'||v_obj.asu_emp);
    -- dbms_output.put_line('ASU_UNEMP:'||v_obj.asu_unemp);
    -- dbms_output.put_line('ASU_URATE:'||v_obj.asu_urate);
	    PIPE ROW(v_obj);	
				
	end loop;
	close v_cur1;
  begin    
      update asustatus set stepstatus='Y' where statecode=v_stateCode and asucode=v_asucode and progyear=v_progyear and prodstep=1;
      if sql%rowcount = 0 then
        insert into asustatus (statecode,asucode,progyear,prodstep,stepstatus) values (v_stateCode,v_asucode,v_progyear,1,'Y');
      end if;
      commit;
      Exception
        when others then
        raise_application_error(-20301, 'In  F_ASU_CALCULATE: SQLCODE:'||SQLCODE||' ERROR MSG:'||sqlerrm);
   end;

	return;
	
end FN_C;


==================================================

create or replace TYPE O_ASU as object(
	asu_code varchar2(8),
	refmonth number,
	refyear number(4),
	progyear number(4),
	asu_emp number,
	asu_unemp number,
	asu_lf number,
	asu_urate number(4,1),
	asu_curpop number,
	asu_decenpop number
);

create or replace TYPE T_ASU as table of O_ASU;


==================================================================
