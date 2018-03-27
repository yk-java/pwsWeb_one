package com.glens.pwCloudOs.cj.base.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.pwCloudOs.cj.base.dao.CjBdzLineDeviceDao;
import com.glens.pwCloudOs.cj.base.dao.CjLineDao;
import com.glens.pwCloudOs.cj.base.dao.CjXldDao;
import com.glens.pwCloudOs.cj.base.vo.CjBdzLineDevice;
import com.glens.pwCloudOs.cj.base.vo.CjLine;
import com.glens.pwCloudOs.cj.base.vo.CjXld;
import com.glens.pwCloudOs.cj.base.web.CjXldForm;

public class CjXldService extends EAPAbstractService {

	public PageBean queryCjXldForPage(CjXldForm form) {
		CjXldDao dao = (CjXldDao) this.dao;
		CjLineDao cjLineDao = (CjLineDao) EAPContext.getContext().getBean(
				"cjLineDao");
		CjBdzLineDeviceDao cjBdzLineDeviceDao = (CjBdzLineDeviceDao) EAPContext
				.getContext().getBean("cjBdzLineDeviceDao");
		PageBean page = dao.queryForPage(form.toMap(), form.getCurrentPage(),
				form.getPerpage());

		for (CjXld e : (List<CjXld>) page.getList()) {
			String xlCollectId = e.getXlCollectId();
			String fromCollectId = e.getFromId();
			Integer fromTypeNumber = e.getFromType();
			String toCollectId = e.getToId();
			Integer toTypeNumber = e.getToType();

			if (xlCollectId != null && !xlCollectId.isEmpty()) {
				CjLine cjLine = (CjLine) cjLineDao.findById(xlCollectId);
				e.setXlAmname(cjLine.getAmname());
			}

			if (fromCollectId != null && !fromCollectId.isEmpty()) {
				Map fromDeviceParameter = new HashMap();
				fromDeviceParameter.put("collectId", fromCollectId);
				fromDeviceParameter.put("typeNumber", fromTypeNumber);
				CjBdzLineDevice fromDevice = cjBdzLineDeviceDao
						.queryCjDeviceByCollectIdAndTypeNumber(fromDeviceParameter);
				if (fromDevice != null) {
					e.setFromAmname(fromDevice.getAmname());
					e.setFromJd(fromDevice.getJd());
					e.setFromWd(fromDevice.getWd());
					e.setFromTypeName(fromDevice.getType());
				}
			}

			if (toCollectId != null && !toCollectId.isEmpty()) {
				Map toDeviceParameter = new HashMap();
				toDeviceParameter.put("collectId", toCollectId);
				toDeviceParameter.put("typeNumber", toTypeNumber);
				CjBdzLineDevice toDevice = cjBdzLineDeviceDao
						.queryCjDeviceByCollectIdAndTypeNumber(toDeviceParameter);
				if (toDevice != null) {
					e.setToAmname(toDevice.getAmname());
					e.setToJd(toDevice.getJd());
					e.setToWd(toDevice.getWd());
					e.setToTypeName(toDevice.getType());
				}
			}
		}

		return page;
	}

	public List queryCjXldList(CjXldForm form) {
		CjXldDao cjDao = (CjXldDao) this.dao;
		CjLineDao cjLineDao = (CjLineDao) EAPContext.getContext().getBean(
				"cjLineDao");
		CjBdzLineDeviceDao cjBdzLineDeviceDao = (CjBdzLineDeviceDao) EAPContext
				.getContext().getBean("cjBdzLineDeviceDao");

		List<CjXld> cjXldList = cjDao.queryForList(form.toMap());

		for (CjXld e : cjXldList) {
			String xlCollectId = e.getXlCollectId();
			String fromCollectId = e.getFromId();
			Integer fromTypeNumber = e.getFromType();
			String toCollectId = e.getToId();
			Integer toTypeNumber = e.getToType();

			if (xlCollectId != null && !xlCollectId.isEmpty()) {
				CjLine cjLine = (CjLine) cjLineDao.findById(xlCollectId);
				e.setXlAmname(cjLine.getAmname());
			}

			if (fromCollectId != null && !fromCollectId.isEmpty()) {
				Map fromDeviceParameter = new HashMap();
				fromDeviceParameter.put("collectId", fromCollectId);
				fromDeviceParameter.put("typeNumber", fromTypeNumber);
				CjBdzLineDevice fromDevice = cjBdzLineDeviceDao
						.queryCjDeviceByCollectIdAndTypeNumber(fromDeviceParameter);
				if (fromDevice != null) {
					e.setFromAmname(fromDevice.getAmname());
					e.setFromJd(fromDevice.getJd());
					e.setFromWd(fromDevice.getWd());
					e.setFromTypeName(fromDevice.getType());
				}
			}

			if (toCollectId != null && !toCollectId.isEmpty()) {
				Map toDeviceParameter = new HashMap();
				toDeviceParameter.put("collectId", toCollectId);
				toDeviceParameter.put("typeNumber", toTypeNumber);
				CjBdzLineDevice toDevice = cjBdzLineDeviceDao
						.queryCjDeviceByCollectIdAndTypeNumber(toDeviceParameter);
				if (toDevice != null) {
					e.setToAmname(toDevice.getAmname());
					e.setToJd(toDevice.getJd());
					e.setToWd(toDevice.getWd());
					e.setToTypeName(toDevice.getType());
				}
			}
		}

		return cjXldList;
	}

