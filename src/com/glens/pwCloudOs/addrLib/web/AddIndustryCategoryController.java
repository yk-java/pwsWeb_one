package com.glens.pwCloudOs.addrLib.web;

import org.springframework.web.bind.annotation.RequestMapping;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
@FormProcessor(clazz = "com.glens.pwCloudOs.addrLib.web.AddIndustryCategoryForm")
@RequestMapping("/pmsServices/addrLib/industryCategory")
public class AddIndustryCategoryController extends EAPJsonAbstractController {

}
