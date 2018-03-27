package com.glens.pwCloudOs.commuteMgr.performance.digitalSignage.vo;

import java.util.Date;

import com.glens.eap.platform.core.beans.ValueObject;

public class CpCommuteCheck implements ValueObject {

	private Long rowid;
    private String companyCode;
    private String companyName;
    private String unitCode;
    private String unitName;
    private String employeecode;
    private String employeename;
    private String accountCode;
    private String jobNo;
    private String checkinTime;
    private String checkoutTime;
    private String checkinImg1;
    private String checkinImg2;
    private String checkoutImg1;
    private String checkoutImg2;
    private Integer reportCount;
    private Integer checkPointCount;
    private Integer signNormalCount;
    private Integer signAbnormalCount;
    private Float reportPerOfPass;
    private Float signPerOfPass;
    private Date sysCreateTime;
    private Date sysUpdateTime;
    private Date sysDeleteTime;
    private String sysProcessFlag;
    private String remarks;
    private String judgingCode;
    private String judgingName;
    private String judgingDescr;
    private String checkDate;
    
    public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getJudgingCode() {
		return judgingCode;
	}

	public void setJudgingCode(String judgingCode) {
		this.judgingCode = judgingCode;
	}

	public String getJudgingName() {
		return judgingName;
	}

	public void setJudgingName(String judgingName) {
		this.judgingName = judgingName;
	}

	public String getJudgingDescr() {
		return judgingDescr;
	}

	public void setJudgingDescr(String judgingDescr) {
		this.judgingDescr = judgingDescr;
	}

	public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
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

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode == null ? null : accountCode.trim();
    }

    public String getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime;
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

    public String getCheckinImg1() {
        return checkinImg1;
    }

    public void setCheckinImg1(String checkinImg1) {
        this.checkinImg1 = checkinImg1 == null ? null : checkinImg1.trim();
    }

    public String getCheckinImg2() {
        return checkinImg2;
    }

    public void setCheckinImg2(String checkinImg2) {
        this.checkinImg2 = checkinImg2 == null ? null : checkinImg2.trim();
    }

    public Date getSysDeleteTime() {
        return sysDeleteTime;
    }

    public void setSysDeleteTime(Date sysDeleteTime) {
        this.sysDeleteTime = sysDeleteTime;
    }

    public String getCheckoutImg1() {
        return checkoutImg1;
    }

    public void setCheckoutImg1(String checkoutImg1) {
        this.checkoutImg1 = checkoutImg1 == null ? null : checkoutImg1.trim();
    }

    public String getCheckoutImg2() {
        return checkoutImg2;
    }

    public void setCheckoutImg2(String checkoutImg2) {
        this.checkoutImg2 = checkoutImg2 == null ? null : checkoutImg2.trim();
    }

    public String getSysProcessFlag() {
        return sysProcessFlag;
    }

    public void setSysProcessFlag(String sysProcessFlag) {
        this.sysProcessFlag = sysProcessFlag == null ? null : sysProcessFlag.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public Integer getReportCount() {
		return reportCount;
	}

	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}

	public Integer getCheckPointCount() {
		return checkPointCount;
	}

	public void setCheckPointCount(Integer checkPointCount) {
		this.checkPointCount = checkPointCount;
	}

	public Float getReportPerOfPass() {
		return reportPerOfPass;
	}

	public void setReportPerOfPass(Float reportPerOfPass) {
		this.reportPerOfPass = reportPerOfPass;
	}

	public Float getSignPerOfPass() {
		return signPerOfPass;
	}

	public void setSignPerOfPass(Float signPerOfPass) {
		this.signPerOfPass = signPerOfPass;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public Integer getSignNormalCount() {
		return signNormalCount;
	}

	public void setSignNormalCount(Integer signNormalCount) {
		this.signNormalCount = signNormalCount;
	}

	public Integer getSignAbnormalCount() {
		return signAbnormalCount;
	}

	public void setSignAbnormalCount(Integer signAbnormalCount) {
		this.signAbnormalCount = signAbnormalCount;
	}
    
    
}