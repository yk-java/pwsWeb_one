/**
 * @Title: RechargeController.java
 * @Package com.glens.pwCloudOs.materielMg.vehicleMg.recharge.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-24 上午11:18:28
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.vehicleMg.recharge.web;

import org.springframework.web.bind.annotation.RequestMapping;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@FormProcessor(clazz="com.glens.pwCloudOs.materielMg.vehicleMg.recharge.web.RechargeForm")
@RequestMapping("pmsServices/materielMg/vehicleMg/recharge")
public class RechargeController extends EAPJsonAbstractController {

}
