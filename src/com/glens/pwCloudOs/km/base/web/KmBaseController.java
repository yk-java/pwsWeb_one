package com.glens.pwCloudOs.km.base.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.km.base.service.KmBaseService;
import com.glens.pwCloudOs.km.base.vo.KmBaseVo;
import com.glens.pwCloudOs.km.question.service.QuestionService;
import com.glens.pwCloudOs.pm.projDoc.service.ProjDocService;


@FormProcessor(clazz="com.glens.pwCloudOs.km.base.web.KmBaseForm")
@RequestMapping("pmsServices/km/kmbase/kmbase")
public class KmBaseController extends EAPJsonAbstractController {
	
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	@Override
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				KmBaseService kmBaseService = (KmBaseService) getService();
				ResponseResult result = new ResponseResult();
				try {
					boolean _ok = kmBaseService.insert(actionForm.toVo(), getRootPath(request));
					
					if (_ok) {
						
						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("上传项目文档成功!");
					}
					else {
						
						result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
						result.setResultMsg("上传项目文档失败!");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("上传项目文档失败:" + e.getMessage());
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("上传项目文档失败:" + e.getMessage());
					e.printStackTrace();
				}
				
				return result;
			}
		});
	}
	
	
	//删除
	@Override
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public Object delete(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				KmBaseService ser=(KmBaseService)getService();
				
				KmBaseForm form=(KmBaseForm)actionForm;
				
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
	
	//新增下载
	@RequestMapping(value="insertDownStudy", method=RequestMethod.GET)
	public Object insertDownStudy(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				KmBaseService kmBaseService = (KmBaseService) getService();
				ResponseResult result = new ResponseResult();
			
				boolean _ok = kmBaseService.insertDownStudy(actionForm.toMap());
				
				if (_ok) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("成功!");
				}
				else {
					
					result.setStatusCode(ResponseConstants.INSERT_DATA_ERROR);
					result.setResultMsg("失败!");
				}
				
				return result;
			}
		});
	}
	
	@Override
	@RequestMapping(value="get", method=RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ValueObject vo = 
					getService().findById(actionForm.toVo());
				
				KmBaseVo v=new KmBaseVo();
				
				if(vo!=null){
					 v=(KmBaseVo)vo;
				}
				
				
				KmBaseService ser=(KmBaseService)getService();
				KmBaseForm form=(KmBaseForm)actionForm;
				Map resultMap=new HashMap();
				Map m=new HashMap();
				if(v!=null){
					m.put("fileCode", v.getFileCode());
					List list=ser.getAttachments(m);
					resultMap.put("attachmentList", list);
				}else{
					resultMap.put("attachmentList", null);
				}
				
				
				
				resultMap.put("vo", vo);
				
				
				
				ser.updateDownNum(m); //点击量增加
				ser.insertReadrecord(actionForm.toMap());// 记录阅读信息
				
				BeanResult result = new BeanResult();
				if (vo != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(resultMap);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				
				return result;
			}
			
		});
	}

	
	
	@RequestMapping(value="getTop5", method=RequestMethod.GET)
	public Object getTop5(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				KmBaseService ser=(KmBaseService)getService();
				
				KmBaseForm form=(KmBaseForm)actionForm;
				String companyCode=form.getCompanyCode();
				Map m=new HashMap();
				m.put("companyCode", companyCode);
				List  list = ser.getTop5(m);
							
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
	 * 全文检索
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="searchFiles", method=RequestMethod.GET)
	public Object searchFiles(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				KmBaseService ser=(KmBaseService)getService();
				
				KmBaseForm form=(KmBaseForm)actionForm;
				//String companyCode=form.getCompanyCode();
				//Map m=new HashMap();
				//m.put("companyCode", companyCode);
				PageBean  list = ser.queryKmBasekeyWordList(form.toMap(),10,actionForm.getCurrentPage(),form.getFullTextSearch());
							
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
	
	/**
	 * 发布  收回  
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updatePublishStatus", method = RequestMethod.POST)
	public Object updatePublishStatus(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				KmBaseService ser=(KmBaseService)getService();
				
				KmBaseForm form=(KmBaseForm)actionForm;

				boolean isSucess = ser.updatePublishStatus(actionForm.toMap(),form);

				ResponseResult result = new ResponseResult();

				if (isSucess) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("发布成功");
				} else {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("发布失败");
				}

				return result;
			}

		});
	}
	/**
	 * 修改文档信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "updateKmBae", method = RequestMethod.POST)
	public Object updateKmBae(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub

				KmBaseService ser=(KmBaseService)getService();
				KmBaseForm form=(KmBaseForm)actionForm;

				boolean isSucess = ser.updateKmBae(actionForm.toMap(),form);

				ResponseResult result = new ResponseResult();

				if (isSucess) {

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
	
	
	@RequestMapping(value="catalogMap", method=RequestMethod.GET)
	public Object catalogMap(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				KmBaseService ser=(KmBaseService)getService();
				
				KmBaseForm form=(KmBaseForm)actionForm;
				String companyCode=form.getCompanyCode();
				Map m=new HashMap();
				m.put("companyCode", companyCode);
				List  list = ser.catalogMap(m);
							
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
	
	@RequestMapping(value="getPersonRead", method=RequestMethod.GET)
	public Object getPersonRead(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				KmBaseService ser=(KmBaseService)getService();
				
				KmBaseForm form=(KmBaseForm)actionForm;
				String companyCode=form.getCompanyCode();
				Map m=new HashMap();
				m.put("companyCode", companyCode);
				m.put("fromDate", form.getFromDate());
				m.put("toDate", form.getToDate());
				m.put("employeeCode", form.getEmployeeCode());
				
				List  list = ser.getPersonRead(m);
							
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
	
	
	
	
	
	
	
}
