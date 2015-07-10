<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://bls/utility/CallStaticBeanTag.tld" prefix="ut"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="bls"%>
<c:set var="appContext" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<%@include file="/header/Metatags.jsp"%>
<title><c:set var="titleObj"
		value="${initParam.title} ${initParam.version}" /> <c:out
		value="${titleObj}(${os})"></c:out></title>
<link rel="stylesheet"
	href="<c:out value='${appContext}'/>/stylesheet/process1024.css"
	type="text/css" />
<link title="Normal Text" media="aural,braille,embossed"
	href="<c:out value='${appContext}'/>/stylesheet/size75.css"
	type="text/css" rel="stylesheet" />
<link title="Medium Text" media="screen,print"
	href="<c:out value='${appContext}'/>/stylesheet/size85.css"
	type="text/css" rel="alternate stylesheet" />
<link title="Large Text" media="screen,print"
	href="<c:out value='${appContext}'/>/stylesheet/size95.css"
	type="text/css" rel="alternate stylesheet" />
<link rel="stylesheet" type="text/css" href="${appContext}/stylesheet/asu/asuEdit.css"/>
	
<script type="text/javascript">
	var varContextPath = '${pageContext.request.contextPath}';
</script>
<script type="text/javascript"
	src="<c:out value='${appContext}'/>/javascript/utility.js"></script>
<script type="text/javascript"
	src="<c:out value='${appContext}'/>/javascript/popup.js"></script>
<!-- script type="text/javascript"
	src="<c:out value='${appContext}'/>/javascript/jquery-1.5.min.js"></script -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.8.1/mustache.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/1.4.5/numeral.min.js"></script>
<script src="<c:out value='${appContext}'/>/javascript/asucalc.js"></script>

<style>
</style>
<script type="text/javascript">
	var selMap = [];
	var opttmpl="<option title='{{asucomptitle}}[{{asucompurate}}]' value={{asucompcode}}>{{asucompcode}} {{asucomptitle}} [{{asucompurate}}]</option>";
	var opt2tmpl="<option title='{{asucomptitle}}[{{asucompurate}}]' value={{oper}}{{asucompcode}}>[{{oper}}]{{asucompcode}} {{asucomptitle}} [{{asucompurate}}]</option>";
	var stCode= '${user.actingStateCode}';
	var progyear= '${progYear}';
	var param = {stateCode: stCode, progyear: progyear};
	$(document).ready(function(){
			var aMap;
			var len;
			loadFlag = true;
			Popup.showModal("lmodal");
			bufMap = ${compareas};
			len = bufMap.length > 0 ? bufMap.length : 25;
			//console.log(bufMap);
			//console.log(progyear);
			//bufMap = jQuery.parseJSON(compAreaInStr);
			setTimeout(function(){
				populatesrc(bufMap);
				Popup.hide('lmodal');
				loadFlag=false;
			}, 500);
			
			$("#areatypesFtr").change( function(){
				//console.log("areatype changed.");
				var selCounty= "";
				if($('#cntyFltr').is(':checked')){
					selCounty= $("#selCounty").val();
				}
				param = {stateCode: stCode, progyear: progyear, areaType:$(this).val(),cntyTitle:selCounty };
				loadFlag = true;
				Popup.showModal("lmodal");
				$.ajax({
					type: 'GET',
					url: varContextPath + '/rest/asu/allcompareas',
					data: param,
					success: function(data){
						selMap = $("#to option").map(function() {
							var tmpcompcode= $(this).val().substring(1);
							//console.log(tmpcompcode);
							var fndEle=_.findWhere(data, {asucompcode: tmpcompcode});
							return fndEle;
						}).get();
						var newArr=_.difference(data, selMap);
						populatesrc(newArr);
						Popup.hide('lmodal');
					},
					error: function(xhr,status,error){
						//console.log(xhr, status, error);
					}
				});
			});
			
			$("#cntyCont").hide();
			$("#cntyFltr").click(function(){
				if($('#cntyFltr').is(':checked')){
					$("#cntyCont").show();
				}
				else{
					$("#cntyCont").hide();
				}
			});
			
			//countyTitle change fn
			$("#selCounty").change( function(){
				param = {stateCode: stCode, progyear: progyear, areaType:'', cntyTitle:$(this).val() };
				loadFlag = true;
				Popup.showModal("lmodal");
				$.ajax({
					type: 'GET',
					url: varContextPath + '/rest/asu/allcompareas',
					data: param,
					success: function(data){
						selMap = $("#to option").map(function() {
							var tmpcompcode= $(this).val().substring(1);
							//console.log(tmpcompcode);
							var fndEle=_.findWhere(data, {asucompcode: tmpcompcode});
							return fndEle;
						}).get();
						var newArr=_.difference(data, selMap);
						populatesrc(newArr);
						Popup.hide('lmodal');
					},
					error: function(xhr,status,error){
						//console.log(xhr, status, error);
					}
				});
			
			});
			
		});
