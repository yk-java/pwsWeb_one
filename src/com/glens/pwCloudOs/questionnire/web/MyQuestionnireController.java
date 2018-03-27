package com.glens.pwCloudOs.questionnire.web;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.pwCloudOs.questionnire.service.QaQuestionnireSendService;
import com.glens.pwCloudOs.questionnire.service.QaQuestionnireService;
import com.glens.pwCloudOs.questionnire.vo.QaAnswerSheet;
import com.glens.pwCloudOs.questionnire.vo.QaQuestion;
import com.glens.pwCloudOs.questionnire.vo.QaQuestionnire;
@RequestMapping("pmsServices/myQuestionnire")
public class MyQuestionnireController extends EAPJsonAbstractController {
	private QaQuestionnireSendService sendService;
	private QaQuestionnireService questionnireService;
	/**
	 * 我的问卷
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="questionnireList")
	public Object selectMyQuestionnire(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String employeeCode = request.getParameter("employeeCode");
				List<Map<String, Object>> res = sendService.selectMyQuestionnire(employeeCode);
				return ListResult.success("ok", res);
			}
			
		});
	}
	
	/**
	 * 获取问卷
	 */
	@Override
	@RequestMapping(value="get", method=RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				try {
					String questionnireCode = request.getParameter("questionnireCode");
					QaQuestionnire questionnire = questionnireService.findByCode(questionnireCode);
					// 清除标准答案
					List<QaQuestion> questions = questionnire.getQuestions();
					for (Iterator<QaQuestion> iterator = questions.iterator(); iterator
							.hasNext();) {
						QaQuestion qaQuestion = iterator.next();
						qaQuestion.setAnswer("");
					}
					return BeanResult.success("ok", questionnire);
				} catch (Exception e) {
					e.printStackTrace();
					return BeanResult.fail(e.getMessage());
				}
			}
		});
	}
	
	/**
	 * 提交问卷答案
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="submitAnswer", method=RequestMethod.POST)
	public Object submitAnswer(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String text = request.getParameter("answerSheet");
				QaAnswerSheet answerSheet = (QaAnswerSheet) JSON.parseObject(text, QaAnswerSheet.class);
				try {
					BeanResult res =  questionnireService.submitAnswer(answerSheet);
					return res;
				} catch (Exception e) {
					e.printStackTrace();
					return BeanResult.fail(e.getMessage());
				}
			}
		});
	}

	public QaQuestionnireSendService getSendService() {
		return sendService;
	}

	public void setSendService(QaQuestionnireSendService sendService) {
		this.sendService = sendService;
	}

	public QaQuestionnireService getQuestionnireService() {
		return questionnireService;
	}

	public void setQuestionnireService(QaQuestionnireService questionnireService) {
		this.questionnireService = questionnireService;
	}
	
}
