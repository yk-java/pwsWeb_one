package com.glens.pwCloudOs.pm.scene.web;

import org.springframework.web.bind.annotation.RequestMapping;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;

@RequestMapping("pmsServices/pm/monitorPointHis")
@FormProcessor(clazz = "com.glens.pwCloudOs.pm.scene.web.PmSceneMonitorPointHisForm")
public class PmSceneMonitorPointHisController extends EAPJsonAbstractController {

}
