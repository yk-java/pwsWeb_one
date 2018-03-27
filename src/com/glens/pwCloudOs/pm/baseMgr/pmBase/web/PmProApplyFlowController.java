package com.glens.pwCloudOs.pm.baseMgr.pmBase.web;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.utils.DateTimeUtil;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.service.PmBaseService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.service.PmProApplyFlowService;
import com.glens.pwCloudOs.pm.baseMgr.pmBase.vo.PmBaseVo;

@FormProcessor(clazz = "com.glens.pwCloudOs.pm.baseMgr.pmBase.web.PmProApplyFlowForm")
@RequestMapping("pmsServices/pm/baseMgr/pmProApplyFlow")
public class PmProApplyFlowController extends EAPJsonAbstractController {

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				PmProApplyFlowForm form = (PmProApplyFlowForm) actionForm;
				form.setSysProcessFlag("1");
				// 待处理
				form.setStatus("1");
				try {
					form.setSysCreateTime(DateTimeUtil.formatDate(new Date(),
							DateTimeUtil.FORMAT_1));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				boolean ok = getService().insert(actionForm.toVo());
				KeyResult result = new KeyResult();
				if (ok) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("新增失败");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				ResponseResult result = new ResponseResult();
				PmProApplyFlowService service = (PmProApplyFlowService) getService();
				PmBaseService pmBaseService = (PmBaseService) EAPContext
						.getContext().getBean("pmBaseService");
				PmProApplyFlowForm form = (PmProApplyFlowForm) actionForm;
				form.setRowid(form.getApplyId());
				// 已处理
				form.setStatus("2");
				try {
					service.update(form.toVo());
					// 如果同意
					if (form.getReplyStatus().equals("1")) {
						// 修改项目状态change为1可修改，0不可修改
						PmBaseVo pb = new PmBaseVo();
						pb.setChange("1");
						pb.setProNo(form.getProNo());
						pmBaseService.update(pb);

					} else if (form.getReplyStatus().equals("0")) {
						PmBaseVo pb = new PmBaseVo();
						pb.setChange("0");
						pb.setProNo(form.getProNo());
						pmBaseService.update(pb);
					}
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
				} catch (Exception e) {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}

				return result;
			}

		});
	}
}
