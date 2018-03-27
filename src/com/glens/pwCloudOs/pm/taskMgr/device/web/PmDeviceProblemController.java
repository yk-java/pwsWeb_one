package com.glens.pwCloudOs.pm.taskMgr.device.web;

import org.springframework.web.bind.annotation.RequestMapping;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;

@FormProcessor(clazz="com.glens.pwCloudOs.pm.taskMgr.device.web.PmDeviceProblemForm")
@RequestMapping("pmsServices/pm/taskMgr/deviceProblem")
public class PmDeviceProblemController extends EAPJsonAbstractController {

	

}
