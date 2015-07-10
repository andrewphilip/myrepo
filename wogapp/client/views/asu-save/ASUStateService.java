package service.asu.state;

import java.util.List;
import java.util.Map;

import dao.asu.state.ASUStateDAO;
import dao.asu.state.ASUStateDAOImpl;
import exception.LAUSOneException;

public class ASUStateService {	
	
	private static ASUStateDAO asuDAO = null;
	
	public static ASUStateDAO getDAOInstance(){
		if(asuDAO == null){
			asuDAO = new ASUStateDAOImpl();
		}
		return asuDAO;
	}
	
	
	public Map<String, ASUDataVO> listASU(String stateCode, String asuProgramYear) {
		return getDAOInstance().getAllAsuByState(stateCode, asuProgramYear);
	}
	
	public void deleteASU(String stateCode, String asuProgramYear, String asuCode) {
		getDAOInstance().deleteSelectedAsu(stateCode, asuProgramYear, asuCode);
	}
	
	public String getStateNumByStateCode(String vProcessMode, String stateCode){
		
		ASUStateDAO asuStateDao = new ASUStateDAOImpl();
		String stateNum = asuStateDao.getStateNumByStateCode(vProcessMode, stateCode);
		return stateNum;
	}

	public void saveNewInfoForASU(String vProcessMode,String stateCode,String asuCode,String asuTitle,String asuPgeoDef,String progYear){
		ASUStateDAO asuStateDao = new ASUStateDAOImpl();
		asuStateDao.saveNewInfoForASU(vProcessMode,stateCode, asuCode, asuTitle, asuPgeoDef, progYear);
	}
	public boolean isASUCodeExist(String vProcessMode,String asuCode,String progYear) {
		ASUStateDAO asuStateDao = new ASUStateDAOImpl();
		return asuStateDao.isASUCodeExist(vProcessMode, asuCode, progYear);
	}
	public boolean isASUTitleExist(String vProcessMode,String title,String stateCode,String year){
		ASUStateDAO asuStateDao = new ASUStateDAOImpl();
		return asuStateDao.isASUTitleExist(vProcessMode, title, stateCode, year);
	}
	
	
	public List<Map<String,String>> getAllComponentAreas(String processMode, String stateCode,String progYear,String areaType,String cntyTitle) throws LAUSOneException{
		return getDAOInstance().getAllComponentAreas(processMode, stateCode, progYear,areaType,cntyTitle);
	}
	
	public Map<String,String> getAllAreaTypes(String processMode) throws LAUSOneException{
		return getDAOInstance().getAllAreaTypes(processMode);
	}
	
	public boolean isAsuFinalized(String stateCode, String asuProgramYear) {
		return getDAOInstance().isAsuFinalized(stateCode, asuProgramYear);
	}
	
	public void finalizeASU(String stateCode, String asuProgramYear) {
		getDAOInstance().finalizeAsu(stateCode, asuProgramYear);
	}	
	
	public  Map<String,String> calculateAsu(String processMode, List<Object> dataLs) throws LAUSOneException{
		 return getDAOInstance().calculateAsu(processMode, dataLs);
	}
	
	public List<String> getAllCountyTitles(String processMode,String stateCode, String progYear) throws LAUSOneException{
		return getDAOInstance().getAllCountyTitles(processMode, stateCode, progYear);
	}	
	public List<Map<String,String>> revCalculateAsu(String processMode, List<Object> dataLs) throws LAUSOneException{
		return getDAOInstance().revCalculateAsu(processMode, dataLs);
	}	
		
	
}
