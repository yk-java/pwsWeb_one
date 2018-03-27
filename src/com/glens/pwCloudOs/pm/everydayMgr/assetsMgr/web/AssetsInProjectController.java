/**
 * @Title: PmBaseController.java
 * @Package com.glens.pwCloudOs.pm.baseMgr.pmBase.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 上午10:55:26
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.service.AssetsInProjectService;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@RequestMapping("pmsServices/pm/everydayMgr/assetsMgr/assetsInProject")
public class AssetsInProjectController extends EAPJsonAbstractController {

	@RequestMapping(value = "selectAssetsInProject", method = RequestMethod.GET)
	public Object active(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {

				Map<String, Object> params = new HashMap<String, Object>();
				Map<String, Object> pageParams = PageBean
						.getPageParamsFromReq(request);// 分页参数
				params.putAll(pageParams);
				params.put("companyCode", request.getParameter("companyCode"));
				params.put("accountCode", request.getParameter("accountCode"));
				params.put("proNo", request.getParameter("proNo"));
				params.put("assetClassCode",
						request.getParameter("assetClassCode"));
				params.put("loanEmployeename",
						request.getParameter("loanEmployeename"));
				UserToken token = getToken(request);
				if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {

					params.put("districtManager", token.getEmployeeCode());
				}
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					params.put("deptCode", token.getUnitCode());
				}

				AssetsInProjectService serviceBean = (AssetsInProjectService) getService();
				PageBean pageBean = serviceBean.selectAssetsInProject(params);
				PageBeanResult result = new PageBeanResult();
				if (pageBean != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setPageBean(pageBean);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}
		});
	}
}
