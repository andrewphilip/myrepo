package laus.rest.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import service.asu.state.ASUStateService;
import user.User;
import util.ServiceLocator;
import exception.LAUSOneException;

@Path("/asu")
public class ASURestHandler {

	private static Logger logger = Logger.getLogger("ASURestHandler.class");
	
	@Context
	private HttpServletRequest request;
	

	@GET
	@Path("/allcompareas")
	@Produces(MediaType.APPLICATION_JSON)
	public  List<Map<String,String>> getAllComponentAreas(@QueryParam("stateCode") String stateCode, @QueryParam("progyear") String progyear,@QueryParam("areaType") String areaType, 
			                                              @QueryParam("cntyTitle") String cntyTitle) throws LAUSOneException{
		logger.debug("In getAllComponentAreas() >> statecode: "+stateCode);
		ASUStateService srvc= new ASUStateService();
		List<Map<String,String>> result=srvc.getAllComponentAreas(ServiceLocator.ASU, stateCode,progyear,areaType,cntyTitle);
		return result;
	}
	
	
	@POST
	@Path("/calc")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String,String>> doCalculate(@Context HttpHeaders headers, @FormParam("asuCode") String asuCode,@FormParam("stateCode") String stateCode,
										  @FormParam("progYear") String progYear, @FormParam("selCompAreas") String selCompAreas ){
		logger.debug("ASUCode:"+asuCode);
		logger.debug("CompAreas:"+selCompAreas);
		logger.debug("StateCode:"+stateCode);
		logger.debug("ProgYear:"+progYear);
		HttpSession session = request.getSession(false);
		User user=null;
		if(session != null){
			user = session.getAttribute("user") != null ? (User)session.getAttribute("user"):null;
		} 
		if(user != null){
			logger.debug("User ID:"+ user.getUserId());
		}
		List<Object> res = convert2DBObj(asuCode,stateCode,progYear,selCompAreas);
		logger.debug(res.toArray());
		ASUStateService srvc=new ASUStateService();
		//Map<String, String> aMap=null;
		List<Map<String,String>> list=null;
		try{
			//aMap=srvc.calculateAsu(ServiceLocator.ASU, res);
			list=srvc.revCalculateAsu(ServiceLocator.ASU, res);
			ObjectMapper mapper= new ObjectMapper();
			String jsonStr=mapper.writeValueAsString(list);
			System.out.println("Result string: "+jsonStr);
			Map<String, String> resultMap=new HashMap<String,String>();
			resultMap.put("res2string", jsonStr);
			list.add(resultMap);
			
		}catch(Exception e){
			e.printStackTrace();
		}			
		return list;
	}
	//saveCalc

	@POST
	@Path("/saveCalc")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String doSave(@Context HttpHeaders headers, @FormParam("asuCode") String asuCode,@FormParam("stateCode") String stateCode,
										  @FormParam("progYear") String progYear, @FormParam("selCompAreas") String selCompAreas,
										  @FormParam("calcVals") String calcVals ){
		String calcVO =calcVals;
		System.out.println("Calculated VO: " + calcVO);
		List<Map<String,String>> resultObj;
		ObjectMapper mapper= new ObjectMapper();
		try {
			resultObj = mapper.readValue(calcVO, new TypeReference <List<Map<String,String>>>() {});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	
	private List<Object> convert2DBObj(String asuCode, String stateCode, String progYear, String selCompAreas){
		String[] compArr=selCompAreas.split(" ");
		List<Object> result=new ArrayList<Object>();
		for(String compArea: compArr){
			if(compArea.startsWith("+")){
				logger.debug("List["+ stateCode+","+asuCode+","+progYear+","+compArea+",+]");
				result.add(new Object[] {stateCode,asuCode,progYear,compArea.substring(1),"+" });
			}
			else{
				logger.debug("List["+ stateCode+","+asuCode+","+progYear+","+compArea+",-]");
				result.add(new Object[] {stateCode,asuCode,progYear,compArea.substring(1),"-" });
			}
		}
		return result;
	}
	
}
