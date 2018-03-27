/**
 * @Title: PmChangeService.java
 * @Package com.glens.pwCloudOs.pm.baseMgr.pmChange.service
 * @Description: TODO
 * Copyright: Copyright (c) 2016 
 * Company:南京量为石信息科技有限公司
 * @author 
 * @date 2016-6-8 上午11:56:50
 * @version V1.0
 */


package com.glens.pwCloudOs.pm.baseMgr.pmChange.service;

import java.util.HashMap;
import java.util.Map;

import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pm.baseMgr.pmChange.dao.PmChangeDao;
import com.glens.pwCloudOs.pm.baseMgr.pmChange.vo.PmModifyRecordVo;

/**
 * 
 * 
 * @author 
 * @version V1.0
 */

public class PmChangeService extends EAPAbstractService {

	public boolean registeProChanged(ValueObject o) {
		
		boolean _ok = false;
		PmChangeDao pmChangeDao = (PmChangeDao) getDao();
		PmModifyRecordVo vo = (PmModifyRecordVo) o;
		if (vo.getFieldContents() != null 
				&& !"".equals(vo.getFieldContents()) 
				&& vo.getValueContents() != null 
				&& !"".equals(vo.getValueContents())) {
			
			String[] fields = vo.getFieldContents().split(",");
			String[] values = vo.getValueContents().split(",");
			Map<String, String> params = new HashMap<String, String>();
			params.put("proNo", vo.getProNo());
			for (int i = 0;i < fields.length;i++) {
				
				params.put(fields[i], values[i]);
			}
			
			_ok = pmChangeDao.insert(vo);
			if (_ok) {
				
				int updateCount = pmChangeDao.updatePmBase(params);
				_ok = updateCount > 0;
			}
		}
		
		return _ok;
	}
	
}
