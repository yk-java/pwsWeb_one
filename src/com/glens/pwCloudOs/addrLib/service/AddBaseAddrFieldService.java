package com.glens.pwCloudOs.addrLib.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.pwCloudOs.addrLib.dao.AddBaseAddrFieldDao;
import com.glens.pwCloudOs.addrLib.dao.AddIndustryCategoryDao;
/**
 * 地址扩展字段查询
 * @author MaDx
 *
 */
public class AddBaseAddrFieldService extends EAPAbstractService {
	private AddIndustryCategoryDao addIndustryCategoryDao;
	
	public void setAddIndustryCategoryDao(
			AddIndustryCategoryDao addIndustryCategoryDao) {
		this.addIndustryCategoryDao = addIndustryCategoryDao;
	}

	/**
	 * 按行业类型分组，查询所有地址扩展字段信息
	 * @param params
	 * @return
	 */
	public BeanResult getAllAddrField(Map<String, Object> params){
		try {
			List<Map<String, Object>> categoryLst = addIndustryCategoryDao.queryForList(params);
			AddBaseAddrFieldDao dao = (AddBaseAddrFieldDao)getDao();
			List<Map<String, Object>> fieldLst = dao.queryForList(params);
			for (Iterator iterator = fieldLst.iterator(); iterator.hasNext();) {
				Map<String, Object> fieldModel = (Map<String, Object>) iterator.next();
				for (Iterator iterator2 = categoryLst.iterator(); iterator2.hasNext();) {
					Map<String, Object> categoryModel = (Map<String, Object>) iterator2
							.next();
					if(categoryModel.get("INDUSTRY_CODE").equals(fieldModel.get("INDUSTRY_CODE"))){
						if(categoryModel.get("children")==null){
							categoryModel.put("children", new ArrayList<Map<String, Object>>());
						}
						List<Map<String, Object>> children = (List<Map<String, Object>>)categoryModel.get("children");
						children.add(fieldModel);
					}
				}
			}
			return BeanResult.success("查询成功", categoryLst);
		} catch (Exception e) {
			e.printStackTrace();
			return BeanResult.fail("查询失败");
		}
	}
}
