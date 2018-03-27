/**
 * @Title: FuelCardController.java
 * @Package com.glens.pwCloudOs.materielMg.vehicleMg.fuelCard.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-23 下午5:56:24
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.vehicleMg.fuelCard.web;

import org.springframework.web.bind.annotation.RequestMapping;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@FormProcessor(clazz="com.glens.pwCloudOs.materielMg.vehicleMg.fuelCard.web.FuelCardForm")
@RequestMapping("pmsServices/materielMg/vehicleMg/fuelCard")
public class FuelCardController extends EAPJsonAbstractController {

}
