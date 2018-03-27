package com.glens.pwCloudOs.addrLib.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.beans.UserToken;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.utils.common.PingYinUtil;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.pwCloudOs.addrLib.dao.AddBaseMongoDao;
import com.mongodb.DBObject;
/**
 * 地址信息维护
 * @author MaDx
 *
 */
public class AddBaseService extends EAPAbstractService {
	private AddBaseMongoDao addBaseMongoDao;

	public void setAddBaseMongoDao(AddBaseMongoDao addBaseMongoDao) {
		this.addBaseMongoDao = addBaseMongoDao;
	}
	/**
	 * 审核
	 * @param params
	 * @param token
	 * @return
	 */
	public BeanResult audit(Map<String, Object> params, UserToken token){
		params.put("AUDITOR_CODE", token.getEmployeeCode());
		params.put("AUDITOR_NAME", token.getEmployeeName());
		int res = addBaseMongoDao.audit(params);
		if(res>0){
			return BeanResult.success("审核成功", res);
		}
		return BeanResult.fail("审核失败");
	}
	/**
	 * 查询
	 * @param params
	 * @return
	 */
	public BeanResult find(Map<String, Object> params){
		Map<String, Object> data = addBaseMongoDao.find(params);
		if(data!=null){
			return BeanResult.success("查询成功", data);
		}
		return BeanResult.fail("查询失败");
	}
	/**
	 * 新增
	 * @param params
	 * @param token
	 * @return
	 */
	public BeanResult insert(Map<String, Object> params, UserToken token){
		params.put("CREATER_CODE", token.getEmployeeCode());
		params.put("CREATER_NAME", token.getEmployeeName());
		String jsonStr = (String)params.get("jsonStr");// 扩展字段
		Map<String, Object> attrMap = JSONObject.parseObject(jsonStr);
		Iterator<String> it = attrMap.keySet().iterator();
		while (it.hasNext()) {

			String key = it.next();
			if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
				continue;
			}
			params.put(key, attrMap.get(key));
		}
		params.remove("jsonStr");//
		params.remove("searchName");// 去掉多余字段
		int res = addBaseMongoDao.insert(params);
		if(res>0){
			return BeanResult.success("新增成功", res);
		}
		return BeanResult.fail("新增失败");
	}
	/**
	 * 分页查询
	 * @param params
	 * @return
	 */
	public PageBeanResult queryByPage(Map<String, Object> params, int currentPage, int perpage){
		PageBean pageBean = addBaseMongoDao.queryByPage(params, currentPage, perpage);
		if(pageBean != null){
			return PageBeanResult.success("分页查询成功", pageBean);
		}
		return PageBeanResult.fail("分页查询失败");
		
	}
	/**
	 * 根据父编号查询地址
	 * @param params
	 * @return
	 */
	public BeanResult queryByParentCode(Map<String, Object> params){
		String addrCode = (String)params.get("ADDR_CODE");
		List<Map<String, Object>> result = addBaseMongoDao.getAddrsByParentAddrCode(addrCode);
		if(result != null){
			return BeanResult.success("分页查询成功", result);
		}
		return BeanResult.fail("分页查询失败");
		
	}
	/**
	 * 搜索查询
	 * @param params
	 * @return
	 */
	public ListResult query(Map<String, Object> params){
		List<Map<String, Object>> res = addBaseMongoDao.query(params);
		if(res != null){
			return ListResult.success("搜索查询成功", res);
		}
		return ListResult.fail("搜索查询失败");
		
	}
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public BeanResult remove(Map<String, Object> params){
		int res = addBaseMongoDao.remove(params);
		if(res>0){
			return BeanResult.success("删除成功", res);
		}
		return BeanResult.fail("删除失败");
	}
	/**
	 * 修改
	 * @param params
	 * @return
	 */
	public BeanResult update(Map<String, Object> params){
		String jsonStr = (String)params.get("jsonStr");// 扩展字段
		Map<String, Object> attrMap = JSONObject.parseObject(jsonStr);
		Iterator<String> it = attrMap.keySet().iterator();
		while (it.hasNext()) {

			String key = it.next();
			if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
				continue;
			}
			params.put(key, attrMap.get(key));
		}
		params.remove("jsonStr");//
		params.remove("searchName");// 去掉多余字段
		int res = addBaseMongoDao.update(params);
		if(res>0){
			return BeanResult.success("删除成功", res);
		}
		return BeanResult.fail("删除失败");
	}
	
	/**
	 * 修改层户结构
	 * @param params
	 * @return
	 */
	public BeanResult updateCenghu(Map<String, Object> params){
		
		Map<String, Object> updateParams =new HashMap<String, Object>();
		
		String arrayJson = (String)params.get("arrayJson");
		Map<String, Object> attrMap = JSONObject.parseObject(arrayJson);
		Iterator<String> it = attrMap.keySet().iterator();
		while (it.hasNext()) {

			String key = it.next();
			if(attrMap.get(key)==null || "".equals(attrMap.get(key))){
				continue;
			}
			updateParams.put(key, attrMap.get(key));
		}
		//params.remove("jsonStr");//
		//params.remove("searchName");// 去掉多余字段
		updateParams.put("ADDR_CODE", params.get("ADDR_CODE"));
		
		int res = addBaseMongoDao.update(updateParams);
		if(res>0){
			return BeanResult.success("成功", res);
		}
		return BeanResult.fail("失败");
	}
	
	//修改图片
	public BeanResult deleteImg(Map<String, Object> params){
		
		Map<String, Object> updateParams =new HashMap<String, Object>();
		
		
		updateParams.put("ADDR_CODE", params.get("ADDR_CODE"));
		updateParams.put("pic", params.get("pic"));
		
		int res = addBaseMongoDao.update(updateParams);
		if(res>0){
			return BeanResult.success("成功", res);
		}
		return BeanResult.fail("失败");
	}
	
	
	/**
	 * 修改位置信息
	 * @param params
	 * @return
	 */
	public BeanResult updatePosition(Map<String, Object> params){
		int res = addBaseMongoDao.updatePosition(params);
		if(res>0){
			return BeanResult.success("修改位置成功", res);
		}
		return BeanResult.fail("修改位置失败");
	}
	
	
	public void batchAddrNameConvertPinyin() {
		
		List<Map<String, Object>> addrList = this.addBaseMongoDao.getAddrList(new HashMap<String, Object>());
		if (addrList != null) {
			
			for (Map<String, Object> addrItem : addrList) {
				
				String addrName = (String) addrItem.get("ADDR_NAME");
				String pinyin = PingYinUtil.getPingYin(addrName);
				String firstLetter = PingYinUtil.getFirstSpell(addrName);
				addrItem.put("PIN_YIN", pinyin);
				addrItem.put("FIRST_LETTER", firstLetter);
				
				this.addBaseMongoDao.saveAddr(addrItem);
			}
		}
	}
	
	public Map<String, Object> getAddrDistribute(Map<String, Object> params) {
		
		Map<String, Object> resultList = this.addBaseMongoDao.getAddrDistributeList(params);
		
		return resultList;
	}
	
	public List<Map<String, Object>> getPlateAddrList(Map<String, Object> params) {
		
		List<Map<String, Object>> addrList = this.addBaseMongoDao.getPlateAddrList(params);
		
		return addrList;
	}
}
