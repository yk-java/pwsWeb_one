package com.glens.pwCloudOs.materielMg.goods.web;

import org.springframework.web.bind.annotation.RequestMapping;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;

@FormProcessor(clazz = "com.glens.pwCloudOs.materielMg.goods.web.GoodsForm")
@RequestMapping("pmsServices/materielMg/goods")
public class GoodsController extends EAPJsonAbstractController {

}