	public CjXld queryCjXldByCollectId(String collectId) {
		CjXldDao cjDao = (CjXldDao) getDao();
		CjLineDao cjLineDao = (CjLineDao) EAPContext.getContext().getBean(
				"cjLineDao");
		CjBdzLineDeviceDao cjBdzLineDeviceDao = (CjBdzLineDeviceDao) EAPContext
				.getContext().getBean("cjBdzLineDeviceDao");

		CjXld cjXld = (CjXld) cjDao.findById(collectId);

		if (cjXld != null) {
			if (cjXld.getXlCollectId() != null
					&& !cjXld.getXlCollectId().isEmpty()) {
				CjLine cjLine = (CjLine) cjLineDao.findById(cjXld
						.getXlCollectId());
				cjXld.setXlAmname(cjLine.getAmname());
			}

			if (cjXld.getFromId() != null && !cjXld.getFromId().isEmpty()) {
				Map fromDeviceParameter = new HashMap();
				fromDeviceParameter.put("collectId", cjXld.getFromId());
				fromDeviceParameter.put("typeNumber", cjXld.getFromType());
				CjBdzLineDevice fromDevice = cjBdzLineDeviceDao
						.queryCjDeviceByCollectIdAndTypeNumber(fromDeviceParameter);
				cjXld.setFromAmname(fromDevice.getAmname());
				cjXld.setFromJd(fromDevice.getJd());
				cjXld.setFromWd(fromDevice.getWd());
				cjXld.setFromTypeName(fromDevice.getType());
			}

			if (cjXld.getToId() != null && !cjXld.getToId().isEmpty()) {
				Map toDeviceParameter = new HashMap();
				toDeviceParameter.put("collectId", cjXld.getToId());
				toDeviceParameter.put("typeNumber", cjXld.getToType());
				CjBdzLineDevice toDevice = cjBdzLineDeviceDao
						.queryCjDeviceByCollectIdAndTypeNumber(toDeviceParameter);
				cjXld.setToAmname(toDevice.getAmname());
				cjXld.setToJd(toDevice.getJd());
				cjXld.setToWd(toDevice.getWd());
				cjXld.setToTypeName(toDevice.getType());
			}
		}
		return cjXld;
	}

	public Map saveCjXld(CjXld cjXld) {
		CjXldDao cjDao = (CjXldDao) this.dao;
		String collectId = UUID.randomUUID().toString().replaceAll("\\-", "");
		cjXld.setCollectId(collectId);
		cjXld.setSysCreateTime(new Date());

		Map result = new HashMap();
		if (dao.insert(cjXld)) {
			result.put("statusCode", ResponseConstants.OK);
			result.put("resultMsg", "返回结果成功");
			result.put("collectId", collectId);
		} else {
			result.put("statusCode", ResponseConstants.INSERT_DATA_ERROR);
			result.put("resultMsg", "新增线路档信息失败");
		}
		return result;
	}

	public Map updateCjXld(CjXld cjXld) {
		CjXldDao cjDao = (CjXldDao) this.dao;
		cjDao.update(cjXld);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}

	public Map delCjXld(CjXld cjXld) {
		CjXldDao cjDao = (CjXldDao) this.dao;
		cjDao.delete(cjXld);
		Map result = new HashMap();
		result.put("statusCode", ResponseConstants.OK);
		result.put("resultMsg", "返回结果成功");
		return result;
	}
}
