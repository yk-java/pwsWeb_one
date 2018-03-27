package com.glens.pwCloudOs.pm.plan.web;

import org.springframework.web.bind.annotation.RequestMapping;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;

@FormProcessor(clazz="com.glens.pwCloudOs.pm.plan.web.PmPlanKpiForm")
@RequestMapping("pmsServices/pm/planKpi")
public class PmPlanKpiController extends EAPJsonAbstractController {

}
