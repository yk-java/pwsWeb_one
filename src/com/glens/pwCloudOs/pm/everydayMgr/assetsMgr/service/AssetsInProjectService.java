package com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.service;

import java.util.Map;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.dao.AssetsInProjectDao;

public class AssetsInProjectService extends EAPAbstractService  {
	public PageBean selectAssetsInProject(Map params) {
		AssetsInProjectDao daoBean = (AssetsInProjectDao)this.dao;
		return daoBean.selectAssetsInProject(params);
	}
}
