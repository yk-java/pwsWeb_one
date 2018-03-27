package com.glens.pwCloudOs.addrLib.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.addrLib.service.AddPreliminaryAuditService;
import com.glens.pwCloudOs.addrLib.vo.AddPreliminaryAuditVo;


@FormProcessor(clazz = "com.glens.pwCloudOs.addrLib.web.AddPreliminaryAuditForm")
@RequestMapping("/pmsServices/addrLib/addPreliminaryAudit")
public class AddPreliminaryAuditController extends EAPJsonAbstractController {

	@Override
	@RequestMapping(value="list", method=RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PageBean page = getService().queryForPage(actionForm.toMap(), 
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
	
	
	@RequestMapping(value="updateStatus", method=RequestMethod.POST)
	public Object updateStatus(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				AddPreliminaryAuditService ser=(AddPreliminaryAuditService)getService();
				AddPreliminaryAuditForm  form=(AddPreliminaryAuditForm)actionForm;
				String roleCode=form.getRoleCode();
				int iCount=0;
				//R20161207143642413959	  地名核准员	
				//R20161207143724432478	  地名审核员	
				//R20161207143741114245  地名审批员	
				if(roleCode.equals("R20161207143642413959")){
					
					Map m=new HashMap();
					m.put("rowid", form.getRowid());
					m.put("acceptStatus", form.getAcceptStatus());
					m.put("acceptorCode", form.getAcceptorCode());
					m.put("acceptorName", form.getAcceptorName());
					m.put("acceptSuggest", form.getAcceptSuggest());
					iCount=ser.updateAccept(m);
					
				}else if(roleCode.equals("R20161207143724432478")){
					Map m=new HashMap();
					m.put("rowid", form.getRowid());
					m.put("auditStatus", form.getAcceptStatus());
					m.put("auditorCode", form.getAcceptorCode());
					m.put("auditorName", form.getAcceptorName());
					m.put("auditSuggest", form.getAcceptSuggest());
					iCount=ser.updateAudit(m);
					
				}else if(roleCode.equals("R20161207143741114245")){
					Map m=new HashMap();
					m.put("rowid", form.getRowid());
					m.put("auditStatus2", form.getAcceptStatus());
					m.put("auditorCode2", form.getAcceptorCode());
					m.put("auditorName2", form.getAcceptorName());
					m.put("auditSuggest2", form.getAcceptSuggest());
					iCount=ser.updateAudit2(m);
				}
				
				
				
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
	
	/**
	 * 保存地名申请信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveApplicat", method = RequestMethod.POST)
	public Object saveApplicat(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				AddPreliminaryAuditService service = (AddPreliminaryAuditService) getService();
				try {
					String formCode = service.saveApplicat(
							(AddPreliminaryAuditVo) actionForm.toVo(),
							getRootPath(request));
					if (formCode!= null && formCode.length()>0) {
						return BeanResult.success("上传成功", formCode);
					} else {
						return BeanResult.fail("上传失败");
					}
				} catch (Exception e) {
					e.printStackTrace();
					return BeanResult.fail("上传失败");
				}
			}
		});
	}
	
	
	/**
	 * 根据申请单号查询审批结果
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getApplicat", method = RequestMethod.POST)
	public Object getApplicat(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				AddPreliminaryAuditService service = (AddPreliminaryAuditService) getService();
				try {
					Map params = actionForm.toMap();
					String formCode = (String)params.get("formCode");
					AddPreliminaryAuditVo vo = service.getApplicat(formCode);
					if (vo!= null) {
						return BeanResult.success("获取成功", vo);
					} else {
						return BeanResult.fail("未查到");
					}
				} catch (Exception e) {
					e.printStackTrace();
					return BeanResult.fail("获取失败");
				}
			}
		});
	}
	
	
	
}