</script>
</head>
<body>
	<div id="topwrapper">
		<jsp:include page="/header/MenuHeader.jsp?menu=asu" flush="true"></jsp:include>

		<ut:call className="util.LAUSOneHelpFile"
			methodName="getLAUSOneHelpFile" param="${user.mainIISIP}"
			param2="99990|1"></ut:call>
		<c:set var="helpLnk" value="${lausonehelpurl}${result}" />
		<c:set var="parameters"
			value="?mainmenu=ASU&mainmenulink=${appContext}/listASUAction.do&menuitem=ASU Edit-Calc-Save&helplink=${helpLnk}" />
		<c:set var="pageURL" value="/header/NavBar.jsp${parameters}" />
		<jsp:include page="${pageURL}" flush="true" />
	</div>
	<jsp:include page="/header/LoginUserInfo.jsp?isASUState=Y" flush="true" /><br />
	<br />
	<div style="border: 1px solid;">
		<h4>Areas of Substantial Unemployment-Component Selection</h4>
		<html:form action="/editViewASU" method="POST">

			<div id="outerShell">
				<div>
					Selected ASU Area:&nbsp;&nbsp;${asucode}<br> 
					<div style="width:100%;">	
						<table>
							<tr>
							<td  style="width:30%;text-align:left;">
								<div>
									<label for="cntyFltr"> Filter Component List by County: </label>
									<input	type="checkbox" id="cntyFltr"  name="cntyFltr" >
								</div>
							</td>
							<td style="width:68%;" align="right">
								<div id="cntyCont" style="float:left;width:70%">
										<select id="selCounty" name="selCounty" >
												<option value="">All</option>
												<logic:iterate id="cntyTitle" collection="${countyTitles}" >
														<option value="${cntyTitle}">${cntyTitle}</option>
												</logic:iterate>
										</select>
								</div>
							</td>
							</tr>
						</table>
					</div>
					<div> 
					Select Area Type for Component	Selection: <select id="areatypesFtr" name="areaTypes" >
																	<option value="">All</option>
																	<logic:iterate id="areaTypObj" collection="${areatypes}" >
																		<bean:define id="areaType" name="areaTypObj" property="key" />
																			<option value="${areaType}">
																				<bean:write name="areaTypObj" property="value" />
																			</option>
																	</logic:iterate>
																</select><br>
					</div>											
				</div>
				<br>
				<div id="outermlistCont">
					<div id="lblradCont">
						<div class="lblcont">Select Component area(s):</div>
						<div id="radcont">
							<table class="radbox">
								<tr>
									<td align="left"><input type="radio" name="addsub"
										value="A" />Addition<br> <input type="radio" name="addsub"
										value="S" />Subtraction</td>
								</tr>
							</table>
						</div>
						<div class="lblcont">Selected Component area(s):</div>
					</div>
					<br>
					<div id="mlisthdr">
						<table id="srcList">
							<tr>
								<td style="width: 25%;"><a
									href="javascript:sortMoveList('from','asucompcode');">Areacode</a></td>
								<td style="width: 50%;"><a
									href="javascript:sortMoveList('from','asucomptitle');">Title</a></td>
								<td style="width: 23%;"><a
									href="javascript:sortMoveList('from','asucompurate');">Rate</a></td>
							</tr>
						</table>
						<table class="controls">
							<tr>
								<td></td>
							</tr>
						</table>
						<table id="destList">
							<tr>
								<td style="width: 25%;"><a
									href="javascript:sortMoveList('to','asucompcode');">Areacode</a></td>
								<td style="width: 50%;"><a
									href="javascript:sortMoveList('to','asucomptitle');">Title</a></td>
								<td style="width: 23%;"><a
									href="javascript:sortMoveList('to','asucompurate');">Rate</a></td>
							</tr>
						</table>
					</div>
					<div id="mlistcont">
						<div id="fromDiv">
							<select multiple size="10" id="from" name="sourcebox">
							</select>
						</div>
						<table class="controls">
							<tr>
									<td align="center"><input class="btnMove" type="button"
										name="btnSelAll" onclick="javascript:moveAll('from', 'to')"
										value="&nbsp;&nbsp;&gt;&gt;&nbsp;"></input><br/> <input
										class="btnMove" type="button" name="btnSel"
										onclick="javascript:moveSelected('from', 'to')"
										value="&nbsp;&nbsp;&gt;&nbsp;&nbsp;"></input> <br/> <input
										class="btnMove" type="button" name="btndeSel"
										onclick="javascript:moveSelected('to', 'from')"
										value="&nbsp;&nbsp;&lt;&nbsp;&nbsp;"></input> <br/> <input
										class="btnMove" type="button" name="btndeSelAll"
										onclick="javascript:moveAll('to', 'from')"
										value="&nbsp;&nbsp;&lt;&lt;&nbsp;"></input>
							</td>
							</tr>
						</table>
						<div id="toDiv">
							<select multiple id="to" size="10" name="destbox"></select>
						</div>	
					</div>
				</div>
				<div id="savCalcCont">
					<input class="savCalcCancbtn" type="button" name="btnCalc"
						value="Calculate"  onclick="calculate()" />
				</div>
				<br> <br>
				<div id="totalCont">
					ASU Totals:<br>
					<table id="tblCalc">
						<tr>
							<th style="width: 15%;">Census 2010 Population</th>
							<th style="width: 15%;">Latest July 1 Population</th>
							<th style="width: 15%;">Labor Force</th>
							<th style="width: 15%;">Employment</th>
							<th style="width: 15%;">Unemployment</th>
							<th style="width: 5%;">Rate</th>
							<th style="width: 10%;">Population Criteria Met?</th>
							<th style="width: 10%;">Rate Criteria Met?</th>
						</tr>
						<tbody>
							<tr>
								<td id="cenpop">&nbsp;</td>
								<td id="curpop">&nbsp;</td>
								<td id="lf">&nbsp;</td>
								<td id="emp">&nbsp;</td>
								<td id="unemp">&nbsp;</td>
								<td id="urate">&nbsp;</td>
								<td id="popcrit" align="center">&nbsp;</td>
								<td id="ratecrit" align="center">&nbsp;</td>
							</tr>
						</tbody>
					</table>
				</div>
				<br/>
				<div id="saveCancCont">
					<input class="savCalcCancbtn" type="button" name="btnSave" value="Save" onclick="doSave()" />&nbsp;&nbsp;
					<input class="savCalcCancbtn" type="button" name="btnCancel" value="Cancel" />
				</div>
			</div>
			<br>
			<input type="hidden" id="asuCode" name="asuCode" value="${asucode}"/>
			<input type="hidden" id="selCompAreas" name="selCompAreas" value=""/>
			<input type="hidden" id="calclf" name="calclf" value=""/>
			<input type="hidden" id="calcemp" name="calcemp" value=""/>
			<input type="hidden" id="calcunemp" name="calcunemp" value=""/>
			<input type="hidden" id="calcurate" name="calcurate" value=""/>
			<input type="hidden" id="calccurpop" name="calccurpop" value=""/>
			<input type="hidden" id="calccenpop" name="calccenpop" value=""/>
		</html:form>
	</div>
	
	<div class="asu-mod-confirm">
		<h3>ASU-Save-Confirm</h3>
		<div class="asu-mod-body">
			<p>Do you want to save your selected components before exiting?</p>
		</div>
		<div class="asu-mod-action">
			<button  name="btnConfOK" onclick="$('.asu-mod-confirm').hide()" >OK</button>&nbsp;&nbsp;&nbsp;
			<button  name="btnConfCanc" onclick="location.href='${appContext}/listASUAction.do'" >Cancel</button>
		</div>
	</div>
	
	<div class="asu-comp-modal">
		<h3>ASU-Alert-Modal</h3>
		<div class="asu-mod-body">
			<p>Please select Addition/Subtraction operation for component selection.</p>
		</div>
		<div class="asu-mod-action">
			<button  name="btnOK" onclick="$('.asu-comp-modal').hide()" >OK</button>
		</div>
	</div>
	
<jsp:include page="/header/WaitingDiv.jsp?loading=Y" flush="true" />	
</body>
</html>
