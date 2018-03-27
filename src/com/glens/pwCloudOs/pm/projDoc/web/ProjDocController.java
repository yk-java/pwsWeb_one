/**
 * @Title: ProjDocController.java
 * @Package com.glens.pwCloudOs.pm.projDoc.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-7-19 下午2:15:49
 * @version V1.0
 */

package com.glens.pwCloudOs.pm.projDoc.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
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
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.common.context.PwCloudOsConstant;
import com.glens.pwCloudOs.pm.projDoc.service.ProjDocService;

/**
 * 
 * 
 * @author
 * @version V1.0
 */
@FormProcessor(clazz = "com.glens.pwCloudOs.pm.projDoc.web.ProjDocForm")
@RequestMapping("pmsServices/pm/projDoc")
public class ProjDocController extends EAPJsonAbstractController {

	/**
	 * 
	 * <p>
	 * Title: insert
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 * @see com.glens.eap.platform.framework.web.EAPJsonAbstractController#insert(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 **/

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@Override
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProjDocService projDocService = (ProjDocService) getService();
				ResponseResult result = new ResponseResult();
				try {
					boolean _ok = projDocService.insert(actionForm.toVo(),
							getRootPath(request));

					if (_ok) {

						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("上传项目文档成功!");
					} else {

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

	@Override
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
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
	
	
	@RequestMapping(value = "getAllFileByProNo", method = RequestMethod.GET)
	public Object getAllFileByProNo(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProjDocService ser = (ProjDocService) getService();
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				Map map = actionForm.toMap();
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}

				List<Map<String, Object>> resultList = ser.getAllFileByProNo(map);
				ListResult result = new ListResult();
				if (resultList != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(resultList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}
	
	
	@RequestMapping(value = "listall", method = RequestMethod.GET)
	public Object listall(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProjDocService ser = (ProjDocService) getService();
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				Map map = actionForm.toMap();
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}

				List<Map<String, Object>> resultList = ser.listall(map);
				ListResult result = new ListResult();
				if (resultList != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(resultList);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	@Override
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Object delete(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProjDocService ser = (ProjDocService) getService();
				int iCount = ser.delete(actionForm.toVo());
				ResponseResult result = new ResponseResult();

				if (iCount > 0) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("删除成功");
				} else {

					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("删除出错");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "proDocStatistict", method = RequestMethod.GET)
	public Object queryProDoc(HttpServletRequest request,
			HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				UserToken token = (UserToken) request.getSession()
						.getAttribute(UserToken.KEY_TOKEN);
				ProjDocService docService = (ProjDocService) getService();

				Map map = actionForm.toMap();
				if (PwCloudOsConstant.DEPT_MANAGER_ROLE_CODE.equals(token
						.getRoleCode())) {
					map.put("deptCode", token.getUnitCode());
				}
				
				List<Map<String, Object>> docList = docService.queryProDoc(map);
				ListResult result = new ListResult();
				if (docList != null && docList.size() > 0) {

					result.setResultMsg("获取文档统计成功！");
					result.setStatusCode(ResponseConstants.OK);
					result.setList(docList);
				} else {

					result.setResultMsg("获取文档统计失败！");
					result.setStatusCode(ResponseConstants.NO_DATA);
				}

				return result;
			}
		});
	}

}
