package com.glens.pwCloudOs.questionnire.web;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.questionnire.dao.QuestionnireStatisticDao;
import com.glens.pwCloudOs.questionnire.service.QaQuestionnireSendService;
import com.glens.pwCloudOs.questionnire.service.QaQuestionnireService;
import com.glens.pwCloudOs.questionnire.service.QuestionnireStatisticService;
import com.glens.pwCloudOs.questionnire.vo.QaQuestionnire;
import com.glens.pwCloudOs.questionnire.vo.QaQuestionnireSend;
@RequestMapping("pmsServices/questionnireStatistic")
public class QuestionnireStatisticController extends EAPJsonAbstractController {
	private QaQuestionnireSendService sendService;
	private QaQuestionnireService questionnireService;
	
	/**
	 * 问卷查询
	 */
	@Override
	@RequestMapping(value="list", method=RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				Map<String, Object> params = new HashMap<String, Object>();
				String searchName=null;
				Integer status=null;
				Integer type=null;
				try {
					status=Integer.parseInt(request.getParameter("status"));
				} catch (NumberFormatException e1) {
				}
				try {
					type=Integer.parseInt(request.getParameter("type"));
				} catch (NumberFormatException e1) {
				}
				searchName=request.getParameter("searchName");
				if(searchName!=null && searchName.length()==0){
					searchName=null;
				}
				params.put("searchName", searchName);
				params.put("status", status);
				params.put("type", type);
				int currentPage = 1;
				try {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				} catch (NumberFormatException e) {
				}
				int perpage = 10;
				try {
					perpage = Integer.parseInt(request.getParameter("perpage"));
				} catch (NumberFormatException e) {
				}
				PageBean page = getService().queryForPage(params,
							currentPage, perpage);
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
					return BeanResult.success("ok", questionnire);
				} catch (Exception e) {
					e.printStackTrace();
					return BeanResult.fail(e.getMessage());
				}
			}
		});
	}
	
	/**
	 * 根据问卷编号与提交人查询问卷作答信息
	 */
	@RequestMapping(value="getUserAnswer", method=RequestMethod.GET)
	public Object getUserAnswer(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				try {
					String questionnireCode = request.getParameter("questionnireCode");
					String submitUser = request.getParameter("submitUser");
					QuestionnireStatisticService service=(QuestionnireStatisticService)getService();
					QaQuestionnire questionnire = service.getAnswerInfo(questionnireCode, submitUser);
					return BeanResult.success("ok", questionnire);
				} catch (Exception e) {
					e.printStackTrace();
					return BeanResult.fail(e.getMessage());
				}
			}
		});
	}
	
	
	@RequestMapping(value="statisticRadioAnswer", method=RequestMethod.GET)
	public Object statisticRadioAnswer(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				try{
					String questionCode = request.getParameter("questionCode");
					QuestionnireStatisticService service=(QuestionnireStatisticService)getService();
					List<Map<String, Object>> data = service.statisticRadioAnswer(questionCode);
					return BeanResult.success("ok", data);
				} catch (Exception e) {
					e.printStackTrace();
					return BeanResult.fail(e.getMessage());
				}
			}
		});
	}
	
	@RequestMapping(value="selectSupplementByQuestionCodeAndOptCode", method=RequestMethod.GET)
	public Object selectSupplementByQuestionCodeAndOptCode(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				try{
					String questionCode = request.getParameter("questionCode");
					String optNum = request.getParameter("optNum");
					QuestionnireStatisticService service=(QuestionnireStatisticService)getService();
					List<Map<String, Object>> data = service.selectSupplementByQuestionCodeAndOptCode(questionCode, optNum);
					return BeanResult.success("ok", data);
				} catch (Exception e) {
					e.printStackTrace();
					return BeanResult.fail(e.getMessage());
				}
			}
		});
	}
	
	@RequestMapping(value="selectTextAnswerByQuestionCode", method=RequestMethod.GET)
	public Object selectTextAnswerByQuestionCode(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				try{
					String questionCode = request.getParameter("questionCode");
					QuestionnireStatisticService service=(QuestionnireStatisticService)getService();
					List<Map<String, Object>> data = service.selectTextAnswerByQuestionCode(questionCode);
					return BeanResult.success("ok", data);
				} catch (Exception e) {
					e.printStackTrace();
					return BeanResult.fail(e.getMessage());
				}
			}
		});
	}
	
	/**
	 * 导出答卷数据
	 */
	@RequestMapping(value="exportAnswerData", method=RequestMethod.GET)
	public Object exportAnswerData(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("questionnireCode", request.getParameter("questionnireCode"));
				params.put("subType", request.getParameter("subType"));
				params.put("questionCode", request.getParameter("questionCode"));
				params.put("submitUser", request.getParameter("submitUser"));
				
				QuestionnireStatisticService service = (QuestionnireStatisticService)getService();
				List<Map<String, Object>> data = service.selectAnswerData(params);
				for (Iterator iterator = data.iterator(); iterator.hasNext();) {
					Map<String, Object> map = (Map<String, Object>) iterator
							.next();
					String subType = (String)map.get("subType");
					if("radio".equals(subType)){
						map.put("subTypeName", "单选题");
					}else if("checkbox".equals(subType)){
						map.put("subTypeName", "多选题");
					}else if("text".equals(subType)){
						map.put("subTypeName", "填空题");
					}else{
						map.put("subTypeName", subType);
					}
				}
				return data;
			}
			
			@Override
			public Object doWithFinish(HttpServletRequest request,
					HttpServletResponse response, Object data, String viewType,
					EAPController controller) {

				ModelAndView modelAndView = new ModelAndView();
				EAPView view = new EAPView() {

					@Override
					public void render(Map<String, ?> model,
							HttpServletRequest request,
							HttpServletResponse response) throws Exception {
						response.setCharacterEncoding("UTF-8");
						String fileName = URLEncoder.encode("答卷数据",
								"UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("答卷数据");
						List dataList = (List) data;
						excelHelper.writeData("AnswerData", response.getOutputStream(),
								Map.class, dataList);
					}

					@Override
					public String getContentType() {
						return "application/vnd.ms-excel;charset=UTF-8";
					}
				};

				view.setData(data);
				modelAndView.setView(view);

				return modelAndView;
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
