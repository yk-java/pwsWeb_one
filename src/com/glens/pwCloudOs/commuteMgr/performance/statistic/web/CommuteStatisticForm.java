package com.glens.pwCloudOs.commuteMgr.performance.statistic.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.web.ControllerForm;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.commuteMgr.performance.statistic.vo.CommuteStatisticVo")
public class CommuteStatisticForm extends ControllerForm {

	private Long rowid;
    private String companyCode;
    private String unitCode;
    private String employeecode;
    private String accountCode;
    private Date checkinTime;
    private Date checkoutTime;
    private Date sysCreateTime;
    private Date sysUpdateTime;
    private String checkinImg1;
    private String checkinImg2;
    private Date sysDeleteTime;
    private String checkoutImg1;
    private String checkoutImg2;
    private String sysProcessFlag;
    private String remarks;
    
    private String date;// daily date
    private String beginDate;
    private String endDate;
    private String searchName;
    
    private String proNo;
    
    private String jobCode;
    
    private String districtManager;
    
    public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
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

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public Date getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(Date checkoutTime) {
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
    
    
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	protected Map doPreToMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyCode", this.getCompanyCode());
		map.put("unitCode", this.getUnitCode());
		map.put("employeecode", this.getEmployeecode());
		map.put("accountCode", accountCode);
		map.put("proNo", proNo);
		map.put("date", date);
		map.put("searchName", searchName);
		map.put("jobCode", jobCode);
		map.put("districtManager", districtManager);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			if(this.getDate()!=null && this.getDate().trim().length()>0){
				map.put("beginTime", sdf.parse(this.getDate()+" 00:00:00"));
				map.put("endTime", sdf.parse(this.getDate()+" 23:59:59"));
			}
			if(this.getBeginDate()!=null && this.getBeginDate().trim().length()>0){
				map.put("beginTime", sdf.parse(this.getBeginDate()+" 00:00:00"));
			}
			if(this.getEndDate()!=null && this.getEndDate().trim().length()>0){
				map.put("endTime", sdf.parse(this.getEndDate()+" 23:59:59"));
			}
		} catch (ParseException e) {
		}
		//map.put("beginTime", );
		//map.put("endTime", );
		return map;
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

	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getDistrictManager() {
		return districtManager;
	}

	public void setDistrictManager(String districtManager) {
		this.districtManager = districtManager;
	}

	
}