package com.glens.pwCloudOs.transmission.tower.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.web.ControllerForm;

public class TowerForm extends ControllerForm {
	
	private String voltageLevel;//电压等级
	
	private String xlinteRid;//线路id
	
	private String inteRid; //杆塔id
	
	private String xlName;
	
	private String gtName;
	
	private String picCode;
	
	private String picCode2;
	
	public String getPicCode2() {
		return picCode2;
	}

	public void setPicCode2(String picCode2) {
		this.picCode2 = picCode2;
	}

	private String searchName;
	
	
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getPicCode() {
		return picCode;
	}

	public void setPicCode(String picCode) {
		this.picCode = picCode;
	}

	public String getGtName() {
		return gtName;
	}

	public void setGtName(String gtName) {
		this.gtName = gtName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	//缺陷
	private String companyCode;
	
	private String defectCode; 
	
	private String defectClass;
	
	private int defectSource;
	
	private int defectCategory;
	
	
	private int defectReason;
	
	private String defectContent;
	
	private String  defectContentDesc;
	
	private String  findPersonId;
	
	private String  findDate;
	
	private String  longitude;//经度
	
	private String  latitude;//纬度
	
	private String state;
	
	private String  defectLocation;
	
	private String  defectPic1;
	
	private String  defectPic2;
	
	private String  defectPic3;
	
	private String defectCause;//原因初步分析
	
	private String proposalElimScheme;//建议消缺方案
	
	//缺陷审核
	private int isPass;//是否通过
	
	private String remarks;//审核描述放在remarks
	
	private String  eliminationDes;//消缺说明
	
	private String  clearPic1;
	
	private String  clearPic2;
	
	private String  clearPic3;
	
	//周边设备
	
	private int mile;
	
	private String maxx;
	
	private String maxy;
	
	private String minx;
	
	private String miny;
	
	

	public int getMile() {
		return mile;
	}

	public void setMile(int mile) {
		this.mile = mile;
	}

	public String getMaxx() {
		return maxx;
	}

	public void setMaxx(String maxx) {
		this.maxx = maxx;
	}

	public String getMaxy() {
		return maxy;
	}

	public void setMaxy(String maxy) {
		this.maxy = maxy;
	}

	public String getMinx() {
		return minx;
	}

	public void setMinx(String minx) {
		this.minx = minx;
	}

	public String getMiny() {
		return miny;
	}

	public void setMiny(String miny) {
		this.miny = miny;
	}

	public String getXlName() {
		return xlName;
	}

	public void setXlName(String xlName) {
		this.xlName = xlName;
	}

	public int getIsPass() {
		return isPass;
	}

	public void setIsPass(int isPass) {
		this.isPass = isPass;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEliminationDes() {
		return eliminationDes;
	}

	public void setEliminationDes(String eliminationDes) {
		this.eliminationDes = eliminationDes;
	}

	public String getClearPic1() {
		return clearPic1;
	}

	public void setClearPic1(String clearPic1) {
		this.clearPic1 = clearPic1;
	}

	public String getClearPic2() {
		return clearPic2;
	}

	public void setClearPic2(String clearPic2) {
		this.clearPic2 = clearPic2;
	}

	public String getClearPic3() {
		return clearPic3;
	}

	public void setClearPic3(String clearPic3) {
		this.clearPic3 = clearPic3;
	}

	public int getDefectReason() {
		return defectReason;
	}

	public int getDefectCategory() {
		return defectCategory;
	}

	public void setDefectCategory(int defectCategory) {
		this.defectCategory = defectCategory;
	}

	public void setDefectReason(int defectReason) {
		this.defectReason = defectReason;
	}

	public String getDefectCause() {
		return defectCause;
	}

	public void setDefectCause(String defectCause) {
		this.defectCause = defectCause;
	}

	public String getProposalElimScheme() {
		return proposalElimScheme;
	}

	public void setProposalElimScheme(String proposalElimScheme) {
		this.proposalElimScheme = proposalElimScheme;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getDefectCode() {
		return defectCode;
	}

	public void setDefectCode(String defectCode) {
		this.defectCode = defectCode;
	}

	
	public int getDefectSource() {
		return defectSource;
	}

	public String getDefectClass() {
		return defectClass;
	}

	public void setDefectClass(String defectClass) {
		this.defectClass = defectClass;
	}

	public void setDefectSource(int defectSource) {
		this.defectSource = defectSource;
	}

	
	public String getDefectContent() {
		return defectContent;
	}

	public void setDefectContent(String defectContent) {
		this.defectContent = defectContent;
	}

	public String getDefectContentDesc() {
		return defectContentDesc;
	}

	public void setDefectContentDesc(String defectContentDesc) {
		this.defectContentDesc = defectContentDesc;
	}

	public String getFindPersonId() {
		return findPersonId;
	}

	public void setFindPersonId(String findPersonId) {
		this.findPersonId = findPersonId;
	}

	public String getFindDate() {
		return findDate;
	}

	public void setFindDate(String findDate) {
		this.findDate = findDate;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	

	public String getDefectLocation() {
		return defectLocation;
	}

	public void setDefectLocation(String defectLocation) {
		this.defectLocation = defectLocation;
	}

	public String getDefectPic1() {
		return defectPic1;
	}

	public void setDefectPic1(String defectPic1) {
		this.defectPic1 = defectPic1;
	}

	public String getDefectPic2() {
		return defectPic2;
	}

	public void setDefectPic2(String defectPic2) {
		this.defectPic2 = defectPic2;
	}

	public String getDefectPic3() {
		return defectPic3;
	}

	public void setDefectPic3(String defectPic3) {
		this.defectPic3 = defectPic3;
	}

	public String getInteRid() {
		return inteRid;
	}

	public void setInteRid(String inteRid) {
		this.inteRid = inteRid;
	}

	public String getXlinteRid() {
		return xlinteRid;
	}

	public void setXlinteRid(String xlinteRid) {
		this.xlinteRid = xlinteRid;
	}

	public String getVoltageLevel() {
		return voltageLevel;
	}

	public void setVoltageLevel(String voltageLevel) {
		this.voltageLevel = voltageLevel;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("voltageLevel", voltageLevel);
		params.put("xlinteRid", xlinteRid);
		params.put("inteRid", inteRid);
		params.put("companyCode", companyCode);
		params.put("defectCode", defectCode);
		params.put("defectClass", defectClass);
		params.put("defectSource", defectSource);
		params.put("defectReason", defectReason);
		params.put("defectContent", defectContent);
		params.put("defectContentDesc", defectContentDesc);
		params.put("findPersonId", findPersonId);
		params.put("findDate", findDate);
		params.put("longitude", longitude);
		params.put("latitude", latitude);
		params.put("state", state);
		params.put("defectLocation", defectLocation);
		params.put("defectPic1", defectPic1);
		params.put("defectPic2", defectPic2);
		params.put("defectPic3", defectPic3);
		params.put("proposalElimScheme", proposalElimScheme);
		params.put("defectCause", defectCause);
		params.put("defectCategory", defectCategory);
		params.put("xlName", xlName);
		params.put("isPass", isPass);
		params.put("remarks", remarks);
		params.put("eliminationDes", eliminationDes);
		params.put("clearPic1", clearPic1);
		params.put("clearPic2", clearPic2);
		params.put("clearPic3", clearPic3);
		params.put("mile", mile);
		params.put("maxx", maxx);
		params.put("maxy", maxy);
		params.put("minx", minx);
		params.put("miny", miny);
		
		params.put("gtName", gtName);
		params.put("picCode", picCode);
		params.put("picCode2", picCode2);
		
		params.put("searchName", searchName);
		
		return params;
	}

	@Override
	protected void doPostRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getGenerateKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
