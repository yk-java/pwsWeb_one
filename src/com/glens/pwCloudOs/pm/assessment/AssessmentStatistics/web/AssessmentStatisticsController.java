/**
 * @Title: AssessmentStatisticsController.java
 * @Package com.glens.pwCloudOs.pm.assessment.AssessmentStatistics.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2017-1-10 上午11:26:02
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.assessment.AssessmentStatistics.web;

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
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.pm.assessment.AssessmentStatistics.service.AssessmentStatisticsService;

/**
 * 
 * 
 * @author
 * @version V1.0
 */

@FormProcessor(clazz = "com.glens.pwCloudOs.pm.assessment.AssessmentStatistics.web.AssessmentStatisticsForm")
@RequestMapping("pmsServices/pm/assessment/assessmentStatistics")
public class AssessmentStatisticsController extends EAPJsonAbstractController {

	@RequestMapping(value = "categoryList", method = RequestMethod.GET)
	public Object selectCheckCategory(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				AssessmentStatisticsService assessmentService = (AssessmentStatisticsService) getService();
				List<Map<String, String>> categoryList = assessmentService
						.selectCheckCategory(actionForm.toMap());
				ListResult result = new ListResult();
				if (categoryList != null && categoryList.size() > 0) {

					result.setResultMsg("获取指标分类成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(categoryList);
				} else {

					result.setResultMsg("获取指标分类失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "cycleList", method = RequestMethod.GET)
	public Object selectCheckCycle(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				AssessmentStatisticsService assessmentService = (AssessmentStatisticsService) getService();
				List<Map<String, String>> cycleList = assessmentService
						.selectCheckCycle();
				ListResult result = new ListResult();
				if (cycleList != null && cycleList.size() > 0) {

					result.setResultMsg("获取考核周期成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(cycleList);
				} else {

					result.setResultMsg("获取考核周期失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "kpiList", method = RequestMethod.GET)
	public Object getActiveKpi(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				AssessmentStatisticsService assessmentService = (AssessmentStatisticsService) getService();
				List<Map<String, String>> categoryList = assessmentService
						.getActiveKpi(actionForm.toMap());
				ListResult result = new ListResult();
				if (categoryList != null && categoryList.size() > 0) {

					result.setResultMsg("获取指标成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(categoryList);
				} else {

					result.setResultMsg("获取指标失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "assessmengStatistics", method = RequestMethod.GET)
	public Object getAssessmentStatistics(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);

				AssessmentStatisticsService assessmentService = (AssessmentStatisticsService) getService();

				Map map = actionForm.toMap();
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}

				Map<String, Object> statisticsResult = assessmentService
						.getAssessmentStatistics(map);

				BeanResult result = new BeanResult();
				if (statisticsResult != null && statisticsResult.size() > 0) {

					result.setResultMsg("获取考核统计成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setData(statisticsResult);
				} else {

					result.setResultMsg("获取考核统计失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "pmAssessment", method = RequestMethod.GET)
	public Object getPmAssessment(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				AssessmentStatisticsService assessmentService = (AssessmentStatisticsService) getService();
				List<Map<String, Object>> assessmentList = assessmentService
						.getPmAssessment(actionForm.toMap());
				ListResult result = new ListResult();
				if (assessmentList != null && assessmentList.size() > 0) {

					result.setResultMsg("获取项目考核成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(assessmentList);
				} else {

					result.setResultMsg("获取项目考核失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

	@RequestMapping(value = "kpiScore", method = RequestMethod.GET)
	public Object selectKpiScore(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				AssessmentStatisticsService assessmentService = (AssessmentStatisticsService) getService();
				List<Map<String, Object>> assessmentList = assessmentService
						.selectKpiScore(actionForm.toMap());
				ListResult result = new ListResult();
				if (assessmentList != null && assessmentList.size() > 0) {

					result.setResultMsg("获取项目考核成功!");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(assessmentList);
				} else {

					result.setResultMsg("获取项目考核失败!");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

}
