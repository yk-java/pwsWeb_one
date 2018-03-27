package com.glens.pwCloudOs.pe.boxCheck.service;

import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.context.EAPContext;
import com.glens.eap.platform.framework.service.impl.EAPAbstractService;
import com.glens.pwCloudOs.pe.boxCheck.vo.PeBcCheckBatchVo;

public class PeBcCheckBatchService extends EAPAbstractService {
	@Override
	public boolean insert(Object params) {
		PeBcCheckBatchVo vo = (PeBcCheckBatchVo)params;
		CodeWorker codeWorker = (CodeWorker) EAPContext.getContext().getBean(
				CodeWorker.SIMPLE_CODE_WORKER);
		vo.setBatchCode(codeWorker.createCode("BAT"));
		return this.dao.insert(vo);
	}
}
