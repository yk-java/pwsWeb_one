package com.glens.pwCloudOs.materielMg.goods.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.glens.eap.platform.core.beans.ValueObject;

public class GoodsCategory implements ValueObject {
    
    private Long rowid;

    
    private String goodsCode;

    
    private String goodsName;

    
    private String specCode;

    
    private String specName;

    
    private BigDecimal price;

    
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

    
    public String getSpecCode() {
        return specCode;
    }

    
    public void setSpecCode(String specCode) {
        this.specCode = specCode == null ? null : specCode.trim();
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
        this.sysProcessFlag = sysProcessFlag == null ? null : sysProcessFlag.trim();
    }
}