package com.glens.pwCloudOs.cj.base.service;

import java.util.List;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.cj.base.dao.CjBdzLineDeviceDao;
import com.glens.pwCloudOs.cj.base.vo.CjBdzLineDevice;
import com.glens.pwCloudOs.cj.base.web.CjBdzLineDeviceForm;

public class CjBdzLineDeviceService extends EAPAbstractService {

	public PageBean queryCjBdzLineDeviceForPage(CjBdzLineDeviceForm form) {
		CjBdzLineDeviceDao dao = (CjBdzLineDeviceDao) this.dao;
		return dao.queryForPage(form.toMap(), form.getCurrentPage(),
				form.getPerpage());
	}

	public List queryCjBdzLineDeviceList(CjBdzLineDeviceForm form) {
		CjBdzLineDeviceDao cjDao = (CjBdzLineDeviceDao) this.dao;
		return cjDao.queryForList(form.toMap());
	}
	
	public CjBdzLineDevice queryCjDeviceByCollectIdAndTypeNumber(CjBdzLineDeviceForm form) {
		CjBdzLineDeviceDao cjDao = (CjBdzLineDeviceDao) this.dao;
		return cjDao.queryCjDeviceByCollectIdAndTypeNumber(form.toMap());
	}
}
