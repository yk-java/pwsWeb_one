package com.glens.pwCloudOs.addrLib.vo;

import org.springframework.web.multipart.MultipartFile;

import com.glens.eap.platform.core.beans.ValueObject;

public class AddPreliminaryAuditVo implements ValueObject {
	
	private Long rowid;
	private String formCode;//申请单号
	private String applicat;//申请人账号
	private String appOrgName;//申请单位名称
	private String appOrgCode;//申请单位组织机构代码
	private String appOrgTel;//申请人单位联系方式
	private String applicatName;//申请人
	private String applicatTel;//申请人联系方式
	private String placeName;//地名
	private String application;//地名使用说明书
	private String confirmation;//使用权出让确认书
	private MultipartFile applicationFile;
	private MultipartFile confirmationFile;
	private int acceptStatus;//受理核准  0 未审 1 受理 2 不受理
	private String acceptorCode;//受理核准人代码
	private String acceptorName;//受理核准人名称
	private String acceptTime;//受理时间
	private String acceptSuggest;//受理意见
	private String notice;//通知书
	private int auditStatus;//审核状态  0未审  1 通过 2 不通过
	private String auditSuggest;//审核意见
	private String auditorCode;//审核人代码
	private String auditorName;//审核人姓名
	private String auditTime;//审核时间
	private int auditStatus2;//审批状态  0未审  1 通过 2 不通过
	private String auditSuggest2;//审批意见
	private String auditorCode2;//审批人代码
	private String auditorName2;//审批人姓名
	private String auditTime2;//审批时间
	private String dispatch;//发文
	private String remarks;
	private String roleCode;
	private String sysCreateTime;
	
	public String getSysCreateTime() {
		return sysCreateTime;
	}
	public void setSysCreateTime(String sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}
	public MultipartFile getApplicationFile() {
		return applicationFile;
	}
	public void setApplicationFile(MultipartFile applicationFile) {
		this.applicationFile = applicationFile;
	}
	public MultipartFile getConfirmationFile() {
		return confirmationFile;
	}
	public void setConfirmationFile(MultipartFile confirmationFile) {
		this.confirmationFile = confirmationFile;
	}
	public String getAppOrgCode() {
		return appOrgCode;
	}
	public void setAppOrgCode(String appOrgCode) {
		this.appOrgCode = appOrgCode;
	}
	public String getApplicatTel() {
		return applicatTel;
	}
	public void setApplicatTel(String applicatTel) {
		this.applicatTel = applicatTel;
	}

	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public Long getRowid() {
		return rowid;
	}
	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}
	public String getFormCode() {
		return formCode;
	}
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}
	public String getApplicat() {
		return applicat;
	}
	public void setApplicat(String applicat) {
		this.applicat = applicat;
	}
	public String getAppOrgName() {
		return appOrgName;
	}
	public void setAppOrgName(String appOrgName) {
		this.appOrgName = appOrgName;
	}
	public String getApplicatName() {
		return applicatName;
	}
	public void setApplicatName(String applicatName) {
		this.applicatName = applicatName;
	}
	public String getAppOrgTel() {
		return appOrgTel;
	}
	public void setAppOrgTel(String appOrgTel) {
		this.appOrgTel = appOrgTel;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
	public int getAcceptStatus() {
		return acceptStatus;
	}
	public void setAcceptStatus(int acceptStatus) {
		this.acceptStatus = acceptStatus;
	}
	public String getAcceptorCode() {
		return acceptorCode;
	}
	public void setAcceptorCode(String acceptorCode) {
		this.acceptorCode = acceptorCode;
	}
	public String getAcceptorName() {
		return acceptorName;
	}
	public void setAcceptorName(String acceptorName) {
		this.acceptorName = acceptorName;
	}
	public String getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}
	public String getAcceptSuggest() {
		return acceptSuggest;
	}
	public void setAcceptSuggest(String acceptSuggest) {
		this.acceptSuggest = acceptSuggest;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditSuggest() {
		return auditSuggest;
	}
	public void setAuditSuggest(String auditSuggest) {
		this.auditSuggest = auditSuggest;
	}
	public String getAuditorCode() {
		return auditorCode;
	}
	public void setAuditorCode(String auditorCode) {
		this.auditorCode = auditorCode;
	}
	public String getAuditorName() {
		return auditorName;
	}
	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public int getAuditStatus2() {
		return auditStatus2;
	}
	public void setAuditStatus2(int auditStatus2) {
		this.auditStatus2 = auditStatus2;
	}
	public String getAuditSuggest2() {
		return auditSuggest2;
	}
	public void setAuditSuggest2(String auditSuggest2) {
		this.auditSuggest2 = auditSuggest2;
	}
	public String getAuditorCode2() {
		return auditorCode2;
	}
	public void setAuditorCode2(String auditorCode2) {
		this.auditorCode2 = auditorCode2;
	}
	public String getAuditorName2() {
		return auditorName2;
	}
	public void setAuditorName2(String auditorName2) {
		this.auditorName2 = auditorName2;
	}
	public String getAuditTime2() {
		return auditTime2;
	}
	public void setAuditTime2(String auditTime2) {
		this.auditTime2 = auditTime2;
	}
	public String getDispatch() {
		return dispatch;
	}
	public void setDispatch(String dispatch) {
		this.dispatch = dispatch;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
