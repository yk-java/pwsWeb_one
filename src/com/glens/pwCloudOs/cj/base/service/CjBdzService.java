package com.glens.pwCloudOs.cj.base.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants; 
import com.glens.pwCloudOs.cj.base.dao.CjBdzDao;
import com.glens.pwCloudOs.cj.base.vo.CjBdz;
import com.glens.pwCloudOs.cj.base.web.CjBdzForm; 

public class CjBdzService extends EAPAbstractService{
	public PageBean queryCjBdzForPage(CjBdzForm form) {
		CjBdzDao dao = (CjBdzDao) this.dao;
		return dao.queryForPage(form.toMap(), form.getCurrentPage(),
				form.getPerpage());
	}

	public List<?> queryCjBdzList(CjBdzForm form) {
		CjBdzDao cjDao = (CjBdzDao) this.dao;
		return cjDao.queryForList(form);
	}
	public Map<String, String> saveBdz(CjBdz bdz) {
		CjBdzDao dao = (CjBdzDao) this.dao;
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
	    String collectId=codeWorker.createCode("bdz");
		bdz.setCollectId(collectId);
		Map<String, String> params= new HashMap<String, String>();
		params.put("city", bdz.getCity());
		params.put("amname", bdz.getAmname());
	 
		Map<String, String> result = new HashMap<String, String>();
		if (dao.queryForCount(params) > 0) {
			result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
			result.put("resultMsg", "新增变电站信息失败，该区域已经存在该变电站：" + bdz.getAmname()+"("+bdz.getCity()+")");
		} else {
			
			if (dao.insert(bdz)) {
				result.put("statusCode", ResponseConstants.OK);
				result.put("resultMsg", "返回结果成功");
				result.put("collectId", collectId);			
			} else {
				result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
				result.put("resultMsg", "新增变电站信息失败");
			}
		}

		return result;
	}
	

	
}
