package com.glens.pwCloudOs.pe.baseMgr.remouldScheme.web;

import java.util.ArrayList;
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
import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.pe.baseMgr.remouldScheme.service.RemouldSchemeService;
import com.glens.pwCloudOs.pe.baseMgr.remouldScheme.vo.RemouldSchemeVo;


@FormProcessor(clazz="com.glens.pwCloudOs.pe.baseMgr.remouldScheme.web.RemouldSchemeForm")
@RequestMapping("pmsServices/pe/baseMgr/remouldScheme")
public class RemouldSchemeController extends EAPJsonAbstractController {

	
	
	@Override
	@RequestMapping(value="list", method=RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				PageBean page = 
					getService().queryForPage(actionForm.toMap(), 
							actionForm.getCurrentPage(), actionForm.getPerpage());
				List list=page.getList();
				
				RemouldSchemeService ser=(RemouldSchemeService)getService();
				
				
				
				RemouldSchemeForm form=(RemouldSchemeForm)actionForm;
				
				
				List<Map<String,Object>> typelist=ser.getALLType(form.getCompanyCode());//所有的类型
				
				List<Map<String,Object>> materialList=ser.materialList(form.getCompanyCode(),"");
				
				List lastlist=new ArrayList();
				//Map<String,Object> resultMap=new HashMap<String, Object>();
				List returnList=new ArrayList();
				for(int i=0;i<list.size();i++){
					RemouldSchemeVo vo=(RemouldSchemeVo)list.get(i);
					//System.out.println(vo.getRemouldSchemeCode());
					String code=vo.getRemouldSchemeCode();
					String name=vo.getRemouldSchemeName();
					String desc=vo.getRemouldSchemeDesc();
					Map map=new HashMap();
					
					for(int j=0;j<materialList.size();j++){
						
						Map m=materialList.get(j);
						map.put("remouldSchemeName", name);
						map.put("remouldSchemeCode", code);
						map.put("remouldSchemeDesc", desc);
						String recode="";//方案编码
						if(m.get("remouldschemecode")!=null){
							recode=m.get("remouldschemecode").toString();
						}
						
						
						String count="";
						if(m.get("needcount")!=null){
							count=m.get("needcount").toString();
						}
						String materialName=""; //类型名称
						if(m.get("typeName")!=null){
							materialName=m.get("typeName").toString();
						}
						
						String materialCode=""; //类型代码
						if(m.get("typeCode")!=null){
							materialCode=m.get("typeCode").toString();
						}
						if(recode.equals("")){
							/*map.put("remouldSchemeName", name);
							map.put("remouldSchemeCode", code);
							map.put("remouldSchemeDesc", desc);*/
							map.put(materialCode, "");
							continue;
						}
						if(recode.equals(code)){
							/*map.put("remouldSchemeName", name);
							map.put("remouldSchemeCode", code);
							map.put("remouldSchemeDesc", desc);*/
							map.put(materialCode, count);
						}
					}
					
					returnList.add(map);
				}
				
				lastlist.add(returnList);
				lastlist.add(typelist);
				
				
				PageBeanResult result = new PageBeanResult();
				
				if (lastlist != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(lastlist);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				
				return result;
			}
			
		});
	}
	
	
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
				String code = codeWorker.createCode("R");
				
				RemouldSchemeForm form=(RemouldSchemeForm)actionForm;
				form.setRemouldSchemeCode(code);
				boolean ok = 
					getService().insert(actionForm.toVo());
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
				
				RemouldSchemeService ser=(RemouldSchemeService)getService();
				int iCount = 
						ser.delete(actionForm.toVo());
				
				RemouldSchemeForm form=(RemouldSchemeForm)actionForm;
				ser.deleteMaterial(form.getRemouldSchemeCode(),form.getCompanyCode());
				
				
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
				
				RemouldSchemeVo v=(RemouldSchemeVo)vo;
				
				RemouldSchemeService ser=(RemouldSchemeService)getService();
				List<Map<String,Object>> materialList=ser.materialList(v.getCompanyCode(),v.getRemouldSchemeCode());
				List<Map<String,Object>> alllist=ser.getALLType(v.getCompanyCode());
				
				
				List<Map<String,Object>> returnlist=new ArrayList<Map<String,Object>>();
				for(int i=0;i<alllist.size();i++){
					  Map ms=alllist.get(i);
					  String typeCode1=ms.get("typeCode").toString();
					  for(int j=0;j<materialList.size();j++){
						  Map ms1=materialList.get(j);
						  String typeCode2=ms1.get("typeCode").toString();
						  String needcount=ms1.get("needcount").toString();
						  if(typeCode1.equals(typeCode2)){
							  ms.put("needcount", needcount);
						  }
					  } 
					  returnlist.add(ms);
				}
				
				Map m=new HashMap();
				m.put("vo", vo);
				m.put("list", returnlist);
				
				BeanResult result = new BeanResult();
				if (vo != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(m);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}
				
				return result;
			}
			
		});
	}

	
	@Override
	@RequestMapping(value="update", method=RequestMethod.POST)
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				RemouldSchemeService ser=(RemouldSchemeService)getService();
				int iCount =ser.update(actionForm.toVo());
				
				RemouldSchemeForm form=(RemouldSchemeForm)actionForm;
				//先删除  再新增
				ser.deleteMaterial(form.getRemouldSchemeCode(), form.getCompanyCode());
				
				
				String codes=form.getMaterialCodes();
				String values=form.getMaterialValues();
				
				if(!values.equals("")){
					String strVal[]=values.split(",");
					String strCodes[]=codes.split(",");
					
					for(int i=0;i<strVal.length;i++){
						System.out.println(strVal[i]);
						
						Map m=new HashMap();
						m.put("companyCode", form.getCompanyCode());
						m.put("remouldSchemeCode", form.getRemouldSchemeCode());
						m.put("materialTypeCode",strCodes[i]);
						m.put("needcount",strVal[i]);
						ser.addMaterial(m);
					}
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

	
}
