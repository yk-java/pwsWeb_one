package com.glens.pwCloudOs.materielMg.goods.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.glens.eap.platform.core.beans.ValueObject;

public class GoodsDetail implements ValueObject {

	private Long rowid;

	private String goodsCode;

	private String goodsName;

	private String specCode;

	private String specName;

	private BigDecimal price;

	private Integer amount;

	private String gtype;

	private String goodsType;

	private String goodsTime;

	private String pic1;

	private String pic2;

	private String pic3;

	private String storeCode;

	private Date sysCreateTime;

	private Date sysUpdateTime;

	private Date sysDeleteTime;

	private String sysProcessFlag;

	public Long getRowid() {
		return rowid;
	}

	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode == null ? null : goodsCode.trim();
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName == null ? null : goodsName.trim();
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName == null ? null : specName.trim();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getGtype() {
		return gtype;
	}

	public void setGtype(String gtype) {
		this.gtype = gtype == null ? null : gtype.trim();
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType == null ? null : goodsType.trim();
	}

	public String getGoodsTime() {
		return goodsTime;
	}

	public void setGoodsTime(String goodsTime) {
		this.goodsTime = goodsTime == null ? null : goodsTime.trim();
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1 == null ? null : pic1.trim();
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2 == null ? null : pic2.trim();
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3 == null ? null : pic3.trim();
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode == null ? null : storeCode.trim();
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

	public String getSpecCode() {
		return specCode;
	}

	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}

}