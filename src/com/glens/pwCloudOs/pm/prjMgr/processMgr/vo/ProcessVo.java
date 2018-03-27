package com.glens.pwCloudOs.pm.prjMgr.processMgr.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class ProcessVo implements ValueObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 523208637003317829L;
	
	private Integer viewtype;
	private String viewtypeName;
	private String content;
	private String proposer;
	private String unitName;
	private String proName;
	private String submitTime;
	private Integer type;
	private Long rowid;
	public Integer getViewtype() {
		return viewtype;
	}
	public void setViewtype(Integer viewtype) {
		this.viewtype = viewtype;
	}
	public String getViewtypeName() {
		return viewtypeName;
	}
	public void setViewtypeName(String viewtypeName) {
		this.viewtypeName = viewtypeName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getProposer() {
		return proposer;
	}
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getRowid() {
		return rowid;
	}
	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}
	
}
