package com.glens.eap.platform.framework.codeCenter.impl;

import com.glens.eap.platform.framework.codeCenter.CodeWorker;
import com.glens.eap.platform.framework.codeCenter.CodeWorkerFactory;

public class CodeWorkerFactoryImpl implements CodeWorkerFactory {

	@Override
	public CodeWorker build(String name) {
		// TODO Auto-generated method stub
		CodeWorker worker = null;
		if (CodeWorkerFactoryImpl.SIMPLE_WORKER.equals(name)) {
			
			worker = new SimpleCodeWorker();
		}
		
		return worker;
	}

}
