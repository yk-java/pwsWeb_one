package com.glens.pwCloudOs.pm.plan.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.service.PmProKpiService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.vo.PmProKpi;
import com.glens.pwCloudOs.pm.plan.service.PmKpiLibService;
import com.glens.pwCloudOs.pm.plan.vo.PmKpiLib;

@FormProcessor(clazz = "com.glens.pwCloudOs.pm.plan.web.PmKpiLibForm")
@RequestMapping("pmsServices/pm/kpiLib")
public class PmKpiLibController extends EAPJsonAbstractController {

	private PmProKpiService pmProKpiService;

	@RequestMapping(value = "importData", method = RequestMethod.POST)
	public Object importData(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PmKpiLibForm form = (PmKpiLibForm) actionForm;
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext()
						.getBean(CodeWorker.SIMPLE_CODE_WORKER);
				PmKpiLibService pmKpiLibService = (PmKpiLibService) getService();
				Map paramsMap = new HashMap<String, Object>();
				paramsMap.put("categoryCode", form.getCategoryCode());
				List list = pmKpiLibService.queryForList(paramsMap);
				ListResult result = new ListResult();
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						PmKpiLib pmKpiLib = (PmKpiLib) list.get(i);
						PmProKpi pmProKpi = new PmProKpi();
						pmProKpi.setProNo(form.getProNo());
						pmProKpi.setKpiType(pmKpiLib.getKpiType());
						pmProKpi.setKpiName(pmKpiLib.getKpiName());
						pmProKpi.setKpiWeight(pmKpiLib.getKpiWeight());
						pmProKpi.setKpiUnit(pmKpiLib.getKpiUnit());
						pmProKpi.setKpiDesc(pmKpiLib.getKpiDesc());
						pmProKpi.setKpiSort(pmKpiLib.getKpiSort());
						pmProKpi.setStandard(pmKpiLib.getStandard());
						pmProKpi.setUesAttached(pmKpiLib.getUesAttached());
						pmProKpi.setAttachedUnit(pmKpiLib.getAttachedUnit());
						pmProKpi.setMergeFlag(pmKpiLib.getMergeFlag());
						pmProKpi.setIsPersonCnt(pmKpiLib.getIsPerCnt());
						pmProKpi.setKpiCode(codeWorker.createCode("k"));
						pmProKpi.setSysProcessFlag("1");
						pmProKpi.setSysCreateTime(new Date());
						pmProKpiService.insert(pmProKpi);
					}
					paramsMap = new HashMap<String, Object>();
					paramsMap.put("proNo", form.getProNo());
					List proKpiList = pmProKpiService.queryForList(paramsMap);
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("查询成功");
					result.setList(proKpiList);
				} else {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("查询失败");
				}

				return result;
			}

		});
	}

	public PmProKpiService getPmProKpiService() {
		return pmProKpiService;
	}

	public void setPmProKpiService(PmProKpiService pmProKpiService) {
		this.pmProKpiService = pmProKpiService;
	}

}
