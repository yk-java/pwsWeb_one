package com.glens.pwCloudOs.cj.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.util.StringUtils;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.cj.base.dao.CjLineDao;
import com.glens.pwCloudOs.cj.base.vo.CjBdz;
import com.glens.pwCloudOs.cj.base.vo.CjLine;
import com.glens.pwCloudOs.cj.base.web.CjBdzForm;
import com.glens.pwCloudOs.cj.base.web.CjLineForm;

public class CjLineService extends EAPAbstractService {

	public PageBean queryCjLineForPage(CjLineForm form) {
		CjLineDao dao = (CjLineDao) this.dao;
		return dao.queryForPage(form.toMap(), form.getCurrentPage(),
				form.getPerpage());
	}

	public List queryCjLineList(CjLineForm form) {
		CjLineDao cjDao = (CjLineDao) this.dao;
		return cjDao.queryForList(form.toMap());
	}

	public Map saveCjLine(CjLine cjLine) {
		CjLineDao dao = (CjLineDao) this.dao;
		String collectId = UUID.randomUUID().toString().replaceAll("\\-", "");
		cjLine.setCollectId(collectId);
		cjLine.setSysCreateTime(new Date());

		Map result = new HashMap();
		if (dao.insert(cjLine)) {
			result.put("statusCode", ResponseConstants.OK);
			result.put("resultMsg", "返回结果成功");
			result.put("collectId", collectId);
		} else {
			result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
			result.put("resultMsg", "新增线路信息失败");
		}
		return result;
	}

	public Map updateCjLine(CjLine cjLine) {
		CjLineDao dao = (CjLineDao) this.dao;
		cjLine.setSysUpdateTime(new Date());
		dao.update(cjLine);

		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map delCjLine(CjLine cjLine) {
		CjLineDao dao = (CjLineDao) this.dao;
		dao.delete(cjLine);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map saveLine(CjLine line) {
		CjLineDao dao = (CjLineDao) this.dao;
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
	    String collectId=codeWorker.createCode("line");
	    line.setCollectId(collectId);
		Map<String, String> params= new HashMap<String, String>();
		params.put("city", line.getCity());
		params.put("amname", line.getAmname());
		params.put("bdzAmname", line.getBdzAmname());
		Map bdzInfo= new HashMap<String, String>();
		Map<String, String> result = new HashMap<String, String>();
		if (dao.queryForCount(params) > 0) {
			result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
			result.put("resultMsg", "新增线路信息失败，该变电站下已经存在该线路：" + line.getAmname()+"("+line.getBdzAmname()+"-"+line.getCity()+")");
		} else {
			String bdzKey=line.getCity()+"-"+line.getBdzAmname();
			String bdzCollectId=null; 
			if(bdzInfo.containsKey(bdzKey)){
				bdzCollectId=bdzInfo.get(bdzKey).toString();				
			}else{
				CjBdzService cjBdzService = (CjBdzService) EAPContext
			            .getContext().getBean("cjBdzService");
				CjBdzForm form=new CjBdzForm();
				form.setAmname(line.getBdzAmname());
				form.setCity(line.getCity());
				List<?> bdzlist=cjBdzService.queryCjBdzList(form);
				if(bdzlist.size()==1){
					CjBdz bdz=(CjBdz)bdzlist.get(0);
					bdzCollectId=bdz.getCollectId();
					
					bdzInfo.put(bdzKey, bdz.getCollectId());
				}else{
					result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
					result.put("resultMsg", "新增线路信息失败,所属变电站找不到"+ line.getAmname()+"("+line.getBdzAmname()+"-"+line.getCity()+")");
				}		
			}
			if(!StringUtils.isEmpty(bdzCollectId)){
				line.setBdzCollectId(bdzCollectId);
				if (dao.insert(line)) {
					result.put("statusCode", ResponseConstants.OK);
					result.put("resultMsg", "返回结果成功");
					result.put("collectId", collectId);			
				} else {
					result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
					result.put("resultMsg", "新增线路信息失败");
				}
			}
			
		}

		return result;
	}
}
