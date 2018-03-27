package com.glens.pwCloudOs.materielMg.assetMg.asset.web;

import org.springframework.web.bind.annotation.RequestMapping;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;


@FormProcessor(clazz="com.glens.pwCloudOs.materielMg.assetMg.asset.web.AssetRepairForm")
@RequestMapping("pmsServices/materielMg/assetMg/assetRepair")
public class AssetRepairController extends EAPJsonAbstractController {
	
	

}
