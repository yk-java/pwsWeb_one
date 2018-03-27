package com.glens.pwCloudOs.questionnire.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.questionnire.service.QaQuestionnireService;
import com.glens.pwCloudOs.questionnire.vo.QaQuestionnire;
@RequestMapping("pmsServices/questionnire")
@FormProcessor(clazz = "com.glens.pwCloudOs.questionnire.web.QaQuestionnireForm")
public class QaQuestionnireController extends EAPJsonAbstractController {
	
	/**
	 * 保存问卷
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public Object save(HttpServletRequest request, HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String text = request.getParameter("questionnire");
				QaQuestionnire questionnire = (QaQuestionnire) JSON.parseObject(text, QaQuestionnire.class);
				QaQuestionnireService service = (QaQuestionnireService)getService();
				try {
					BeanResult result = service.saveQuestionnire(questionnire);
					return result;
				} catch (Exception e) {
					e.printStackTrace();
					return BeanResult.fail(e.getMessage());
				}
			}
		});
	}
	
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
					QaQuestionnireService service = (QaQuestionnireService)getService();
					QaQuestionnire questionnire = service.findByCode(questionnireCode);
					return BeanResult.success("ok", questionnire);
				} catch (Exception e) {
					e.printStackTrace();
					return BeanResult.fail(e.getMessage());
				}
			}
		});
	}
	
	/**
	 * 删除问卷
	 */
	@Override
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public Object delete(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String questionnireCode = request.getParameter("questionnireCode");
				QaQuestionnireService service = (QaQuestionnireService)getService();
				try {
					service.delete(questionnireCode);
					return ResponseResult.success("ok");
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseResult.fail(e.getMessage());
				}
			}
			
		});
	}
	
	
	public static void main(String[] args) {
		String text="{\"type\":\"1\",\"questions\":["
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
					+ "{\"num\":\"B\",\"$$hashKey\":\"object:105\",\"optionLabel\":\"地\"}]}]}";
		QaQuestionnire questionnire = (QaQuestionnire) JSON.parseObject(text, QaQuestionnire.class);
		System.out.println(questionnire.getQuestions().get(1).getOptions().size());
	}

}
