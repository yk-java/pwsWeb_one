package com.glens.pwCloudOs.cj.base.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.pwCloudOs.cj.base.vo.CjXlgh;

@ValueObjectProcessor(clazz = "com.glens.pwCloudOs.cj.base.vo.CjPole")
public class CjPoleForm extends ControllerForm {
	
	private String xlCollectId;
	
	private String collectId;
    
    private String gh;

    private String collectorCode;

    private String poleType;

    private String poleModel;

    private BigDecimal poleHeight;

    private BigDecimal jd;

    private BigDecimal wd;

    private Date sysCreateTime;

    private Date sysUpdateTime;

    private Date sysDeleteTime;

    private String sysProcessFlag;

    private Integer syncState;
    
    private String cjXlgh_tg_json;

	private String bdzCollectId;

	private String bdzAmname;

	private String xlAmname;

	private String city;

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

    public String getPoleType() {
        return poleType;
    }

    public void setPoleType(String poleType) {
        this.poleType = poleType == null ? null : poleType.trim();
    }

    public String getPoleModel() {
        return poleModel;
    }

    public void setPoleModel(String poleModel) {
        this.poleModel = poleModel == null ? null : poleModel.trim();
    }

    public BigDecimal getPoleHeight() {
        return poleHeight;
    }

    public void setPoleHeight(BigDecimal poleHeight) {
        this.poleHeight = poleHeight;
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

    public Integer getSyncState() {
        return syncState;
    }

    public void setSyncState(Integer syncState) {
        this.syncState = syncState;
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

	public String getCjXlgh_tg_json() {
		return cjXlgh_tg_json;
	}

	public void setCjXlgh_tg_json(String cjXlgh_tg_json) {
		this.cjXlgh_tg_json = cjXlgh_tg_json == null ? null : cjXlgh_tg_json.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getBdzCollectId() {
		return bdzCollectId;
	}

	public void setBdzCollectId(String bdzCollectId) {
		this.bdzCollectId = bdzCollectId == null ? null : bdzCollectId.trim();
	}

	public String getBdzAmname() {
		return bdzAmname;
	}

	public void setBdzAmname(String bdzAmname) {
		this.bdzAmname = bdzAmname == null ? null : bdzAmname.trim();
	}

	public String getXlAmname() {
		return xlAmname;
	}

	public void setXlAmname(String xlAmname) {
		this.xlAmname = xlAmname == null ? null : xlAmname.trim();
	}

	@Override
	protected Map doPreToMap() {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("collectId", collectId);
		params.put("xlCollectId", xlCollectId);
		params.put("xlAmname", xlAmname);
		params.put("poleType", poleType);	
		params.put("poleModel", poleModel);	
		params.put("city", city);	
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
