package com.glens.pwCloudOs.pm.everydayMgr.consumableReceive.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.pm.everydayMgr.consumableReceive.service.GmMaterialUseService;
import com.glens.pwCloudOs.pm.everydayMgr.consumableReceive.vo.GmMaterialUse;

@FormProcessor(clazz = "com.glens.pwCloudOs.pm.everydayMgr.consumableReceive.web.GmMaterialUseForm")
@RequestMapping("pmsServices/pm/everydayMgr/consumableReceive")
public class GmMaterialUseController extends EAPJsonAbstractController {

	@Override
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);

				Map map = actionForm.toMap();
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}

				PageBean page = getService().queryForPage(map,
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setPageBean(page);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "addFaster", method = RequestMethod.POST)
	public Object addFaster(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				GmMaterialUseService service = (GmMaterialUseService) getService();
				GmMaterialUse vo = (GmMaterialUse) actionForm.toVo();
				int iCount = service.addFaster(vo);

				ResponseResult result = new ResponseResult();

				if (iCount > 0) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
				} else {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "updateMaterial", method = RequestMethod.POST)
	public Object updateMaterial(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				GmMaterialUseService service = (GmMaterialUseService) getService();
				GmMaterialUse vo = (GmMaterialUse) actionForm.toVo();
				int iCount = service.updateMaterial(vo);

				ResponseResult result = new ResponseResult();

				if (iCount > 0) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("审批成功");
				} else {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("审批失败");
				}

				return result;
			}

		});
	}
}
