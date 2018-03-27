package com.glens.pwCloudOs.materielMg.vehicleMg.check.web;

import org.springframework.web.bind.annotation.RequestMapping;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;


@FormProcessor(clazz="com.glens.pwCloudOs.materielMg.vehicleMg.check.web.CheckForm")
@RequestMapping("pmsServices/materielMg/vehicleMg/check")

public class CheckController extends EAPJsonAbstractController {
	
}
