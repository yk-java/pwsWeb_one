/**
 * @Title: ScheduleSummaryController.java
 * @Package com.glens.pwCloudOs.pm.schedulePlan.scheduleSummary.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-12 下午2:23:22
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.schedulePlan.scheduleSummary.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.pm.schedulePlan.scheduleSummary.service.ScheduleSummaryService;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@FormProcessor(clazz = "com.glens.pwCloudOs.pm.schedulePlan.scheduleSummary.web.ScheduleSummaryForm")
@RequestMapping("pmsServices/pm/schedulePlan/scheduleSummary")
public class ScheduleSummaryController extends EAPJsonAbstractController {

	@RequestMapping(method = RequestMethod.GET)
	public Object scheduleSummary(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);

				
				ScheduleSummaryService scheduleSummaryService = (ScheduleSummaryService) getService();
				Map map = actionForm.toMap();

				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}

				List<Map<String, Object>> list = scheduleSummaryService
						.getScheduleSummary(map);
				ListResult result = new ListResult();
				if (list != null && list.size() > 0) {

					result.setResultMsg("获取项目进度统计数据成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(list);
				} else {

					result.setResultMsg("获取项目进度统计数据失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

}
