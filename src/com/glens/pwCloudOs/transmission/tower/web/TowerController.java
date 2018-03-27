package com.glens.pwCloudOs.transmission.tower.web;

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
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.transmission.tower.service.TowerService;


@FormProcessor(clazz="com.glens.pwCloudOs.transmission.tower.web.TowerForm")
@RequestMapping("pmsServices/transmission/tower")
public class TowerController extends EAPJsonAbstractController {
	
	/**
	 *  获取电压和线路
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getVoltageLevel", method=RequestMethod.GET)
	public Object getVoltageLevel(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				TowerService towerService = (TowerService) getService();
				List<Map<String, String>> proPhaseList = towerService.getVoltageLevel(actionForm.toMap());
				ListResult result = new ListResult();
				if (proPhaseList != null) {
					if(proPhaseList.size()>0){
						result.setResultMsg("获取电压等级成功");
						result.setStatusCode(ResponseConstants.OK);
						result.setList(proPhaseList);
					}else{
						result.setResultMsg("未获取到数据");
						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setList(proPhaseList);
					}
				}
				else {
					
					result.setResultMsg("获取电压等级失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	
	/**
	 * 获取设备列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getTowers", method=RequestMethod.GET)
	public Object getTowers(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				TowerService towerService = (TowerService) getService();
				List<Map<String, String>> proPhaseList = towerService.getTowers(actionForm.toMap());
				ListResult result = new ListResult();
				if (proPhaseList != null) {
					if(proPhaseList.size()>0){
						result.setResultMsg("获取杆塔成功");
						result.setStatusCode(ResponseConstants.OK);
						result.setList(proPhaseList);
					}else{
						result.setResultMsg("未获取到数据");
						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setList(proPhaseList);
					}
				}
				else {
					
					result.setResultMsg("获取杆塔失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	
	/**
	 * 获取设备详情和图片
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getTowersDetail", method=RequestMethod.GET)
	public Object getTowersDetail(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				TowerService towerService = (TowerService) getService();
				Map<String, String> data = towerService.getTowerDetail(actionForm.toMap());
				BeanResult result = new BeanResult();
				
				if (data != null) {
						result.setResultMsg("获取杆塔成功");
						result.setStatusCode(ResponseConstants.OK);
						result.setData(data);
				}
				else {
					
					result.setResultMsg("获取杆塔失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	
	/**
	 * 获取所有的电压
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getDianYa", method=RequestMethod.GET)
	public Object getDianYa(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				TowerService towerService = (TowerService) getService();
				List<Map<String, String>> list = towerService.getDianYa(actionForm.toMap());
				//BeanResult result = new BeanResult();
				ListResult result = new ListResult();
				if (list != null) {
					if(list.size()>0){
						result.setResultMsg("获取电压成功");
						result.setStatusCode(ResponseConstants.OK);
						result.setList(list);
					}else{
						result.setResultMsg("未获取到数据");
						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setList(list);
					}
				}
				else {
					
					result.setResultMsg("获取杆塔失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	
	
	/**
	 * 根据电压获取线路
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getLineByDianya", method=RequestMethod.GET)
	public Object getLineByDianya(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				TowerService towerService = (TowerService) getService();
				List<Map<String, String>> list = towerService.getLineByDianya(actionForm.toMap());
				//BeanResult result = new BeanResult();
				ListResult result = new ListResult();
				if (list != null) {
					if(list.size()>0){
						result.setResultMsg("获取线路成功");
						result.setStatusCode(ResponseConstants.OK);
						result.setList(list);
					}else{
						result.setResultMsg("未获取到数据");
						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setList(list);
					}
				}
				else {
					
					result.setResultMsg("获取线路失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	
	
	/**
	 * 根据线路获取设备
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getTowerslist", method=RequestMethod.GET)
	public Object getTowerslist(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				TowerService towerService = (TowerService) getService();
				List<Map<String, String>> list = towerService.getTowerslist(actionForm.toMap());
				//BeanResult result = new BeanResult();
				ListResult result = new ListResult();
				if (list != null) {
					if(list.size()>0){
						result.setResultMsg("获取设备成功");
						result.setStatusCode(ResponseConstants.OK);
						result.setList(list);
					}else{
						result.setResultMsg("未获取到数据");
						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setList(list);
					}
				}
				else {
					
					result.setResultMsg("获取设备失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	
	
	@RequestMapping(value="addTower", method=RequestMethod.POST)
	public Object addTower(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				TowerService towerService = (TowerService) getService();
				TowerForm form=(TowerForm)actionForm;
				CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
						CodeWorker.SIMPLE_CODE_WORKER);
				String defectCode = codeWorker.createCode("D");
				form.setDefectCode(defectCode);
				
				boolean ok=towerService.addTower(form.toMap());
				
				if(ok){
					towerService.addDeviceRange(form.toMap());
				}
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
	
	@RequestMapping(value="addPic", method=RequestMethod.POST)
	public Object addPic(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				TowerService towerService = (TowerService) getService();
				
				boolean ok=towerService.addPic(actionForm.toMap());
				
				
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
	
	
	
	
	/**
	 * 获取缺陷列表  state=0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getDefectList", method=RequestMethod.GET)
	public Object getDefectList(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				TowerService towerService = (TowerService) getService();
				List<Map<String, String>> list = towerService.getDefectList(actionForm.toMap());
				//BeanResult result = new BeanResult();
				ListResult result = new ListResult();
				if (list != null) {
					if(list.size()>0){
						result.setResultMsg("获取缺陷列表成功");
						result.setStatusCode(ResponseConstants.OK);
						result.setList(list);
					}else{
						result.setResultMsg("未获取到数据");
						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setList(list);
					}
				}
				else {
					
					result.setResultMsg("获取缺陷列表失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	
	
	
	/**
	 * 审核缺陷
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="checkDefect", method=RequestMethod.POST)
	public Object checkDefect(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				TowerService towerService = (TowerService) getService();
				int iCount = towerService.checkDefect(actionForm.toMap());
				
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
	 * 获取待消缺列表  state=1
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getPassList", method=RequestMethod.GET)
	public Object getPassList(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				TowerService towerService = (TowerService) getService();
				List<Map<String, String>> list = towerService.getPassList(actionForm.toMap());
				//BeanResult result = new BeanResult();
				ListResult result = new ListResult();
				if (list != null) {
					if(list.size()>0){
						result.setResultMsg("获取列表成功");
						result.setStatusCode(ResponseConstants.OK);
						result.setList(list);
					}else{
						result.setResultMsg("未获取到数据");
						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setList(list);
					}
				}
				else {
					
					result.setResultMsg("获取列表失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	
	
	/**
	 * 消除缺陷
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="workOutDefect", method=RequestMethod.POST)
	public Object workOutDefect(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				TowerService towerService = (TowerService) getService();
				int iCount = towerService.workOutDefect(actionForm.toMap());
				
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
	 * 根据范围查询列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="getAreaList", method=RequestMethod.GET)
	public Object getAreaList(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				TowerService towerService = (TowerService) getService();
				List<Map<String, String>> list = towerService.getAreaList(actionForm.toMap());
				//BeanResult result = new BeanResult();
				ListResult result = new ListResult();
				if (list != null) {
					if(list.size()>0){
						result.setResultMsg("获取列表成功");
						result.setStatusCode(ResponseConstants.OK);
						result.setList(list);
					}else{
						result.setResultMsg("未获取到数据");
						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setList(list);
					}
				}
				else {
					
					result.setResultMsg("获取列表失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	
	
	@RequestMapping(value="getTeam1", method=RequestMethod.GET)
	public Object getTeam1(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				TowerService towerService = (TowerService) getService();
				List<Map<String, String>> list = towerService.getTeam1(actionForm.toMap());
				//BeanResult result = new BeanResult();
				ListResult result = new ListResult();
				if (list != null) {
					if(list.size()>0){
						result.setResultMsg("获取列表成功");
						result.setStatusCode(ResponseConstants.OK);
						result.setList(list);
					}else{
						result.setResultMsg("未获取到数据");
						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setList(list);
					}
				}
				else {
					
					result.setResultMsg("获取列表失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	
	
	@RequestMapping(value="getTeam2", method=RequestMethod.GET)
	public Object getTeam2(HttpServletRequest request,
			HttpServletResponse response) {
		
		return this.process(request, response, new JsonProcessRequestHandler() {
			
			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				TowerService towerService = (TowerService) getService();
				List<Map<String, String>> list = towerService.getTeam2(actionForm.toMap());
				//BeanResult result = new BeanResult();
				ListResult result = new ListResult();
				if (list != null) {
					if(list.size()>0){
						result.setResultMsg("获取列表成功");
						result.setStatusCode(ResponseConstants.OK);
						result.setList(list);
					}else{
						result.setResultMsg("未获取到数据");
						result.setStatusCode(ResponseConstants.NO_DATA);
						result.setList(list);
					}
				}
				else {
					
					result.setResultMsg("获取列表失败");
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
				}
				
				return result;
			}
		});
	}
	
	
	
}
