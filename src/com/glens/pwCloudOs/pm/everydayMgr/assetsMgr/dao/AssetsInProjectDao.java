package com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.dao;

import java.util.Map;

import com.glens.eap.platform.core.annotation.MybatisNamespaceProcessor;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.dao.impl.EAPAbstractDao;

@MybatisNamespaceProcessor(value="com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.dao.pmTmVehicleFuelchargeVoMapper")
public class AssetsInProjectDao extends EAPAbstractDao {
	
	public PageBean selectAssetsInProject(Map params){
		PageBean page = null;
		page = this.queryForPage(params, "getAssetsInProjectCount", "selectAssetsInProject");
		return page;
	}
}
