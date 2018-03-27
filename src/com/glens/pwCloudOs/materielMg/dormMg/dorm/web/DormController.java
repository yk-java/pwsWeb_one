/**
 * @Title: DormController.java
 * @Package com.glens.pwCloudOs.materielMg.dormMg.dorm.web
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-24 下午5:34:53
 * @version V1.0
 */


package com.glens.pwCloudOs.materielMg.dormMg.dorm.web;

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
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.materielMg.assetMg.asset.service.AssetService;
import com.glens.pwCloudOs.materielMg.assetMg.asset.web.AssetForm;
import com.glens.pwCloudOs.materielMg.dormMg.dorm.service.DormService;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */
@FormProcessor(clazz="com.glens.pwCloudOs.materielMg.dormMg.dorm.web.DormForm")
@RequestMapping("pmsServices/materielMg/dormMg/dorm")
public class DormController extends EAPJsonAbstractController {

	@RequestMapping(value="dormBed", method=RequestMethod.GET)
	public Object selectBedByDormCode(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				DormService dormService = (DormService) getService();
				List<Map<String, String>> bedList = dormService.selectBedByDormCode(actionForm.toMap());
				ListResult result = new ListResult();
				if (bedList != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取床位成功！");
					result.setList(bedList);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取床位失败！");
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="rentDorm", method=RequestMethod.GET)
	public Object selectRentDorm(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DormService dormService = (DormService) getService();
				List<Map<String, Object>> rentDormList = dormService.selectRentDorm(actionForm.toMap());
				ListResult result = new ListResult();
				if (rentDormList != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取宿舍成功！");
					result.setList(rentDormList);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("获取宿舍失败！");
				}
				
				return result;
			}
		});
	}
	
	//宿舍合同列表
	@RequestMapping(value="getContract", method=RequestMethod.GET)
	public Object getContract(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DormService dormService = (DormService) getService();
				
				DormForm form=(DormForm)actionForm;
				
				List list = dormService.getContract(form.getDormCode());
				
				ListResult result = new ListResult();
				if (list != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
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
	
	//宿舍开票列表
	@RequestMapping(value="getBilling", method=RequestMethod.GET)
	public Object getBilling(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DormService dormService = (DormService) getService();
				
				DormForm form=(DormForm)actionForm;
				
				List list = dormService.getBilling(form.getDormCode());
				
				ListResult result = new ListResult();
				if (list != null) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
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
	
	@RequestMapping(value="addContract", method=RequestMethod.POST)
	public Object addContract(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DormService dormService = (DormService) getService();
				KeyResult result = new KeyResult();
				DormForm form=(DormForm)actionForm;
				
				boolean ok;
				try {
					ok = dormService.addContract(actionForm.toVo(),getRootPath(request));
					
					if (ok) {
						
						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("新增成功");
						result.setGenerateKey(actionForm.getGenerateKey());
					}
					else {
						
						result.setStatusCode(ResponseConstants.SERVER_ERROR);
						result.setResultMsg("新增失败");
					}
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return result;
			}
			
		});
	}
	
	@RequestMapping(value="addInvoice", method=RequestMethod.POST)
	public Object addInvoice(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DormService dormService = (DormService) getService();
				KeyResult result = new KeyResult();
				DormForm form=(DormForm)actionForm;
				
				boolean ok;
				try {
					ok = dormService.insertInvoice(actionForm.toVo(),getRootPath(request));
					
					if (ok) {
						
						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("新增成功");
						result.setGenerateKey(actionForm.getGenerateKey());
					}
					else {
						
						result.setStatusCode(ResponseConstants.SERVER_ERROR);
						result.setResultMsg("新增失败");
					}
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return result;
			}
			
		});
	}
	
	
	@RequestMapping(value="addPerson", method=RequestMethod.POST)
	public Object addPerson(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DormService dormService = (DormService) getService();
				KeyResult result = new KeyResult();
				DormForm form=(DormForm)actionForm;
				
				String employeeCodes=form.getEmployeeCode();
				String employeeNames=form.getEmployeeName();
				
				String tempcodes[]=employeeCodes.split(",");
				String tempnames[]=employeeNames.split(",");
				String mess="";
				boolean ishave=false;
				for(int i=0;i<tempcodes.length;i++){
					ishave=dormService.getPersonDorm(tempcodes[i]); //判断是否有在租的宿舍
					
					if(!ishave){
						mess=tempnames[i]+"已有在租的宿舍!";
						break;
					}
					if(i==tempcodes.length-1){
						ishave=true;
					}
				}
				if(ishave){
					boolean ok=true;
					for(int i=0;i<tempcodes.length;i++){
						ok = dormService.checkDorm(actionForm.toMap(),tempcodes[i],tempnames[i]);
					}
				//	boolean ok = dormService.checkDorm(actionForm.toMap());
					if (ok) {
						
						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("添加成功");
						result.setGenerateKey(actionForm.getGenerateKey());
					}
					else {
						result.setStatusCode("101");
						result.setResultMsg("床位已满");
					}
				}else{
					result.setStatusCode("100");
					result.setResultMsg(mess);
					result.setGenerateKey(actionForm.getGenerateKey());
				}
				return result;
			}
			
		});
	}
	
	@RequestMapping(value="fastAddPerson", method=RequestMethod.POST)
	public Object fastAddPerson(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DormService dormService = (DormService) getService();
				KeyResult result = new KeyResult();
				DormForm form=(DormForm)actionForm;
				
				String employeeCodes=form.getEmployeeCode();
				String employeeNames=form.getEmployeeName();
				String proNos=form.getProNo();
				String proNames=form.getProName();
				String rentDate=form.getRentDate();
				String returnDate=form.getReturnDate();
				
				String tempcodes[]=employeeCodes.split(",");
				String tempnames[]=employeeNames.split(",");
				String tempPronos[]=proNos.split(",");
				String tempPronames[]=proNames.split(",");
				String tempRentDate[]=rentDate.split(",");
				String tempReturnDate[]=returnDate.split(",");
				
				String mess="";
				boolean ishave=false;
				for(int i=0;i<tempcodes.length;i++){
					ishave=dormService.getPersonDorm(tempcodes[i]); //判断是否有在租的宿舍
					
					if(!ishave){
						mess=tempnames[i]+"已有在租的宿舍!";
						break;
					}
					if(i==tempcodes.length-1){
						ishave=true;
					}
				}
				if(ishave){
					boolean ok=true;
					for(int i=0;i<tempcodes.length;i++){
						//ok = dormService.checkDorm1(actionForm.toMap(),tempcodes[i],tempnames[i]);
						ok = dormService.checkDorm1(actionForm.toMap(), tempPronos[i], tempPronames[i], tempRentDate[i], tempReturnDate[i], tempcodes[i], tempnames[i]);
					}
				//	boolean ok = dormService.checkDorm(actionForm.toMap());
					if (ok) {
						result.setStatusCode(ResponseConstants.OK);
						result.setResultMsg("添加成功");
						result.setGenerateKey(actionForm.getGenerateKey());
					}
					else {
						result.setStatusCode("101");
						result.setResultMsg("床位已满");
					}
				}else{
					result.setStatusCode("100");
					result.setResultMsg(mess);
					result.setGenerateKey(actionForm.getGenerateKey());
				}
				return result;
			}
			
		});
	}
	
	
	@RequestMapping(value="deleteContract", method=RequestMethod.POST)
	public Object deleteContract(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DormService dormService = (DormService) getService();
				KeyResult result = new KeyResult();
				DormForm form=(DormForm)actionForm;
				boolean isdelete=dormService.deleteContract(form.getContractScanimg());
				//ResponseResult result = new ResponseResult();
				
				if (isdelete) {
					
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
	
	@RequestMapping(value="deleteInvoice", method=RequestMethod.POST)
	public Object deleteInvoice(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DormService dormService = (DormService) getService();
				KeyResult result = new KeyResult();
				DormForm form=(DormForm)actionForm;
				boolean isdelete=dormService.deleteInvoice(form.getInvoiceScanimg());
				//ResponseResult result = new ResponseResult();
				
				if (isdelete) {
					
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
	
	
	@RequestMapping(value="deletePerson", method=RequestMethod.POST)
	public Object deletePerson(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DormService dormService = (DormService) getService();
				KeyResult result = new KeyResult();
				DormForm form=(DormForm)actionForm;
				
				String dormCode=form.getDormCode();
				String employeeCode=form.getEmployeeCode();
				String returnDate=form.getReturnDate();
				boolean isdelete=dormService.deletePerson(employeeCode,returnDate);
				
				
				
				
				if(isdelete){
					
					List resultList=dormService.getDormRentNums(dormCode);
					if(resultList.size()==0){
						dormService.updateNum(dormCode,1); //移除之后  判断宿舍是否还有人  没人的把状态改为闲置
					}else{
						dormService.updateNum(dormCode,2);
					}
					
				}
				
				//ResponseResult result = new ResponseResult();
				
				if (isdelete) {
					
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
	
	
	//项目人员都移除之后  则清除这个项目
	@RequestMapping(value="removePro", method=RequestMethod.POST)
	public Object removePro(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DormService dormService = (DormService) getService();
				KeyResult result = new KeyResult();
				DormForm form=(DormForm)actionForm;
				
				String dormCode=form.getDormCode();
				String proNo=form.getProNo();
				String proName=form.getProName();
				
				boolean isdelete=dormService.removePro(dormCode,proNo,proName);
				
				//ResponseResult result = new ResponseResult();
				
				if (isdelete) {
					
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
	
	
	
	@RequestMapping(value="hasbedList", method=RequestMethod.GET)
	public Object hasbedList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				Map<String, Object> params = new HashMap<String, Object>();
				params.putAll(PageBean.getPageParamsFromReq(request));
				String areaName = request.getParameter("areaName");
				String dormName = request.getParameter("dormName");
				params.put("areaName", areaName);
				params.put("dormName", dormName);
				DormService dormService = (DormService) getService();
				PageBean page = dormService.queryHasbedForPage(params);
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
	
	@RequestMapping(value="dormScrap", method=RequestMethod.POST)
	public Object dormScrap(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				
				DormService dormService = (DormService) getService();
				DormForm form=(DormForm)actionForm;
				
				Map<String, Object> asset = dormService.dormScrap(form.getDormCode(),form.getRefundaMount(),form.getRefundDate(),form.getRefundDesc());
				BeanResult result = new BeanResult();
				if (asset != null && asset.size() > 0) {
					
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("宿舍退租成功!");
					result.setData(asset);
				}
				else {
					
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("宿舍退租失败!");
				}
				
				return result;
			}
		});
	}
	
	@RequestMapping(value="getVehicleList", method=RequestMethod.GET)
	public Object getVehicleList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				DormService ser=(DormService)getService();
				
				PageBean page = ser.getVehicleList(actionForm.toMap(), 
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
	
}
