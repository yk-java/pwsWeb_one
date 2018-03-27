package com.glens.pwCloudOs.pm.taskMgr.device.web;

import org.springframework.web.bind.annotation.RequestMapping;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;

@FormProcessor(clazz="com.glens.pwCloudOs.pm.taskMgr.device.web.PowerDeviceTypeForm")
@RequestMapping("pmsServices/pm/taskMgr/deviceType")
public class PowerDeviceTypeController extends EAPJsonAbstractController {

	

}
