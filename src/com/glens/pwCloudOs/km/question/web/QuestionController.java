package com.glens.pwCloudOs.km.question.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.utils.StringUtil;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.es.EsQuestionService;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.eap.sys.orgEmployee.account.service.PfAccountService;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.km.base.service.KmBaseService;
import com.glens.pwCloudOs.km.base.web.KmBaseForm;
import com.glens.pwCloudOs.km.question.service.QuestionService;
import com.glens.pwCloudOs.materielMg.assetMg.asset.web.AssetForm;
import com.glens.pwCloudOs.notice.service.SmNoticeService;

@FormProcessor(clazz="com.glens.pwCloudOs.km.question.web.QuestionForm")
@RequestMapping("pmsServices/km/question")
public class QuestionController extends EAPJsonAbstractController {
	
	private PfAccountService pfAccountService;
	
	private SmNoticeService smNoticeService;

	@RequestMapping(value="list", method=RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				QuestionService ser=(QuestionService)getService();
				
				QuestionForm form=(QuestionForm)actionForm;
				
				UserToken token = getToken(request);

				
				if (token != null) {
					
					if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token.getRoleCode()) 
							|| PwCloudOsConstant.PRO_WATCHER_ROLE_CODE.equals(token.getRoleCode())) {
						
						form.setDeptCode(token.getUnitCode());
					}
					
					if (PwCloudOsConstant.DISTRICT_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						form.setDistrictManager(token.getEmployeeCode());
					}
					
					if (PwCloudOsConstant.PRO_MANAGER_ROLE_CODE.equals(token.getRoleCode())) {
						
						form.setProManager(token.getEmployeeCode());
					}
				}
				
				form.setDealCode(token.getEmployeeCode());
				
				PageBean page = ser.queryForPage(form.toMap(), 
							actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setPageBean(page);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				
				return result;
			}
			
		});
	}
	


	@RequestMapping(value="getTypes", method=RequestMethod.GET)
	public Object getTypes(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				QuestionService ser=(QuestionService)getService();

				List  list = ser.getTypes(actionForm.toMap());

				PageBeanResult result = new PageBeanResult();
				if (list != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(list);
				}
				else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}
	
	/**
	 * 获取审核人列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getCheckers", method=RequestMethod.GET)
	public Object getCheckers(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				QuestionService ser=(QuestionService)getService();

				List  list = ser.getCheckers(actionForm.toMap());

				PageBeanResult result = new PageBeanResult();
				if (list != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(list);
				}
				else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}
	
	
	
	@RequestMapping(value="getQuestionByCode", method=RequestMethod.GET)
	public Object getQuestionByCode(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				QuestionService ser=(QuestionService)getService();

				List  list = ser.getQuestionByCode(actionForm.toMap());

				
				
				BeanResult result=new BeanResult();
				if (list != null) {

					
					
					if(list.size()>0){
						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("获取成功");
						result.setData(list.get(0));
					}else{
						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setResultMsg("未获取到数据");
					}
					
				}
				else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("异常");
				}

				return result;
			}

		});
	}

	@RequestMapping(value="insertQuestion", method=RequestMethod.POST)
	public Object insertQuestion(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				QuestionService ser=(QuestionService)getService();
				ResponseResult result = new ResponseResult();
				QuestionForm form=(QuestionForm)actionForm;
				if (form.getSovlerCode() == null || "".equals(form.getSovlerCode())) {
					
					UserToken token = (UserToken) getToken(request);
					if (token != null) {
						
						form.setSovlerCode(token.getEmployeeCode());
					}
				}
				
				boolean _ok = ser.insertQuestion(actionForm.toVo(),form);

				if (_ok) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功!");
					
					//推送
					String msgContent = "问题【" + form.getQuestionTitle() + "】需要您来解答，请迅速登录云平台解答！";
					List<String> employeeCodes = new ArrayList<String>();
					employeeCodes.add(form.getSovlerCode());
					List<String> accountCodeList = pfAccountService.selectAccountsByEmployeecodes(employeeCodes);
					smNoticeService.sendMessage("系统通知", "问题解答", msgContent, accountCodeList);
				}
				else {

					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
					result.setResultMsg("新增失败!");
				}

				return result;
			}
		});
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public Object delete(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				QuestionService ser=(QuestionService)getService();
				
				QuestionForm form=(QuestionForm)actionForm;
				
				int iCount = ser.delete(actionForm.toMap(),form.getRowid());
				ResponseResult result = new ResponseResult();
				
				if (iCount > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("删除成功");
				}
				else {
					
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("删除出错");
				}
				
				return result;
			}
			
		});
	}
	
	//修改问题
	@RequestMapping(value = "updateQuestion", method = RequestMethod.POST)
	public Object updateQuestion(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				QuestionService ser=(QuestionService)getService();
				QuestionForm form=(QuestionForm)actionForm;
				int iCount = ser.updateQuestion(actionForm.toMap(),form);

				ResponseResult result = new ResponseResult();

				if (iCount >= 0) {

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
	
	/**
	 * 审核意见
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "checkOption", method = RequestMethod.POST)
	public Object checkOption(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				QuestionService ser=(QuestionService)getService();
				QuestionForm form=(QuestionForm)actionForm;
				int iCount = ser.checkOption(actionForm.toMap(),form);

				ResponseResult result = new ResponseResult();

				if (iCount >= 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功");
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("失败");
				}

				return result;
			}

		});
	}
	
	
	
	@RequestMapping(value = "updateReadNum", method = RequestMethod.POST)
	public Object updateReadNum(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				QuestionService ser=(QuestionService)getService();

				int iCount = ser.updateReadNum(actionForm.toMap());

				ResponseResult result = new ResponseResult();

				if (iCount >= 0) {

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
	
	//全文检索
	
	@RequestMapping(value="searchAll", method=RequestMethod.GET)
	public Object searchAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
			    QuestionService ser=(QuestionService)getService();
				
				QuestionForm form=(QuestionForm)actionForm;
				//String companyCode=form.getCompanyCode();
				//Map m=new HashMap();
				//m.put("companyCode", companyCode);
				PageBean  list = ser.queryQuestionList(form.toMap(),10,actionForm.getCurrentPage(),form.getFullTextSearch(),form.getPublicStatus(),form.getStatus());
							
				PageBeanResult result = new PageBeanResult();
				if (list != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setPageBean(list);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				return result;
			}
			
		});
	}



	public PfAccountService getPfAccountService() {
		return pfAccountService;
	}



	public void setPfAccountService(PfAccountService pfAccountService) {
		this.pfAccountService = pfAccountService;
	}



	public SmNoticeService getSmNoticeService() {
		return smNoticeService;
	}



	public void setSmNoticeService(SmNoticeService smNoticeService) {
		this.smNoticeService = smNoticeService;
	}
	


}
