package com.glens.eap.platform.framework.codeCenter;

public interface CodeWorkerFactory {

	public String SIMPLE_WORKER = "simple";
	
	public CodeWorker build(String name);
}
