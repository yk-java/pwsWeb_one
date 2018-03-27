package com.glens.pwCloudOs.pm.baseMgr.pmBase.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.service.PmProKpiService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.vo.PmProKpi;
import com.glens.pwCloudOs.pm.plan.service.PmKpiLibService;
import com.glens.pwCloudOs.pm.plan.vo.PmKpiLib;

@FormProcessor(clazz = "com.glens.pwCloudOs.pm.baseMgr.pmBase.web.PmProKpiForm")
@RequestMapping("pmsServices/pm/baseMgr/pmProKpi")
public class PmProKpiController extends EAPJsonAbstractController {

	private PmKpiLibService pmKpiLibService;

	/**
	 * 项目指标保存或更新
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Object save(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmProKpiForm form = (PmProKpiForm) actionForm;
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext()
						.getBean(CodeWorker.SIMPLE_CODE_WORKER);
				KeyResult result = new KeyResult();
				try {
					if (form.getRowid() != null && form.getRowid() == -1L) {
						form.setKpiCode(codeWorker.createCode("k"));
						form.setSysProcessFlag("1");
						getService().insert(form.toVo());
						// 保存，判断是否同步到指标库
						if (StringUtils.isNotEmpty(form.getSync())
								&& form.getSync().equals("1")) {
							PmKpiLib pmKpiLib = new PmKpiLib();
							pmKpiLib.setCategoryCode(form.getCategoryCode());
							pmKpiLib.setKpiType(form.getKpiType());
							pmKpiLib.setKpiCode(codeWorker.createCode("d"));
							pmKpiLib.setKpiName(form.getKpiName());
							pmKpiLib.setKpiUnit(form.getKpiUnit());
							pmKpiLib.setKpiDesc(form.getKpiDesc());
							pmKpiLib.setKpiWeight(form.getKpiWeight());
							pmKpiLib.setStandard(form.getStandard());
							pmKpiLib.setKpiSort(form.getKpiSort());
							pmKpiLib.setUesAttached(form.getUesAttached());
							pmKpiLib.setAttachedUnit(form.getAttachedUnit());
							pmKpiLib.setMergeFlag(form.getMergeFlag());
							pmKpiLib.setSysCreateTime(new Date());
							pmKpiLib.setSysProcessFlag("1");
							if (form.getIsPersonCnt() != null) {
								pmKpiLib.setIsPerCnt(form.getIsPersonCnt());
							}
							pmKpiLibService.insert(pmKpiLib);
						}
					} else {
						PmProKpi pmProKpi = (PmProKpi) form.toVo();
						pmProKpi.setSysUpdateTime(new Date());
						getService().update(pmProKpi);
					}
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("操作成功");
				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("操作失败");
				}
				return result;
			}
		});
	}

	@RequestMapping(value = "queryList", method = RequestMethod.GET)
	public Object queryList(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				List res = getService().queryForList(actionForm.toMap());
				ListResult result = new ListResult();
				if (res != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(res);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}

		});
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Object delete(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmProKpiForm form = (PmProKpiForm) actionForm;
				PmProKpiService pmProKpiService = (PmProKpiService) getService();
				ResponseResult result = new ResponseResult();
				int count = pmProKpiService.queryPmProKpiInWorkList(form
						.getRowid());
				if (count > 0) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("项目指标已被引用不能删除");
				} else {
					int iCount = pmProKpiService.delete(form.toVo());

					if (iCount > 0) {
						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("删除成功");
					} else {
						result.setStatusCode(ResponseConstants.SERVER_ERROR);
						result.setResultMsg("删除出错");
					}
				}
				return result;
			}

		});
	}

	public void setPmKpiLibService(PmKpiLibService pmKpiLibService) {
		this.pmKpiLibService = pmKpiLibService;
	}

}
