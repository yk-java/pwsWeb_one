package com.glens.pwCloudOs.commuteMgr.performance.digitalSignage.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.pwCloudOs.commuteMgr.performance.digitalSignage.service.CpCommuteCheckService;

@FormProcessor(clazz = "com.glens.pwCloudOs.commuteMgr.performance.digitalSignage.web.CpCommuteCheckForm")
@RequestMapping("/pmsServices/commuteMgr/performance/digitalSignage")
public class CpCommuteCheckController extends EAPJsonAbstractController {
	
	
	@RequestMapping(method = RequestMethod.GET, value = "checkinRatio")
	public Object unitSignPerOfPassStat(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				CpCommuteCheckService cpcommutecheckservice = (CpCommuteCheckService) EAPContext
						.getContext().getBean("cpCommuteCheckService");
				CpCommuteCheckForm form = (CpCommuteCheckForm) actionForm;
				Map result = cpcommutecheckservice.unitSignPerOfPassStat(form);

				return result;
			}

		});
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "checkin")
	public Object signPerOfPassTop10(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				CpCommuteCheckService cpcommutecheckservice = (CpCommuteCheckService) EAPContext
						.getContext().getBean("cpCommuteCheckService");
				CpCommuteCheckForm form = (CpCommuteCheckForm) actionForm;
				Map result = cpcommutecheckservice.signPerOfPassTop10(form);

				return result;
			}

		});
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "judging")
	public Object judging(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				CpCommuteCheckService cpcommutecheckservice = (CpCommuteCheckService) EAPContext
						.getContext().getBean("cpCommuteCheckService");
				CpCommuteCheckForm form = (CpCommuteCheckForm) actionForm;
				int res=0;
				if(form.getRowid()!=null && form.getRowid()!= 0){
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("rowid", form.getRowid());
					params.put("judgingCode", form.getJudgingCode());
					params.put("judgingName", form.getJudgingName());
					params.put("judgingDescr", form.getJudgingDescr());
					res = cpcommutecheckservice.updateJudging(params);
				}else{
					res = cpcommutecheckservice.insertWithJudging(form.toVo());
				}
				if(res>0){
					return BeanResult.success("ok", "success");
				}else{
					return BeanResult.fail("fail");
				}
			}

		});
	}
	
}
