package com.glens.eap.sys.orgEmployee.employee.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class MdEmployee implements ValueObject {

	private Long rowid;
	private Long rowno;

	public Long getRowno() {
		return rowno;
	}

	public void setRowno(Long rowno) {
		this.rowno = rowno;
	}

	private String unitCode;

	private String unitName;
	private String proNo;
	private String proName;
	private String aboutProName;// 相关项目名称（参与项目、负责项目）

	private String employeecode;

	private String employeename;

	private String nickname;

	private String jobNo;

	private String sex;
	private String sexLabel;
    private String propertyName;
    
	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getSexLabel() {
		return sexLabel;
	}

	public void setSexLabel(String sexLabel) {
		this.sexLabel = sexLabel;
	}

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

	private String jobCode;
	private String jobName;
	private String jobPropertyCode;
	private String jobPropertyName;
	private Integer jobClass1;
	private Integer jobClass2;
	private String jobClass1Name;
	private String jobClass2Name;
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
	private String recordno;
	private String leaderCode;
	private String leaderName;
	
	private Integer salaryGrade;
	private Integer salaryLevel;
	private String prefix;

	
	public Integer getSalaryGrade() {
		return salaryGrade;
	}

	public void setSalaryGrade(Integer salaryGrade) {
		this.salaryGrade = salaryGrade;
	}

	public Integer getSalaryLevel() {
		return salaryLevel;
	}

	public void setSalaryLevel(Integer salaryLevel) {
		this.salaryLevel = salaryLevel;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
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

	public String getRecordno() {
		return recordno;
	}

	public void setRecordno(String recordno) {
		this.recordno = recordno;
	}

	public String getAboutProName() {
		return aboutProName;
	}

	public void setAboutProName(String aboutProName) {
		this.aboutProName = aboutProName;
	}

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getJobClass1Name() {
		return jobClass1Name;
	}

	public void setJobClass1Name(String jobClass1Name) {
		this.jobClass1Name = jobClass1Name;
	}

	public String getJobClass2Name() {
		return jobClass2Name;
	}

	public void setJobClass2Name(String jobClass2Name) {
		this.jobClass2Name = jobClass2Name;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobPropertyCode() {
		return jobPropertyCode;
	}

	public void setJobPropertyCode(String jobPropertyCode) {
		this.jobPropertyCode = jobPropertyCode;
	}

	public String getJobPropertyName() {
		return jobPropertyName;
	}

	public void setJobPropertyName(String jobPropertyName) {
		this.jobPropertyName = jobPropertyName;
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

	public String getContractPropertyName() {
		return contractPropertyName;
	}

	public void setContractPropertyName(String contractPropertyName) {
		this.contractPropertyName = contractPropertyName;
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

}