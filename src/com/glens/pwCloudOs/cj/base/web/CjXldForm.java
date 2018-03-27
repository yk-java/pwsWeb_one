package com.glens.pwCloudOs.cj.base.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.cj.base.vo.CjXld")
public class CjXldForm extends ControllerForm {

	private String collectId;

	private String collectorCode;

	private BigDecimal dj;

	private String amname;

	private String dxlx;

	private String xlCollectId;

	private String xlAmname;

	private String type;

	private String fromId;

	private String fromAmname;

	private BigDecimal fromJd;

	private BigDecimal fromWd;

	private Integer fromType;

	private String fromTypeName;

	private String toId;

	private String toAmname;

	private BigDecimal toJd;

	private BigDecimal toWd;

	private Integer toType;

	private String toTypeName;

	private Date sysCreateTime;

	private Date sysUpdateTime;

	private Date sysDeleteTime;

	private String sysProcessFlag;

	private Integer syncState;

	public String getCollectId() {
		return collectId;
	}

	public void setCollectId(String collectId) {
		this.collectId = collectId == null ? null : collectId.trim();
	}

	public String getCollectorCode() {
		return collectorCode;
	}

	public void setCollectorCode(String collectorCode) {
		this.collectorCode = collectorCode == null ? null : collectorCode
				.trim();
	}

	public BigDecimal getDj() {
		return dj;
	}

	public void setDj(BigDecimal dj) {
		this.dj = dj;
	}

	public String getAmname() {
		return amname;
	}

	public void setAmname(String amname) {
		this.amname = amname == null ? null : amname.trim();
	}

	public String getDxlx() {
		return dxlx;
	}

	public void setDxlx(String dxlx) {
		this.dxlx = dxlx == null ? null : dxlx.trim();
	}

	public String getXlCollectId() {
		return xlCollectId;
	}

	public void setXlCollectId(String xlCollectId) {
		this.xlCollectId = xlCollectId == null ? null : xlCollectId.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId == null ? null : fromId.trim();
	}

	public Integer getFromType() {
		return fromType;
	}

	public void setFromType(Integer fromType) {
		this.fromType = fromType;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId == null ? null : toId.trim();
	}

	public Integer getToType() {
		return toType;
	}

	public void setToType(Integer toType) {
		this.toType = toType;
	}

	public Date getSysCreateTime() {
		return sysCreateTime;
	}

	public void setSysCreateTime(Date sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	public Date getSysUpdateTime() {
		return sysUpdateTime;
	}

	public void setSysUpdateTime(Date sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	public Date getSysDeleteTime() {
		return sysDeleteTime;
	}

	public void setSysDeleteTime(Date sysDeleteTime) {
		this.sysDeleteTime = sysDeleteTime;
	}

	public String getSysProcessFlag() {
		return sysProcessFlag;
	}

	public void setSysProcessFlag(String sysProcessFlag) {
		this.sysProcessFlag = sysProcessFlag == null ? null : sysProcessFlag
				.trim();
	}

	public Integer getSyncState() {
		return syncState;
	}

	public void setSyncState(Integer syncState) {
		this.syncState = syncState;
	}

	public String getXlAmname() {
		return xlAmname;
	}

	public void setXlAmname(String xlAmname) {
		this.xlAmname = xlAmname == null ? null : xlAmname.trim();
	}

	public String getFromAmname() {
		return fromAmname;
	}

	public void setFromAmname(String fromAmname) {
		this.fromAmname = fromAmname == null ? null : fromAmname.trim();
	}

	public String getToAmname() {
		return toAmname;
	}

	public void setToAmname(String toAmname) {
		this.toAmname = toAmname == null ? null : toAmname.trim();
	}

	public BigDecimal getFromJd() {
		return fromJd;
	}

	public void setFromJd(BigDecimal fromJd) {
		this.fromJd = fromJd;
	}

	public BigDecimal getFromWd() {
		return fromWd;
	}

	public void setFromWd(BigDecimal fromWd) {
		this.fromWd = fromWd;
	}

	public BigDecimal getToJd() {
		return toJd;
	}

	public void setToJd(BigDecimal toJd) {
		this.toJd = toJd;
	}

	public BigDecimal getToWd() {
		return toWd;
	}

	public void setToWd(BigDecimal toWd) {
		this.toWd = toWd;
	}

	public String getFromTypeName() {
		return fromTypeName;
	}

	public void setFromTypeName(String fromTypeName) {
		this.fromTypeName = fromTypeName == null ? null : fromTypeName.trim();
	}

	public String getToTypeName() {
		return toTypeName;
	}

	public void setToTypeName(String toTypeName) {
		this.toTypeName = toTypeName == null ? null : toTypeName.trim();
	}

	@Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("collectId", collectId);
		params.put("amname", amname);
		params.put("dxlx", dxlx);
		params.put("type", type);
		params.put("xlCollectId", xlCollectId);
		params.put("xlAmname", xlAmname);
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
