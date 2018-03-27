package com.glens.pwCloudOs.commuteMgr.online.web;

import org.springframework.web.bind.annotation.RequestMapping;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;

@FormProcessor(clazz = "com.glens.pwCloudOs.commuteMgr.online.web.CpOnlineUserHistoryForm")
@RequestMapping("/pmsServices/commuteMgr/app/online")
public class OnlineUserController extends EAPJsonAbstractController {

}
