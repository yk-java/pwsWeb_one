package com.glens.pwCloudOs.cj.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.cj.base.dao.CjPoleDao;
import com.glens.pwCloudOs.cj.base.dao.CjXlghDao;
import com.glens.pwCloudOs.cj.base.vo.CjPole;
import com.glens.pwCloudOs.cj.base.vo.CjXlgh;
import com.glens.pwCloudOs.cj.base.web.CjPoleForm;

public class CjPoleService extends EAPAbstractService {

	public CjPole queryByXlGtCollectId(CjPoleForm form) {
		CjPoleDao cjDao = (CjPoleDao) this.dao;

		CjPole cjPole = cjDao.queryByXlGtCollectId(form);

		Map tgMap = new HashMap();
		tgMap.put("collectId", cjPole.getCollectId());
		List tgList = cjDao.queryForList(tgMap);
		if (tgList != null && tgList.size() > 0) {
			Iterator<CjPole> it = tgList.iterator();
			while (it.hasNext()) {
				CjPole _cPole = it.next();
				if (_cPole.getXlCollectId().equals(cjPole.getXlCollectId())
						&& _cPole.getCollectId().equals(cjPole.getCollectId())) {
					it.remove();
				}
			}
			cjPole.setCjXlgh_tg_json(JSON.toJSONString(tgList));
		} else {
			cjPole.setCjXlgh_tg_json("[]");
		}

		return cjPole;
	}

	public List queryCjPoleList(CjPoleForm form) {
		CjPoleDao cjDao = (CjPoleDao) this.dao;
		return cjDao.queryForList(form.toMap());
	}

	public Map saveCjPole(CjPole cjPole, String cjXlghJson) {
		CjPoleDao cjDao = (CjPoleDao) this.dao;
		CjXlghDao cjXlghDao = (CjXlghDao) EAPContext.getContext().getBean(
				"cjXlghDao");

		boolean cjPoleIsAdd = true;
		String collectId = UUID.randomUUID().toString().replaceAll("\\-", "");
		CjXlgh cjXlgh = null;
		List<CjXlgh> cjXlgh_tg_List = new ArrayList<CjXlgh>();
		List<CjXlgh> addCjXlghs = new ArrayList<CjXlgh>();

		if (cjXlghJson != null && !cjXlghJson.isEmpty()
				&& !cjXlghJson.equals("null")) {
			cjXlgh_tg_List = JSON.parseObject(cjXlghJson,
					new TypeReference<ArrayList<CjXlgh>>() {
					});
		}

		for (CjXlgh _cjXlgh : cjXlgh_tg_List) {
			if (_cjXlgh.getGtCollectId() != null
					&& !_cjXlgh.getGtCollectId().isEmpty()) {
				collectId = _cjXlgh.getGtCollectId();
				cjPoleIsAdd = false;
				break;
			}
		}

		if (cjPoleIsAdd) {
			cjPole.setCollectId(collectId);
			cjPole.setSysCreateTime(new Date());
			cjDao.insert(cjPole);
		} else {
			cjPole.setCollectId(collectId);
			cjPole.setSysUpdateTime(new Date());
			cjDao.update(cjPole);
		}

		cjXlgh = new CjXlgh();
		cjXlgh.setXlCollectId(cjPole.getXlCollectId());
		cjXlgh.setGtCollectId(collectId);
		cjXlgh.setGh(cjPole.getGh());
		addCjXlghs.add(cjXlgh);

		for (CjXlgh _cjXlgh : cjXlgh_tg_List) {
			if (_cjXlgh.getGtCollectId() == null
					|| _cjXlgh.getGtCollectId().isEmpty()) {
				cjXlgh = new CjXlgh();
				cjXlgh.setXlCollectId(_cjXlgh.getXlCollectId());
				cjXlgh.setGtCollectId(collectId);
				cjXlgh.setGh(_cjXlgh.getGh());
				addCjXlghs.add(cjXlgh);
			}
		}

		for (CjXlgh _cjXlgh : addCjXlghs) {
			cjXlghDao.insert(_cjXlgh);
		}

		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		result.put("collectId", collectId);
		return result;
	}

	public Map updateCjPole(CjPole cjPole, String cjXlghJson) {
		CjPoleDao cjDao = (CjPoleDao) this.dao;
		CjXlghDao cjXlghDao = (CjXlghDao) EAPContext.getContext().getBean(
				"cjXlghDao");

		String collectId = cjPole.getCollectId();
		// 删除所有该杆塔的，杆塔线路关系
		cjXlghDao.delByGtCollectId(cjPole.getCollectId());

		CjXlgh cjXlgh = null;
		List<CjXlgh> cjXlgh_tg_List = new ArrayList<CjXlgh>();
		List<CjXlgh> addCjXlghs = new ArrayList<CjXlgh>();

		if (cjXlghJson != null && !cjXlghJson.isEmpty()
				&& !cjXlghJson.equals("null")) {
			cjXlgh_tg_List = JSON.parseObject(cjXlghJson,
					new TypeReference<ArrayList<CjXlgh>>() {
					});
		}

		cjPole.setCollectId(collectId);
		cjPole.setSysUpdateTime(new Date());
		cjDao.update(cjPole);

		cjXlgh = new CjXlgh();
		cjXlgh.setXlCollectId(cjPole.getXlCollectId());
		cjXlgh.setGtCollectId(collectId);
		cjXlgh.setGh(cjPole.getGh());
		addCjXlghs.add(cjXlgh);

		for (CjXlgh _cjXlgh : cjXlgh_tg_List) {
			if (_cjXlgh.getGtCollectId() == null
					|| _cjXlgh.getGtCollectId().isEmpty()) {
				cjXlgh = new CjXlgh();
				cjXlgh.setXlCollectId(_cjXlgh.getXlCollectId());
				cjXlgh.setGtCollectId(collectId);
				cjXlgh.setGh(_cjXlgh.getGh());
				addCjXlghs.add(cjXlgh);
			}
		}

		for (CjXlgh _cjXlgh : addCjXlghs) {
			cjXlghDao.insert(_cjXlgh);
		}

		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map delCjPole(CjPole cjPole) {
		CjPoleDao cjDao = (CjPoleDao) this.dao;
		CjXlghDao cjXlghDao = (CjXlghDao) EAPContext.getContext().getBean(
				"cjXlghDao");

		CjXlgh cjXlgh = new CjXlgh();
		cjXlgh.setXlCollectId(cjPole.getXlCollectId());
		cjXlgh.setGtCollectId(cjPole.getCollectId());

		cjDao.delete(cjPole);
		cjXlghDao.delete(cjXlgh);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}
}
