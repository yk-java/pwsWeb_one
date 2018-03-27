/**

 * @Title: MyCommuteController.java

 * @Package com.glens.pwCloudOs.commuteMgr.app.myCommute.web

 * @Description: TODO

 * Copyright: Copyright (c) 2016 

 * Company:南京量为石信息科技有限公司

 * @author 

 * @date 2016-5-20 下午5:11:44

 * @version V1.0

 **/

package com.glens.pwCloudOs.commuteMgr.app.myCommute.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.commuteMgr.app.myCommute.service.MyCommuteService;

/**
 * 
 * @ClassName: MyCommuteController
 * 
 * @Description: TODO
 * 
 * @author
 * 
 * @date 2016-5-20 下午5:11:44
 **/

@FormProcessor(clazz = "com.glens.pwCloudOs.commuteMgr.app.myCommute.web.MyCommuteForm")
@RequestMapping("/pmsServices/commuteMgr/app/myCommute")
public class MyCommuteController extends EAPJsonAbstractController {

	@RequestMapping(value="myCommuteStatus", method=RequestMethod.GET)
	public Object myCommuteStatus(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				MyCommuteForm myCommuteForm = (MyCommuteForm) actionForm;
				MyCommuteService myCommuteService = (MyCommuteService) getService();
				List<Map<String, String>> dateCommuteStatusList = myCommuteService
						.getMyCommuteStatusByMonth(
								myCommuteForm.getCompanyCode(),
								myCommuteForm.getUnitCode(),
								myCommuteForm.getAccountCode(),
								myCommuteForm.getMonth());
				ListResult listResult = new ListResult();
				if (dateCommuteStatusList != null && dateCommuteStatusList.size() > 0) {
					
					listResult.setStatusCode(ResponseConstants.OK);
					listResult.setResultMsg("获取我的月底通勤情况成功！");
					listResult.setList(dateCommuteStatusList);
				}
				else {
					
					listResult.setStatusCode(ResponseConstants.NO_DATA);
					listResult.setResultMsg("没有获取到我的月底通勤情况！");
				}

				return listResult;
			}
		});
	}

}
