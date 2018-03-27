/**
 * @Title: AssetTypeService.java
 * @Package com.glens.pwCloudOs.materielMg.assetMg.assetType.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-22 上午11:21:12
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.assetMg.assetType.service;

import java.util.List;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.materielMg.assetMg.assetType.dao.AssetTypeDao;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class AssetTypeService extends EAPAbstractService {
	
	
	public List getTypeList(Object parameters) {
		// TODO Auto-generated method stub
		AssetTypeDao dao=(AssetTypeDao)getDao();
		return dao.getTypeList(parameters);
	}

}
