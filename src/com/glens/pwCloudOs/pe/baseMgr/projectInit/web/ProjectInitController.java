package com.glens.pwCloudOs.pe.baseMgr.projectInit.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.pe.baseMgr.materialType.web.MaterialTypeForm;
import com.glens.pwCloudOs.pe.baseMgr.projectInit.service.ProjectInitService;
import com.glens.pwCloudOs.pe.baseMgr.projectInit.vo.ProjectInitVo;


@FormProcessor(clazz="com.glens.pwCloudOs.pe.baseMgr.projectInit.web.ProjectInitForm")
@RequestMapping("pmsServices/pe/baseMgr/projectInit")
public class ProjectInitController extends EAPJsonAbstractController {
	@Override
	@RequestMapping(value="add", method=RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
						CodeWorker.SIMPLE_CODE_WORKER);
				String typecode = codeWorker.createCode("M");
				ProjectInitForm form=(ProjectInitForm)actionForm;
				form.setMctViewCode(typecode);
				
				
				//String proNos=form.getProNos();
				//String proNames=form.getProNames();
				
				//String pns[]=proNos.split(",");
				//String pas[]=proNames.split(",");
				
				ProjectInitService ser=(ProjectInitService)getService();
				KeyResult result = new KeyResult();

				
				
				ProjectInitVo v=new ProjectInitVo();
				v.setMctViewCode(typecode);
				v.setProMctCodes(form.getProMctCodes());
				v.setProMctNames(form.getProMctNames());
				v.setDeviceTypeCode(form.getDeviceTypeCode());
				v.setMctViewName(form.getMctViewName());
				v.setCompanyCode(form.getCompanyCode());
				v.setProNos(form.getProNos());
				v.setProNames(form.getProNames());
				
				boolean ok =
					getService().insert(v);
				
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
	
	
	@RequestMapping(value="treeList", method=RequestMethod.GET)
	public Object treeList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				ProjectInitForm form=(ProjectInitForm)actionForm;
				
				ProjectInitService ser=(ProjectInitService)getService();
				
				List list=ser.treeList(form.getCompanyCode());
				
				
				List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();
				for(int i=0;i<list.size();i++){
					Map m=(Map)list.get(i);
					
					String categoryCode=m.get("categoryCode").toString();
					String categoryName=m.get("categoryName").toString();
					Map p1=new HashMap();
					p1.put("id", categoryCode);
					p1.put("name", categoryName);
					p1.put("pId", "-1");
					p1.put("nocheck", true);
					resultList.add(p1);
					
					
					List list1=ser.treeList1(form.getCompanyCode(),categoryCode);
					
					for(int j=0;j<list1.size();j++){
						Map m1=(Map)list1.get(j);
						String proNo=m1.get("proNo").toString();
						String proName=m1.get("proName").toString();
						
						Map p2=new HashMap();
						p2.put("id", proNo);
						p2.put("name", proName);
						p2.put("pId", categoryCode);
						p2.put("nocheck", true);
						resultList.add(p2);
						
						List list2=ser.treeList2(form.getCompanyCode(),categoryCode,proNo);
						
						for(int k=0;k<list2.size();k++){
							Map m2=(Map)list1.get(k);
							
							String mctCode=m1.get("mctCode").toString();
							String mctName=m1.get("mctName").toString();
							String deviceType=m1.get("deviceType").toString();
							
							Map p3=new HashMap();
							p3.put("id", mctCode);
							p3.put("name", mctName);
							p3.put("pId", proNo);
							p3.put("type", deviceType);
							p3.put("nocheck", false);
							p3.put("proName", proName);
							resultList.add(p3);
							
						}
					}
					
				}
				PageBeanResult result = new PageBeanResult();
				if (resultList != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(resultList);
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
