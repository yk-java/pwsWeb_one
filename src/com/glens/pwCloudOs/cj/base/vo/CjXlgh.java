package com.glens.pwCloudOs.cj.base.vo;

public class CjXlgh {
	private String gtCollectId;

	private String xlCollectId;

	private String gh;

	public String getGtCollectId() {
		return gtCollectId;
	}

	public void setGtCollectId(String gtCollectId) {
		this.gtCollectId = gtCollectId == null ? null : gtCollectId.trim();
	}

	public String getXlCollectId() {
		return xlCollectId;
	}

	public void setXlCollectId(String xlCollectId) {
		this.xlCollectId = xlCollectId == null ? null : xlCollectId.trim();
	}

	public String getGh() {
		return gh;
	}

	public void setGh(String gh) {
		this.gh = gh == null ? null : gh.trim();
	}
}