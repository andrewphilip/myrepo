package dao.asu.state;

import gov.dol.bls.dfsms.laus.common.cache.ResourceCache;
import gov.dol.bls.dfsms.laus.common.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.apache.log4j.Logger;

import service.asu.state.ASUDataVO;
import util.ServiceLocator;
import util.SystemVars;
import util.UserMessage;
import dao.DAOUtility;
import exception.LAUSOneException;

public class ASUStateDAOImpl implements ASUStateDAO {

	private static Logger logger = Logger.getLogger("ASUStateDAOImpl.class");
	private boolean autoCommit = false;
	private boolean hasException = false;
	
	@Override
	public Map<String, ASUDataVO> getAllAsuByState(String stateCode, String asuProgramYear) {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//Set<String> asuCodeKeySet = new HashSet<String>();
		Map<String, ASUDataVO> asuDataMap = new LinkedHashMap<String, ASUDataVO>();

		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append(" from vw_asu where");
		sql.append(" statecode = ? and progyear = ? order by asucode");
		logger.debug("getAsuData: sql for " + stateCode + asuProgramYear + " : " + sql.toString());

		try
		{
			DataSource dataSource = ResourceCache.getInstance().getDatabaseSource(JDBCUtils.DS_NAME_LAUSToo_ASU);
			dbConnection = DAOUtility.openConnection(dataSource, autoCommit);

			ps = dbConnection.prepareStatement(sql.toString());

			ps.setString(1, stateCode);
			ps.setString(2, asuProgramYear);

			rs = ps.executeQuery();
			while(rs.next())
			{
				ASUDataVO asuData = new ASUDataVO();
				//asuData.setStateCode(rs.getString("statecode"));
				//asuData.setProgYear(rs.getString("progyear"));
				asuData.setAsuCode(rs.getString("asucode"));
				asuData.setAsuTitle(rs.getString("asu_title"));
				asuData.setDecenPop(getNullableValue("decen_pop",rs));
				asuData.setCurPop(getNullableValue("cur_pop",rs));
				asuData.setAsuLf(getNullableValue("asu_lf",rs));
				asuData.setAsuEmp(getNullableValue("asu_emp",rs));
				asuData.setAsuUnemp(getNullableValue("asu_unemp",rs));
				asuData.setAsuUrate(getNullableValue("asu_urate",rs));
				asuData.setPopCriteria(rs.getString("pop_criteria"));
				asuData.setRateCriteria(rs.getString("rate_criteria"));		
				asuData.setGeographicalDef(rs.getString("geographical_def"));	
				
				asuDataMap.put(rs.getString("asucode"), asuData);
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();

			logger.error(".getAsuData - SQLException for " + stateCode + ": " + se.getMessage());
			LAUSOneException lausoneException = new LAUSOneException(UserMessage.getUserMessage("199"));
			if(dbConnection == null)
			{
				lausoneException.setForwardPage(500);
			}
			else
			{
				lausoneException.setForwardPage(0);
			}			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			
		}
		finally
		{
			try
			{
				DAOUtility.release(dbConnection, ps, rs);
			}
			catch(Exception e)
			{
				logger.warn("Unable to close DB connection: " + e);
			}
		}
		return asuDataMap;
	}
	
	@SuppressWarnings("unchecked")
	protected final String getNullableValue(String colName, ResultSet rs) throws SQLException {
		 
		Object colValue = rs.getObject(colName);
		 
		if (rs.wasNull()) {
		return "N/A";
		}
		return colValue.toString();
	}
	
	/**
	 * convert the stateCode to stateNum
	 * 
	 *@param vProcessMode 
	 *@param  stateCoce
	 *@return stateNum
	 */
	@Override
	public String getStateNumByStateCode(String vProcessMode, String stateCoce) {
		
		
		Connection dbConnection = null;
		PreparedStatement statement = null;
		// CallableStatement aCStatement = null;
		ResultSet resultSet = null;
		String stateNum ="";
		
		DataSource dataSource;
		try {
			dataSource = ServiceLocator.getInstance().getDataSource(vProcessMode);
			dbConnection = DAOUtility.openConnection(dataSource, this.autoCommit);
			String sql = "select statenum from state where statecode=?";
			
			statement = dbConnection.prepareStatement(sql.toString());
			statement.setString(1, stateCoce);
			
			resultSet =statement.executeQuery();
			while (resultSet.next()) {
				stateNum = resultSet.getString("statenum");
				
			}
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			logger.error("Catch NamingException in getStateNumByStateCode: " + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			logger.error("Catch SQLException in getStateNumByStateCode: " + e);
		}finally{
			try {
				DAOUtility.commitOrRollback(dbConnection, this.hasException);

				DAOUtility.release(dbConnection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Unable to close DB connection: " + e);
			}
		}
				
		return stateNum;

	}

	public void saveNewInfoForASU(String vProcessMode,String stateNum,String asuCode,String asuTitle,String asuPgeoDef,String asuYear){
		
		Connection dbConnection = null;
		PreparedStatement statement = null;
		DataSource dataSource=null;
		
		
			try {
				dataSource = ServiceLocator.getInstance().getDataSource(vProcessMode);
				dbConnection = DAOUtility.openConnection(dataSource, this.autoCommit);
				String sql = "insert into ASU (ASUCODE,ASU_TITLE,GEOGRAPHICAL_DEF,PROGYEAR,STATECODE) VALUES (?,?,?,?,?)";
				statement = dbConnection.prepareStatement(sql.toString());
				statement.setString(1, asuCode);
				statement.setString(2, asuTitle);
				statement.setString(3, asuPgeoDef);
				statement.setString(4, asuYear);
				statement.setString(5, stateNum);
				int count = statement.executeUpdate();
				
				if(count>=0){
					logger.info("Save into db successfully");
				}
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				logger.error("Catch NamingException in saveNewInfoForASU: " + e);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Catch SQLException in saveNewInfoForASU: " + e);
			}finally{
				try {
					DAOUtility.commitOrRollback(dbConnection, this.hasException);

					DAOUtility.release(dbConnection);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					logger.error("Unable to close DB connection: " + e);
				}
			}
			
	}

	@Override
	public boolean isASUCodeExist(String vProcessMode,String code,String year) {
		// TODO Auto-generated method stub
		
		Connection dbConnection = null;
		PreparedStatement statement = null;
		boolean existed = false;
		ResultSet resultSet = null;
		DataSource dataSource=null;
		
		try {
			dataSource = ServiceLocator.getInstance().getDataSource(vProcessMode);
			dbConnection = DAOUtility.openConnection(dataSource, this.autoCommit);
		
			//String sql = "insert into ASU (ASU_CODE,ASUTITLE,GEOGRAPHICAL_DEF) VALUES (?,?,?)";
			String sql = "select ASUCODE from ASU where ASUCODE=? and PROGYEAR=?";
			statement = dbConnection.prepareStatement(sql.toString());
			statement.setString(1, code);
			statement.setString(2, year);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				if(resultSet.getString("ASUCODE") != null){
					existed = true;
				}
			}
			logger.debug("isASUCodeExist execute");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			logger.error("Catch NamingException in isASUCodeExist: " + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Catch SQLException in isASUCodeExist: " + e);
		}finally{
			try {
				DAOUtility.commitOrRollback(dbConnection, this.hasException);

				DAOUtility.release(dbConnection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Unable to close DB connection: " + e);
			}
		}
		
		return existed;
	}

	@Override
	public boolean isASUTitleExist(String vProcessMode,String title,String stateCode,String year) {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement statement = null;
		boolean existed = false;
		ResultSet resultSet = null;
		DataSource dataSource=null;
		
		try {
			dataSource = ServiceLocator.getInstance().getDataSource(vProcessMode);
			dbConnection = DAOUtility.openConnection(dataSource, this.autoCommit);
		
			//String sql = "insert into ASU (ASU_CODE,ASUTITLE,GEOGRAPHICAL_DEF) VALUES (?,?,?)";
			String sql = "select ASU_TITLE from ASU where STATECODE=? and ASU_TITLE=? and PROGYEAR=?";
			statement = dbConnection.prepareStatement(sql.toString());
			statement.setString(1, stateCode);
			statement.setString(2, title);
			statement.setString(3, year);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				if(resultSet.getString("ASU_TITLE") != null){
					existed = true;
				}
			}
			logger.debug("isASUTitleExist executed");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			logger.error("Catch NamingException in isASUTitleExist: " + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Catch SQLException in isASUTitleExist: " + e);
		}finally{
			try {
				DAOUtility.commitOrRollback(dbConnection, this.hasException);

				DAOUtility.release(dbConnection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Unable to close DB connection: " + e);
			}
		}
		
		return existed;
	}
	
	public List<Map<String,String>> getAllComponentAreas(String processMode, String stateCode, String progYear, String areaType,String cntyTitle) throws LAUSOneException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String countyTitle = cntyTitle == null ? "" : cntyTitle;
		/*
		String query="select t1.compcode,t1.COMP_TITLE, t2.asu_urate from asu_comp_def t1 inner join asu_comp_estimate t2 "
					  + "on (t1.compcode=t2.compcode and t1.progyear=t2.progyear) where t1.statecode=? and t2.progyear=? and t2.refmonth=? "
					  + " AREATYPE order by 1";
		*/
		String areaTypeCndn= getAreaTypeCond(areaType);
		StringBuilder buildQry=new StringBuilder();
		buildQry.append("select t1.compcode,t1.COMP_TITLE, t2.asu_urate from asu_comp_def t1 inner join asu_comp_estimate t2 ");
		buildQry.append(" on (t1.compcode=t2.compcode and t1.progyear=t2.progyear) where t1.statecode=? and t2.progyear=? and t2.refmonth=? ");
		buildQry.append(areaTypeCndn);
		if(countyTitle.length() > 0){
			buildQry.append(" and t1.county_title = ?");
		}
		buildQry.append(" order by 1");
		//query = query.replaceAll("AREATYPE", areaTypeCndn);
		
		
		logger.debug("Query: "+buildQry.toString());
		logger.debug("Params:["+stateCode+","+ progYear+"]");
		
		List<Map<String,String>> result= new ArrayList<Map<String, String>>();

		try (Connection conn = DAOUtility.openConnection(ServiceLocator.getInstance().getDataSource(processMode), autoCommit)){
			
			pstmt = conn.prepareStatement(buildQry.toString());
			pstmt.setString(1, stateCode);
			pstmt.setString(2, progYear);
			pstmt.setString(3, "99");
			if(countyTitle.length() > 0){
				pstmt.setString(4, cntyTitle);
			}	
			rs= pstmt.executeQuery();
			while(rs.next()){
				Map<String,String> aMap=new HashMap<String,String>();
				aMap.put("asucompcode", rs.getString("COMPCODE"));
				aMap.put("asucomptitle", rs.getString("COMP_TITLE"));
				aMap.put("asucompurate", rs.getString("ASU_URATE"));
				aMap.put("oper", "");
				result.add(aMap);
			}
			
		} catch (SQLException | NamingException e) {
			this.hasException = true;
			logger.error("SQLException occurred in " + e.getClass() + "getAllComponentAreas() :" + e.getMessage());
			e.printStackTrace();
			String errorMessage = SystemVars.oLAUSTitle + " encountered some errors in ASUStateDAOImpl::getAllComponentAreas(). Please contact system administrator.";
			LAUSOneException lausoneException = new LAUSOneException(errorMessage);
			lausoneException.setAdminError("Exception in getAllComponentAreas(): " + e.getMessage());
			throw lausoneException;
		}
		finally{
			try{
				DAOUtility.release(pstmt,rs);
			} catch(Exception e){}
		}
		
		return result;
	}
	
	public Map<String,String> getAllAreaTypes(String processMode) throws LAUSOneException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String query="select * from addareatypeinfo order by 1";
		logger.debug("Query: "+query);
		Map<String, String> result=new LinkedHashMap<String,String>();
		try (Connection conn = DAOUtility.openConnection(ServiceLocator.getInstance().getDataSource(processMode), autoCommit)){
			
			pstmt = conn.prepareStatement(query);
			rs= pstmt.executeQuery();
			while(rs.next()){
				result.put(rs.getString("ADDAREATYPE"), rs.getString("DESCR"));
			}
			
		} catch (SQLException | NamingException e) {
			this.hasException = true;
			logger.error("SQLException occurred in " + e.getClass() + "getAllAreaTypes() :" + e.getMessage());
			e.printStackTrace();
			String errorMessage = SystemVars.oLAUSTitle + " encountered some errors in ASUStateDAOImpl::getAllAreaTypes(). Please contact system administrator.";
			LAUSOneException lausoneException = new LAUSOneException(errorMessage);
			lausoneException.setAdminError("Exception in getAllAreaTypes(): " + e.getMessage());
			throw lausoneException;
			
		}
		finally{
			try{
				DAOUtility.release(pstmt,rs);
			} catch(Exception e){}
		}
		
		return result;
	}

	@Override
	public void deleteSelectedAsu(String stateCode, String asuProgramYear, String asuCode) {
		Connection dbConnection = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		
		StringBuilder delAsuSql = new StringBuilder();
		delAsuSql.append("delete from asu where asucode in (");
		delAsuSql.append(asuCode);
		delAsuSql.append(") and statecode = ? and progyear = ?");
		logger.debug("deleteSelectedAsu: delAsuSql for " + stateCode + asuProgramYear + " : " + delAsuSql.toString());
		
		StringBuilder delAsuEstimateSql = new StringBuilder();
		delAsuEstimateSql.append("delete from asu_estimate where asucode in (");
		delAsuEstimateSql.append(asuCode);
		delAsuEstimateSql.append(") and statecode = ? and progyear = ?");
		logger.debug("deleteSelectedAsu: delAsuEstimateSql for " + stateCode + asuProgramYear + " : " + delAsuEstimateSql.toString());

		StringBuilder delAsuAddSubSql = new StringBuilder();
		delAsuAddSubSql.append("delete from asu_addsub where asucode in (");
		delAsuAddSubSql.append(asuCode);
		delAsuAddSubSql.append(") and statecode = ? and progyear = ?");
		logger.debug("deleteSelectedAsu: delAsuAddSubSql for " + stateCode + asuProgramYear + " : " + delAsuAddSubSql.toString());

		try 		
		{
			DataSource dataSource = ResourceCache.getInstance().getDatabaseSource(JDBCUtils.DS_NAME_LAUSToo_ASU);
			dbConnection = DAOUtility.openConnection(dataSource, autoCommit);

			ps1 = dbConnection.prepareStatement(delAsuSql.toString());
			ps1.setString(1, stateCode);
			ps1.setString(2, asuProgramYear);
			
			ps2 = dbConnection.prepareStatement(delAsuEstimateSql.toString());
			ps2.setString(1, stateCode);
			ps2.setString(2, asuProgramYear);
			
			ps3 = dbConnection.prepareStatement(delAsuAddSubSql.toString());
			ps3.setString(1, stateCode);
			ps3.setString(2, asuProgramYear);			

			ps1.executeQuery();	
			ps2.executeQuery();	
			ps3.executeQuery();	
		}
		catch(SQLException se)
		{
			se.printStackTrace();

			logger.error(".deleteSelectedAsu - SQLException for " + stateCode + ": " + se.getMessage());
			LAUSOneException lausoneException = new LAUSOneException(UserMessage.getUserMessage("199"));
			if(dbConnection == null)
			{
				lausoneException.setForwardPage(500);
			}
			else
			{
				lausoneException.setForwardPage(0);
			}			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			
		}
		finally
		{
			try
			{
				DAOUtility.release(dbConnection, ps1);
				DAOUtility.release(dbConnection, ps2);
				DAOUtility.release(dbConnection, ps3);
			}
			catch(Exception e)
			{
				logger.warn("Unable to close DB connection: " + e);
			}			
		}		
	}

	@Override
	public boolean isAsuFinalized(String stateCode, String asuProgramYear) {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		boolean finalized = false;

		StringBuilder sql = new StringBuilder();
		sql.append("select stepstatus from prodstatus where");
		sql.append(" statecode = ? and progyear = ? and prodstep = 2");
		logger.debug("isAsuFinalized: sql for " + stateCode + asuProgramYear + " : " + sql.toString());

		try
		{
			DataSource dataSource = ResourceCache.getInstance().getDatabaseSource(JDBCUtils.DS_NAME_LAUSToo_ASU);
			dbConnection = DAOUtility.openConnection(dataSource, autoCommit);

			ps = dbConnection.prepareStatement(sql.toString());

			ps.setString(1, stateCode);
			ps.setString(2, asuProgramYear);

			rs = ps.executeQuery();
			while(rs.next())
			{
				finalized = (rs.getString("stepstatus").equalsIgnoreCase("Y"))? true : false;
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();

			logger.error(".isAsuFinalized - SQLException for " + stateCode + ": " + se.getMessage());
			LAUSOneException lausoneException = new LAUSOneException(UserMessage.getUserMessage("199"));
			if(dbConnection == null)
			{
				lausoneException.setForwardPage(500);
			}
			else
			{
				lausoneException.setForwardPage(0);
			}			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		finally
		{
			try
			{
				DAOUtility.release(dbConnection, ps, rs);
			}
			catch(Exception e)
			{
				logger.warn("Unable to close DB connection: " + e);
			}
		}
		return finalized;
	}

	@Override
	public void finalizeAsu(String stateCode, String asuProgramYear) {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder();
		sql.append("update prodstatus set stepstatus = 'Y' where");
		sql.append(" statecode = ? and progyear = ? and prodstep = 2");
		logger.debug("finalizeAsu: sql for " + stateCode + asuProgramYear + " : " + sql.toString());

		try
		{
			DataSource dataSource = ResourceCache.getInstance().getDatabaseSource(JDBCUtils.DS_NAME_LAUSToo_ASU);
			dbConnection = DAOUtility.openConnection(dataSource, autoCommit);

			ps = dbConnection.prepareStatement(sql.toString());

			ps.setString(1, stateCode);
			ps.setString(2, asuProgramYear);

			rs = ps.executeQuery();			
		}
		catch(SQLException se)
		{
			se.printStackTrace();

			logger.error(".finalizeAsu - SQLException for " + stateCode + ": " + se.getMessage());
			LAUSOneException lausoneException = new LAUSOneException(UserMessage.getUserMessage("199"));
			if(dbConnection == null)
			{
				lausoneException.setForwardPage(500);
			}
			else
			{
				lausoneException.setForwardPage(0);
			}			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());			
		}
		finally
		{
			try
			{
				DAOUtility.release(dbConnection, ps, rs);
			}
			catch(Exception e)
			{
				logger.warn("Unable to close DB connection: " + e);
			}
		}		
	}
	
	public Map<String,String> calculateAsu(String processMode, List<Object> dataLs) throws LAUSOneException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String query="select F_ASU_CALCULATE(?) from dual";
		Map<String,String> aMap=new HashMap<String,String>();
		try (Connection conn = DAOUtility.openConnection(ServiceLocator.getInstance().getDataSource(processMode), autoCommit)){
			ArrayDescriptor descr= ArrayDescriptor.createDescriptor("T_ASU_COMP_OBJ",conn);
			Object[] dataArr= dataLs.toArray();
			if(dataArr != null && dataArr.length > 0){
				ARRAY p_data = new ARRAY(descr, conn, dataArr);
				pstmt=conn.prepareStatement(query);
				pstmt.setArray(1, p_data); 
				rs=pstmt.executeQuery();
				if(rs.next()){
					//Object obj=rs.getObject(1); 					

					Struct obj = (Struct) rs.getObject(1);
					logger.debug("EMP: "+obj.getAttributes()[0]) ;
					logger.debug("UNEMP: "+obj.getAttributes()[1]) ;
					logger.debug("LF: "+obj.getAttributes()[2]) ;
					logger.debug("URATE: "+obj.getAttributes()[3]) ;
					logger.debug("CURPOP: "+obj.getAttributes()[4]) ;
					logger.debug("DECENPOP: "+obj.getAttributes()[5]) ;
					int curpop= obj.getAttributes()[4] != null ?  Integer.parseInt(obj.getAttributes()[4].toString()) : 0;
					int cenpop= obj.getAttributes()[5] != null ?  Integer.parseInt(obj.getAttributes()[5].toString()) : 0;
					float urate= obj.getAttributes()[3] != null ?  Float.parseFloat(obj.getAttributes()[3].toString()) : 0;
					aMap.put("emp", obj.getAttributes()[0] != null ? obj.getAttributes()[0].toString():"");
					aMap.put("unemp", obj.getAttributes()[1] != null ? obj.getAttributes()[1].toString():"");
					aMap.put("lf", obj.getAttributes()[2] != null ? obj.getAttributes()[2].toString():"");
					aMap.put("urate", obj.getAttributes()[3] != null ? obj.getAttributes()[3].toString():"");
					aMap.put("curpop", curpop+"");
					aMap.put("decenpop", cenpop+"");
					
					if(curpop >= 10000 || cenpop >= 10000){
						aMap.put("popcrit", "Yes");
					}
					else{
						aMap.put("popcrit", "No");
					}
					
					if(urate >= 6.5){
						aMap.put("ratecrit", "Yes");
					}
					else{
						aMap.put("ratecrit", "No");
					}
				}	
				
			}

		} catch (SQLException | NamingException e) {
			this.hasException = true;
			logger.error("SQLException occurred in " + e.getClass() + "calculateAsu() :" + e.getMessage());
			e.printStackTrace();
			String errorMessage = SystemVars.oLAUSTitle + " encountered some errors in ASUStateDAOImpl::calculateAsu(). Please contact system administrator.";
			LAUSOneException lausoneException = new LAUSOneException(errorMessage);
			lausoneException.setAdminError("Exception in calculateAsu(): " + e.getMessage());
			throw lausoneException;
			
		}
		finally{
			try{
				DAOUtility.release(pstmt,rs);
			} catch(Exception e){}
		}
		
		return aMap;
	}
	
	public List<Map<String,String>> revCalculateAsu(String processMode, List<Object> dataLs) throws LAUSOneException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Map<String,String>> result=new ArrayList<Map<String,String>>();
		String query="select * from table( FN_C(?))";
		try (Connection conn = DAOUtility.openConnection(ServiceLocator.getInstance().getDataSource(processMode), autoCommit)){
			ArrayDescriptor descr= ArrayDescriptor.createDescriptor("T_ASU_COMP_OBJ",conn);
			Object[] dataArr= dataLs.toArray();
			if(dataArr != null && dataArr.length > 0){
				ARRAY p_data = new ARRAY(descr, conn, dataArr);
				pstmt=conn.prepareStatement(query);
				pstmt.setArray(1, p_data); 
				rs=pstmt.executeQuery();
				while(rs.next()){
					Map<String,String> aMap=new HashMap<String,String>();
					int curpop= rs.getInt("asu_curpop") ;
					int cenpop= rs.getInt("asu_decenpop"); 
					String lf= rs.getString("asu_lf") != null ? rs.getString("asu_lf"):"";
					String emp= rs.getString("asu_emp")!= null ? rs.getString("asu_emp"):"";
					String unemp= rs.getString("asu_unemp")!= null ? rs.getString("asu_unemp"):"";
					float urate = rs.getFloat("asu_urate") ;
					
					aMap.put("asucode",rs.getString("asu_code"));
					aMap.put("refmonth", rs.getString("refmonth"));
					aMap.put("refyear", rs.getString("refyear"));
					aMap.put("progyear", rs.getString("progyear"));
					aMap.put("emp", emp);
					aMap.put("unemp",unemp);
					aMap.put("lf", lf);
					aMap.put("urate", urate+"");
					aMap.put("curpop", curpop+"");
					aMap.put("decenpop", cenpop+"");
					if(curpop >= 10000 || cenpop >= 10000){
						aMap.put("popcrit", "Yes");
					}
					else{
						aMap.put("popcrit", "No");
					}
					
					if(urate >= 6.5){
						aMap.put("ratecrit", "Yes");
					}
					else{
						aMap.put("ratecrit", "No");
					}
					
					result.add(aMap);
				}	
				
			}

		} catch (SQLException | NamingException e) {
			this.hasException = true;
			logger.error("SQLException occurred in " + e.getClass() + "calculateAsu() :" + e.getMessage());
			e.printStackTrace();
			String errorMessage = SystemVars.oLAUSTitle + " encountered some errors in ASUStateDAOImpl::calculateAsu(). Please contact system administrator.";
			LAUSOneException lausoneException = new LAUSOneException(errorMessage);
			lausoneException.setAdminError("Exception in calculateAsu(): " + e.getMessage());
			throw lausoneException;
			
		}
		finally{
			try{
				DAOUtility.release(pstmt,rs);
			} catch(Exception e){}
		}
		
		return result;
	}

	
	public List<String> getAllCountyTitles(String processMode,String stateCode, String progYear) throws LAUSOneException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String query="select distinct county_title from asu_comp_def where county_title is not null and statecode=? and progyear=? order by 1";
		logger.debug("Query: "+query);
		List<String> result=new ArrayList<String>();
		try (Connection conn = DAOUtility.openConnection(ServiceLocator.getInstance().getDataSource(processMode), autoCommit)){
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, stateCode);
			pstmt.setString(2, progYear);
			rs= pstmt.executeQuery();
			while(rs.next()){
				result.add(rs.getString("COUNTY_TITLE"));
			}
			
		} catch (SQLException | NamingException e) {
			this.hasException = true;
			logger.error("SQLException occurred in " + e.getClass() + "getAllCountyTitles() :" + e.getMessage());
			e.printStackTrace();
			String errorMessage = SystemVars.oLAUSTitle + " encountered some errors in ASUStateDAOImpl::getAllCountyTitles(). Please contact system administrator.";
			LAUSOneException lausoneException = new LAUSOneException(errorMessage);
			lausoneException.setAdminError("Exception in getAllCountyTitles(): " + e.getMessage());
			throw lausoneException;
			
		}
		finally{
			try{
				DAOUtility.release(pstmt,rs);
			} catch(Exception e){}
		}
		
		return result;
	}

	
	private String getAreaTypeCond(String areaType){
		String condn="";
		if(areaType == null){
			return condn;
		}
		switch(areaType){
			case "4":
				condn=" and AT_4=1";
				break;
			case "5":
				condn=" and AT_5=1";
				break;
			case "6":
				condn=" and AT_6=1";
				break;
			case "7":
				condn=" and AT_7=1";
				break;
			case "8":
				condn=" and AT_8=1";
				break;
			case "9":
				condn=" and AT_9=1";
				break;
			case "10":
				condn=" and AT_10=1";
				break;
			case "11":
				condn=" and AT_11=1";
				break;
			case "12":
				condn=" and AT_12=1";
				break;
			case "13":
				condn=" and AT_13=1";
				break;
			case "14":
				condn=" and AT_14=1";
				break;
			case "15":
				condn=" and AT_15=1";
				break;
			case "16":
				condn=" and AT_16=1";
				break;
			case "17":
				condn=" and AT_17=1";
				break;
			case "19":
				condn=" and AT_19=1";
				break;
			case "20":
				condn=" and AT_20=1";
				break;
				
		};
		return condn;
	}
	
}