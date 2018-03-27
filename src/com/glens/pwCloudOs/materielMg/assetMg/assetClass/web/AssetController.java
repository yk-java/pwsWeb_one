/**
 * @Title: AssetController.java
 * @Package com.glens.pwCloudOs.materielMg.assetMg.assetClass.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-22 上午10:50:11
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.assetMg.assetClass.web;

import org.springframework.web.bind.annotation.RequestMapping;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@FormProcessor(clazz="com.glens.pwCloudOs.materielMg.assetMg.assetClass.web.AssetClassForm")
@RequestMapping("pmsServices/materielMg/assetMg/assetClass")
public class AssetController extends EAPJsonAbstractController {

}
