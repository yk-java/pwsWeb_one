package com.glens.pwCloudOs.pm.baseMgr.pmBase.service;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.dao.PmProKpiDao;

public class PmProKpiService extends EAPAbstractService {

	public int queryPmProKpiInWorkList(Long rowid) {
		// TODO Auto-generated method stub
		PmProKpiDao dao = (PmProKpiDao) getDao();
		return dao.queryPmProKpiInWorkList(rowid);
	}

}
