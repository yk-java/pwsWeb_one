package com.glens.pwCloudOs.pe.baseMgr.rpMaterialBase.vo;

import com.glens.eap.platform.core.beans.ValueObject;

public class RpMaterialBaseVo implements ValueObject {
	
	private Long rowid;
	private String  materialBatchno;//耗材批次编号
	private String  materialName;
	private String materialtypeCode;
	private String materialtypeName;
	private String brand;
    private String modelNo;
    private Float price1;
    private Float price2;
    private Float total1;
    public Long getRowid() {
		return rowid;
	}
	public void setRowid(Long rowid) {
		this.rowid = rowid;
	}
	public String getMaterialBatchno() {
		return materialBatchno;
	}
	public void setMaterialBatchno(String materialBatchno) {
		this.materialBatchno = materialBatchno;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialtypeCode() {
		return materialtypeCode;
	}
	public void setMaterialtypeCode(String materialtypeCode) {
		this.materialtypeCode = materialtypeCode;
	}
	public String getMaterialtypeName() {
		return materialtypeName;
	}
	public void setMaterialtypeName(String materialtypeName) {
		this.materialtypeName = materialtypeName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public Float getPrice1() {
		return price1;
	}
	public void setPrice1(Float price1) {
		this.price1 = price1;
	}
	public Float getPrice2() {
		return price2;
	}
	public void setPrice2(Float price2) {
		this.price2 = price2;
	}
	public Float getTotal1() {
		return total1;
	}
	public void setTotal1(Float total1) {
		this.total1 = total1;
	}
	public Float getTotal2() {
		return total2;
	}
	public void setTotal2(Float total2) {
		this.total2 = total2;
	}
	public String getBill() {
		return bill;
	}
	public void setBill(String bill) {
		this.bill = bill;
	}
	public String getDatePurchase() {
		return datePurchase;
	}
	public void setDatePurchase(String datePurchase) {
		this.datePurchase = datePurchase;
	}
	public String getDateStorage() {
		return dateStorage;
	}
	public void setDateStorage(String dateStorage) {
		this.dateStorage = dateStorage;
	}
	public Integer getCountStorage() {
		return countStorage;
	}
	public void setCountStorage(Integer countStorage) {
		this.countStorage = countStorage;
	}
	public Integer getCurcountStorage() {
		return curcountStorage;
	}
	public void setCurcountStorage(Integer curcountStorage) {
		this.curcountStorage = curcountStorage;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	private Float total2;
    private String bill;
    private String datePurchase;
    private String dateStorage;
    private Integer  countStorage;
    private Integer curcountStorage;
    private String remarks;


}
