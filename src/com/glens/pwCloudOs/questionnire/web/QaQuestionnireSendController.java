package com.glens.pwCloudOs.questionnire.web;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.utils.excel.ExcelHelper;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.questionnire.service.QaQuestionnireSendService;
import com.glens.pwCloudOs.questionnire.vo.QaQuestionnire;
import com.glens.pwCloudOs.questionnire.vo.QaQuestionnireSend;
@RequestMapping("pmsServices/questionnireSend")
public class QaQuestionnireSendController extends EAPJsonAbstractController {

	/**
	 * 问卷分发
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="send", method=RequestMethod.POST)
	public Object save(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String text = request.getParameter("questionnireSend");
				JSONObject params = JSON.parseObject(text);
				QaQuestionnireSendService sendService = (QaQuestionnireSendService)getService();
				try {
					int res = sendService.doSend(params);
					return BeanResult.success("ok", res);
				} catch (Exception e) {
					e.printStackTrace();
					return BeanResult.fail(e.getMessage());
				}
			}
		});
	}
	
	/**
	 * 问卷分发情况查询
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
				try {
					status=Integer.parseInt(request.getParameter("status"));
				} catch (NumberFormatException e1) {
				}
				searchName=request.getParameter("searchName");
				if(searchName!=null && searchName.length()==0){
					searchName=null;
				}
				params.put("questionnireCode", request.getParameter("questionnireCode"));
				params.put("searchName", searchName);
				params.put("status", status);
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
	 * 问卷分发情况查询
	 */
	@Override
	@RequestMapping(value="listAll", method=RequestMethod.GET)
	public Object listAll(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				Map<String, Object> params = new HashMap<String, Object>();
				String searchName=null;
				Integer status=null;
				try {
					status=Integer.parseInt(request.getParameter("status"));
				} catch (NumberFormatException e1) {
				}
				searchName=request.getParameter("searchName");
				if(searchName!=null && searchName.length()==0){
					searchName=null;
				}
				params.put("questionnireCode", request.getParameter("questionnireCode"));
				params.put("searchName", searchName);
				params.put("status", status);
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
				List data = getService().queryForList(params);
				ListResult result = new ListResult();
				if (data != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(data);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}
			
		});
	}
	
	/**
	 * 导出问卷分发情况
	 */
	@RequestMapping(value="export", method=RequestMethod.GET)
	public Object export(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				Map<String, Object> params = new HashMap<String, Object>();
				String searchName=null;
				Integer status=null;
				try {
					status=Integer.parseInt(request.getParameter("status"));
				} catch (NumberFormatException e1) {
				}
				searchName=request.getParameter("searchName");
				if(searchName!=null && searchName.length()==0){
					searchName=null;
				}
				params.put("questionnireCode", request.getParameter("questionnireCode"));
				params.put("searchName", searchName);
				params.put("status", status);
				List data = getService().queryForList(params);
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
						String fileName = URLEncoder.encode("问卷下发记录",
								"UTF-8");

						response.setContentType("application/ms-excel;charset=UTF-8");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + fileName
										+ ".xlsx;charset=UTF-8");
						ExcelHelper excelHelper = new ExcelHelper();
						excelHelper.setSheetName("问卷下发记录");
						List dataList = (List) data;
						excelHelper.writeData(response.getOutputStream(),
								QaQuestionnireSend.class, dataList);
						// response.getOutputStream().write("abc".getBytes());
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
	
	
	/**
	 * 根据问卷编号与员工编号删除（撤销）下发到某人的问卷
	 */
	@Override
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public Object get(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				try {
					String questionnireCode = request.getParameter("questionnireCode");
					String employeeCode = request.getParameter("employeeCode");
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("questionnireCode", questionnireCode);
					params.put("employeeCode", employeeCode);
					QaQuestionnireSendService sendService = (QaQuestionnireSendService)service;
					int res = sendService.delete(params);
					return BeanResult.success("ok", res);
				} catch (Exception e) {
					e.printStackTrace();
					return BeanResult.fail(e.getMessage());
				}
			}
		});
	}
	
	/**
	 * 删除（撤销）问卷下发
	 */
	@Override
	@RequestMapping(value="deleteByQuestionnireCode", method=RequestMethod.POST)
	public Object delete(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String questionnireCode = request.getParameter("questionnireCode");
				try {
					QaQuestionnireSendService sendService = (QaQuestionnireSendService)service;
					int res = sendService.deleteByQuestionnireCode(questionnireCode);
					if(res>0){
						return ResponseResult.success("ok");
					}else{
						return ResponseResult.fail("fail");
					}
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseResult.fail(e.getMessage());
				}
			}
			
		});
	}
	
	public static void main(String[] args) {
		String text="[{\"type\":\"1\",\"questions\":["
				+ "{\"type\":\"group\",\"subType\":\"\",\"idx\":0,\"num\":\"一\",\"title\":\"基础\"},"
				+ "{\"type\":\"question\",\"subType\":\"radio\",\"idx\":1,\"num\":1,\"title\":\"你最喜欢的名星\",\"options\":["
					+ "{\"num\":\"A\",\"$$hashKey\":\"object:59\",\"optionLabel\":\"刘德华\"},"
					+ "{\"num\":\"B\",\"$$hashKey\":\"object:63\",\"optionLabel\":\"张学友\"},"
					+ "{\"num\":\"C\",\"$$hashKey\":\"object:67\",\"optionLabel\":\"其他\",\"isSupplement\":1}]},"
				+ "{\"type\":\"question\",\"subType\":\"radio\",\"idx\":2,\"num\":2,\"title\":\"你喜欢的水果\",\"options\":["
					+ "{\"num\":\"A\",\"$$hashKey\":\"object:74\",\"optionLabel\":\"蕉\"},"
					+ "{\"num\":\"B\",\"$$hashKey\":\"object:78\",\"optionLabel\":\"果\"}]},"
				+ "{\"type\":\"question\",\"subType\":\"checkbox\",\"idx\":3,\"num\":3,\"title\":\"你对公司有什么意见\",\"options\":["
					+ "{\"num\":\"A\",\"$$hashKey\":\"object:83\",\"optionLabel\":\"要\"},"
					+ "{\"num\":\"B\",\"$$hashKey\":\"object:87\",\"optionLabel\":\"厅\"}]},"
				+ "{\"type\":\"group\",\"subType\":\"\",\"idx\":4,\"num\":\"二\",\"title\":\"综合\"},"
				+ "{\"type\":\"question\",\"subType\":\"radio\",\"idx\":5,\"num\":4,\"title\":\"基本原则苛栽\",\"options\":["
					+ "{\"num\":\"A\",\"$$hashKey\":\"object:94\",\"optionLabel\":\"要\"},"
					+ "{\"num\":\"B\",\"$$hashKey\":\"object:98\",\"optionLabel\":\"在\"}]},"
				+ "{\"type\":\"question\",\"subType\":\"checkbox\",\"idx\":6,\"num\":5,\"title\":\"基本原则苛栽\",\"options\":["
					+ "{\"num\":\"A\",\"$$hashKey\":\"object:102\",\"optionLabel\":\"在\"},"
					+ "{\"num\":\"B\",\"$$hashKey\":\"object:105\",\"optionLabel\":\"地\"}]}]}]";
		List<QaQuestionnire> questionnire = (List<QaQuestionnire>) JSON.parseArray(text, QaQuestionnire.class);
		System.out.println(questionnire.get(0).getQuestions().get(1).getOptions().size());
	}

}
