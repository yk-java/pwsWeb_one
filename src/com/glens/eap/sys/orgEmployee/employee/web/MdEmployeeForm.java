package com.glens.eap.sys.orgEmployee.employee.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.eap.sys.orgEmployee.employee.vo.MdEmployee")
public class MdEmployeeForm extends ControllerForm {

	private Long rowid;

	private String unitCode;

	private String employeecode;

	private String employeename;

	private String nickname;

	private String jobNo;

	private String sex;

	private String nativePlace;

	private Integer marital;

	private String idcard;

	private String birthday;

	private String addr;

	private String tel;

	private String officeTel;

	private String mobilephone1;

	private String mobilephone2;

	private String mail;

	private String degree;

	private String majorDegree;

	private String graduateTime;

	private String graduateSchool;

	private String headPhotokey;

	private String facePhotokey;

	private String gradeclasscode;

	private String emergencyContactorName;

	private String emergencyContactorPhone;

	private String emergencyContactorRelation;

	private String remarks;

	private String searchName;

	private String jobCode;
	private String jobName;
	private String jobPropertyCode;
	private String jobPropertyName;
	private Integer jobClass1;
	private Integer jobClass2;
	private String recruitPlace;
	private String officeDate;
	private String workDate;
	private String contractPropertyCode;
	private String contractPropertyName;
	private String contractDateS;
	private String contractDateE;
	private String bankAccount;
	private String bankName;
	private String weal1Status;
	private String weal2Status;
	private String weal3Status;
	private Integer workStatus;
	private String leaveDate;
	private String leaveExplain;
	private String fromDate;
	private String toDate;
	private String recordno;
	private String companyCode;
	private MultipartFile excelFile;
	private String leaderCode;
	private String leaderName;
	
	private String propertyName;

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	// 微信号
	private String weixinid;

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public String getLeaderCode() {
		return leaderCode;
	}

