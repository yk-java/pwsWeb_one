package com.glens.pwCloudOs.cj.base.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.glens.eap.platform.core.beans.ValueObject;

public class CjLine  implements ValueObject{
    private String collectId;

    private String collectorCode;

    private String bdzCollectId;
    
    private String bdzAmname;

    private String interid;

    private String code;

    private String amname;

    private Long voltagelevel;

    private Date productdate;

    private Date updatetime;

    private String area;

    private String tyrq;

    private String zjly;

    private String jyfs;

    private String zcxz;

    private String zcdwmc;

    private String xllx;

    private String sjdl;

    private String bz;

    private BigDecimal xlcdTz;

    private BigDecimal xlcdMap;

    private String proNo;
    
    private String proName;

    private String city;

    private Date sysCreateTime;

    private Date sysUpdateTime;

    private Date sysDeleteTime;

    private String sysProcessFlag;

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
        this.collectorCode = collectorCode == null ? null : collectorCode.trim();
    }

    public String getBdzCollectId() {
        return bdzCollectId;
    }

    public void setBdzCollectId(String bdzCollectId) {
        this.bdzCollectId = bdzCollectId == null ? null : bdzCollectId.trim();
    }

    public String getInterid() {
        return interid;
    }

    public void setInterid(String interid) {
        this.interid = interid == null ? null : interid.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getAmname() {
        return amname;
    }

    public void setAmname(String amname) {
        this.amname = amname == null ? null : amname.trim();
    }

    public Long getVoltagelevel() {
        return voltagelevel;
    }

    public void setVoltagelevel(Long voltagelevel) {
        this.voltagelevel = voltagelevel;
    }

    public Date getProductdate() {
        return productdate;
    }

    public void setProductdate(Date productdate) {
        this.productdate = productdate;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getTyrq() {
        return tyrq;
    }

    public void setTyrq(String tyrq) {
        this.tyrq = tyrq;
    }

    public String getZjly() {
        return zjly;
    }

    public void setZjly(String zjly) {
        this.zjly = zjly == null ? null : zjly.trim();
    }

    public String getJyfs() {
        return jyfs;
    }

    public void setJyfs(String jyfs) {
        this.jyfs = jyfs == null ? null : jyfs.trim();
    }

    public String getZcxz() {
        return zcxz;
    }

    public void setZcxz(String zcxz) {
        this.zcxz = zcxz == null ? null : zcxz.trim();
    }

    public String getZcdwmc() {
        return zcdwmc;
    }

    public void setZcdwmc(String zcdwmc) {
        this.zcdwmc = zcdwmc == null ? null : zcdwmc.trim();
    }

    public String getXllx() {
        return xllx;
    }

    public void setXllx(String xllx) {
        this.xllx = xllx == null ? null : xllx.trim();
    }

    public String getSjdl() {
        return sjdl;
    }

    public void setSjdl(String sjdl) {
        this.sjdl = sjdl == null ? null : sjdl.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public BigDecimal getXlcdTz() {
        return xlcdTz;
    }

    public void setXlcdTz(BigDecimal xlcdTz) {
        this.xlcdTz = xlcdTz;
    }

    public BigDecimal getXlcdMap() {
        return xlcdMap;
    }

    public void setXlcdMap(BigDecimal xlcdMap) {
        this.xlcdMap = xlcdMap;
    }

    public String getProNo() {
        return proNo;
    }

    public void setProNo(String proNo) {
        this.proNo = proNo == null ? null : proNo.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
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

	public String getBdzAmname() {
		return bdzAmname;
	}

	public void setBdzAmname(String bdzAmname) {
        this.bdzAmname = bdzAmname == null ? null : bdzAmname.trim();
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
	}
}