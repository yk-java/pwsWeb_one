package com.glens.pwCloudOs.cj.base.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.cj.base.vo.CjBdz")
public class CjBdzForm extends ControllerForm { 
	private static final long serialVersionUID = 1L;
	private String collectId;

    private String collectorCode;

    private String interid;

    private String amname;

    private BigDecimal voltagelevel;

    private BigDecimal rl;

    private Date tyrq;

    private String bz;

    private String ydbyqbh;

    private String txbm;

    private BigDecimal sortnumber;

    private BigDecimal disorder;

    private String zcbm;

    private String zjly;

    private String jyfs;

    private String zcxz;

    private String zcdwmc;

    private String xz;

    private Integer zbnum;

    private BigDecimal zbrl;

    private BigDecimal jd;

    private BigDecimal wd;

    private String city;

    private Date sysCreateTime;

    private Date sysUpdateTime;

    private Date sysDeleteTime;

    private String sysProcessFlag;
    
	private MultipartFile excelFile;

    public MultipartFile getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(MultipartFile excelFile) {
		this.excelFile = excelFile;
	}

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

    public String getInterid() {
        return interid;
    }

    public void setInterid(String interid) {
        this.interid = interid == null ? null : interid.trim();
    }

    public String getAmname() {
        return amname;
    }

    public void setAmname(String amname) {
        this.amname = amname == null ? null : amname.trim();
    }

    public BigDecimal getVoltagelevel() {
        return voltagelevel;
    }

    public void setVoltagelevel(BigDecimal voltagelevel) {
        this.voltagelevel = voltagelevel;
    }

    public BigDecimal getRl() {
        return rl;
    }

    public void setRl(BigDecimal rl) {
        this.rl = rl;
    }

    public Date getTyrq() {
        return tyrq;
    }

    public void setTyrq(Date tyrq) {
        this.tyrq = tyrq;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public String getYdbyqbh() {
        return ydbyqbh;
    }

    public void setYdbyqbh(String ydbyqbh) {
        this.ydbyqbh = ydbyqbh == null ? null : ydbyqbh.trim();
    }

    public String getTxbm() {
        return txbm;
    }

    public void setTxbm(String txbm) {
        this.txbm = txbm == null ? null : txbm.trim();
    }

    public BigDecimal getSortnumber() {
        return sortnumber;
    }

    public void setSortnumber(BigDecimal sortnumber) {
        this.sortnumber = sortnumber;
    }

    public BigDecimal getDisorder() {
        return disorder;
    }

    public void setDisorder(BigDecimal disorder) {
        this.disorder = disorder;
    }

    public String getZcbm() {
        return zcbm;
    }

    public void setZcbm(String zcbm) {
        this.zcbm = zcbm == null ? null : zcbm.trim();
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

    public String getXz() {
        return xz;
    }

    public void setXz(String xz) {
        this.xz = xz == null ? null : xz.trim();
    }

    public Integer getZbnum() {
        return zbnum;
    }

    public void setZbnum(Integer zbnum) {
        this.zbnum = zbnum;
    }

    public BigDecimal getZbrl() {
        return zbrl;
    }

    public void setZbrl(BigDecimal zbrl) {
        this.zbrl = zbrl;
    }

    public BigDecimal getJd() {
        return jd;
    }

    public void setJd(BigDecimal jd) {
        this.jd = jd;
    }

    public BigDecimal getWd() {
        return wd;
    }

    public void setWd(BigDecimal wd) {
        this.wd = wd;
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
    
    @Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("amname", amname);
		params.put("interid", interid);
		params.put("city", city);
		params.put("collectId", collectId);	
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


}