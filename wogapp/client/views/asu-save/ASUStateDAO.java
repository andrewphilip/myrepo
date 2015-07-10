package dao.asu.state;

import java.util.List;
import java.util.Map;

import exception.LAUSOneException;
import service.asu.state.ASUDataVO;

public interface ASUStateDAO {
	public Map<String, ASUDataVO> getAllAsuByState(String stateCode, String asuProgramYear);
	public void deleteSelectedAsu(String stateCode, String asuProgramYear, String asuCode);
	public boolean isAsuFinalized(String stateCode, String asuProgramYear);
	public void finalizeAsu(String stateCode, String asuProgramYear);
	

	/*
	 * Dao For AUS New Info Page
	 */
	public String getStateNumByStateCode(String vProcessMode, String stateCode);
	public void saveNewInfoForASU(String vProcessMode,String stateCode,String asuCode,String asuTitle,String asuPgeoDef,String asuYear);	
	public boolean isASUCodeExist(String vProcessMode, String code,String year);
	public boolean isASUTitleExist(String vProcessMode,String title,String stateCode ,String year);
	
	
	/**
	 * DAO interface for ASU Edit/Save module
	 */
	public List<Map<String,String>> getAllComponentAreas(String processMode, String stateCode,String progYear,String areaType,String cntyTitle) throws LAUSOneException;
	public Map<String,String> getAllAreaTypes(String processMode) throws LAUSOneException;
	public  Map<String,String> calculateAsu(String processMode, List<Object> dataLs) throws LAUSOneException;
	public List<String> getAllCountyTitles(String processMode,String stateCode, String progYear) throws LAUSOneException;	
	public List<Map<String,String>> revCalculateAsu(String processMode, List<Object> dataLs) throws LAUSOneException;
	
	
}
