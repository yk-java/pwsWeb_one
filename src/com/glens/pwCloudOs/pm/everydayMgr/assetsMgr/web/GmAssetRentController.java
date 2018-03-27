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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.service.GmAssetRentService;
import com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.vo.GmAssetRentVo;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

@FormProcessor(clazz="com.glens.pwCloudOs.pm.everydayMgr.assetsMgr.web.GmAssetRentForm")
@RequestMapping("pmsServices/pm/everydayMgr/assetsMgr/gmAssetRent")
public class GmAssetRentController extends EAPJsonAbstractController {
	
	@Override
	@RequestMapping(value="add", method=RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				GmAssetRentService service = (GmAssetRentService)getService();
				GmAssetRentVo vo = (GmAssetRentVo)actionForm.toVo();
				
				boolean ok = service.insert(vo);
				KeyResult result = new KeyResult();
				if (ok) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				}
				else {
					
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("新增失败");
				}
				
				return result;
			}
			
		});
	}
	
	@RequestMapping(value="updateRentStatus", method=RequestMethod.POST)
	public Object updateRentStatus(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				Long rowid = 0l;
				rowid = Long.parseLong(request.getParameter("rowid"));
				String rentStatus = request.getParameter("rentStatus");
				String flowStatus = request.getParameter("flowStatus");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("rowid", rowid);
				params.put("rentStatus", rentStatus);
				params.put("flowStatus", flowStatus);
				GmAssetRentService service = (GmAssetRentService)getService();
				int iCount = service.updateRentStatus(params);
				
				ResponseResult result = new ResponseResult();
				
				if (iCount > 0) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
				}
				else {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}
				
				return result;
			}
			
		});
	}
	
	@RequestMapping(value="rentChange", method=RequestMethod.POST)
	public Object rentChange(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				Long rowid = 0l;
				rowid = Long.parseLong(request.getParameter("rowid"));
				String changeLoanEmployeename = request.getParameter("changeLoanEmployeename");
				String changeLoanEmployeecode = request.getParameter("changeLoanEmployeecode");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("rowid", rowid);
				params.put("changeLoanEmployeename", changeLoanEmployeename);
				params.put("changeLoanEmployeecode", changeLoanEmployeecode);
				GmAssetRentService service = (GmAssetRentService)getService();
				int iCount = service.rentChange(params);
				
				ResponseResult result = new ResponseResult();
				
				if (iCount > 0) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
				}
				else {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}
				
				return result;
			}
			
		});
	}
	
	@RequestMapping(value="settingAsset", method=RequestMethod.POST)
	public Object settingAsset(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				Long rowid = 0l;
				rowid = Long.parseLong(request.getParameter("rowid"));
				String assetCode = request.getParameter("assetCode");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("rowid", rowid);
				params.put("assetCode", assetCode);
				GmAssetRentService service = (GmAssetRentService)getService();
				int iCount = service.settingAsset(params);
				
				ResponseResult result = new ResponseResult();
				
				if (iCount > 0) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
				}
				else {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}
				
				return result;
			}
			
		});
	}
	
	@RequestMapping(value="returnAsset", method=RequestMethod.POST)
	public Object returnAsset(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				Long rowid = 0l;
				rowid = Long.parseLong(request.getParameter("rowid"));
				String assetCode = request.getParameter("assetCode");
				String rentStatus = request.getParameter("rentStatus");
				String location = request.getParameter("location");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("rowid", rowid);
				params.put("assetCode", assetCode);
				params.put("rentStatus", rentStatus);
				params.put("location", location);
				GmAssetRentService service = (GmAssetRentService)getService();
				int iCount = service.returnAsset(params);
				
				ResponseResult result = new ResponseResult();
				
				if (iCount > 0) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
				}
				else {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}
				
				return result;
			}
			
		});
	}
	
	@RequestMapping(value="addFaster", method=RequestMethod.POST)
	public Object addFaster(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				String assetCodes = request.getParameter("assetCodes");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("assetCodes", assetCodes);
				GmAssetRentService service = (GmAssetRentService)getService();
				int iCount = service.addFaster((GmAssetRentVo)actionForm.toVo(), params);
				
				ResponseResult result = new ResponseResult();
				
				if (iCount > 0) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
				}
				else {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}
				
				return result;
			}
			
		});
	}
}