	public void setLeaderCode(String leaderCode) {
		this.leaderCode = leaderCode;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public MultipartFile getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(MultipartFile excelFile) {
		this.excelFile = excelFile;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getRecordno() {
		return recordno;
	}

	public void setRecordno(String recordno) {
		this.recordno = recordno;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Integer getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(Integer workStatus) {
		this.workStatus = workStatus;
	}

	public String getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getLeaveExplain() {
		return leaveExplain;
	}

	public void setLeaveExplain(String leaveExplain) {
		this.leaveExplain = leaveExplain;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobPropertyName() {
		return jobPropertyName;
	}

	public void setJobPropertyName(String jobPropertyName) {
		this.jobPropertyName = jobPropertyName;
	}

	public String getContractPropertyName() {
		return contractPropertyName;
	}

	public void setContractPropertyName(String contractPropertyName) {
		this.contractPropertyName = contractPropertyName;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobPropertyCode() {
		return jobPropertyCode;
	}

	public void setJobPropertyCode(String jobPropertyCode) {
		this.jobPropertyCode = jobPropertyCode;
	}

	public Integer getJobClass1() {
		return jobClass1;
	}

	public void setJobClass1(Integer jobClass1) {
		this.jobClass1 = jobClass1;
	}

	public Integer getJobClass2() {
		return jobClass2;
	}

	public void setJobClass2(Integer jobClass2) {
		this.jobClass2 = jobClass2;
	}

	public String getRecruitPlace() {
		return recruitPlace;
	}

	public void setRecruitPlace(String recruitPlace) {
		this.recruitPlace = recruitPlace;
	}

	public String getOfficeDate() {
		return officeDate;
	}

	public void setOfficeDate(String officeDate) {
		this.officeDate = officeDate;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getContractPropertyCode() {
		return contractPropertyCode;
	}

	public void setContractPropertyCode(String contractPropertyCode) {
		this.contractPropertyCode = contractPropertyCode;
	}

	public String getContractDateS() {
		return contractDateS;
	}

	public void setContractDateS(String contractDateS) {
		this.contractDateS = contractDateS;
	}

	public String getContractDateE() {
		return contractDateE;
	}

	public void setContractDateE(String contractDateE) {
		this.contractDateE = contractDateE;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getWeal1Status() {
		return weal1Status;
	}

	public void setWeal1Status(String weal1Status) {
		this.weal1Status = weal1Status;
	}

	public String getWeal2Status() {
		return weal2Status;
	}

	public void setWeal2Status(String weal2Status) {
		this.weal2Status = weal2Status;
	}

	public String getWeal3Status() {
		return weal3Status;
	}

	public void setWeal3Status(String weal3Status) {
		this.weal3Status = weal3Status;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode == null ? null : unitCode.trim();
	}

	public String getEmployeecode() {
		return employeecode;
	}

	public void setEmployeecode(String employeecode) {
		this.employeecode = employeecode == null ? null : employeecode.trim();
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename == null ? null : employeename.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace == null ? null : nativePlace.trim();
	}

	public Integer getMarital() {
		return marital;
	}

	public void setMarital(Integer marital) {
		this.marital = marital;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard == null ? null : idcard.trim();
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr == null ? null : addr.trim();
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel == null ? null : officeTel.trim();
	}

	public String getMobilephone1() {
		return mobilephone1;
	}

	public void setMobilephone1(String mobilephone1) {
		this.mobilephone1 = mobilephone1 == null ? null : mobilephone1.trim();
	}

	public String getMobilephone2() {
		return mobilephone2;
	}

	public void setMobilephone2(String mobilephone2) {
		this.mobilephone2 = mobilephone2 == null ? null : mobilephone2.trim();
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail == null ? null : mail.trim();
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree == null ? null : degree.trim();
	}

	public String getMajorDegree() {
		return majorDegree;
	}

	public void setMajorDegree(String majorDegree) {
		this.majorDegree = majorDegree == null ? null : majorDegree.trim();
	}

	public String getGraduateTime() {
		return graduateTime;
	}

	public void setGraduateTime(String graduateTime) {
		this.graduateTime = graduateTime == null ? null : graduateTime.trim();
	}

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool == null ? null : graduateSchool
				.trim();
	}

	public String getHeadPhotokey() {
		return headPhotokey;
	}

	public void setHeadPhotokey(String headPhotokey) {
		this.headPhotokey = headPhotokey == null ? null : headPhotokey.trim();
	}

	public String getFacePhotokey() {
		return facePhotokey;
	}

	public void setFacePhotokey(String facePhotokey) {
		this.facePhotokey = facePhotokey == null ? null : facePhotokey.trim();
	}

	public String getGradeclasscode() {
		return gradeclasscode;
	}

	public void setGradeclasscode(String gradeclasscode) {
		this.gradeclasscode = gradeclasscode == null ? null : gradeclasscode
				.trim();
	}

	public String getEmergencyContactorName() {
		return emergencyContactorName;
	}

	public void setEmergencyContactorName(String emergencyContactorName) {
		this.emergencyContactorName = emergencyContactorName == null ? null
				: emergencyContactorName.trim();
	}

	public String getEmergencyContactorPhone() {
		return emergencyContactorPhone;
	}

	public void setEmergencyContactorPhone(String emergencyContactorPhone) {
		this.emergencyContactorPhone = emergencyContactorPhone == null ? null
				: emergencyContactorPhone.trim();
	}

	public String getEmergencyContactorRelation() {
		return emergencyContactorRelation;
	}

	public void setEmergencyContactorRelation(String emergencyContactorRelation) {
		this.emergencyContactorRelation = emergencyContactorRelation == null ? null
				: emergencyContactorRelation.trim();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	@Override
	protected Map doPreToMap() {
		Map map = new HashMap();
		map.put("unitCode", unitCode);
		map.put("searchName", searchName);
		map.put("workStatus", workStatus);
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		map.put("companyCode", companyCode);
		return map;
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

	public ValueObject toVo() {

		ValueObject vo = createVo();
		try {
			PropertyUtils.copyProperties(vo, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
}