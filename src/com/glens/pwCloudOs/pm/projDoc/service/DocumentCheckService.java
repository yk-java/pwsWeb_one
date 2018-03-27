package com.glens.pwCloudOs.pm.projDoc.service;

import java.util.Map;

import com.glens.eap.platform.core.orm.mybatis.exception.MyBatisAccessException;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.projDoc.dao.DocumentCheckDao;

public class DocumentCheckService extends EAPAbstractService {
	public Map queryByDocNo(Object parameter) {
		DocumentCheckDao dao = (DocumentCheckDao)this.getDao();
		return dao.queryByDocNo(parameter);
	}
}
