/**
 * @Title: PmBaseController.java
 * @Package com.glens.pwCloudOs.pm.baseMgr.pmBase.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 上午10:55:26
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.web;

import org.springframework.web.bind.annotation.RequestMapping;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@FormProcessor(clazz="com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.web.PmTmVehicleFuelchargeForm")
@RequestMapping("pmsServices/pm/everydayMgr/assetsMgr/pmVehicleFuelcharge")
public class PmTmVehicleFuelchargeController extends EAPJsonAbstractController {
	
	
}
