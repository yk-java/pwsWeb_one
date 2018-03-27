package com.glens.eap.platform.core.web;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.glens.eap.platform.core.annotation.ValueObjectProcessor;
import com.glens.eap.platform.core.beans.ValueObject;
import com.glens.eap.platform.core.utils.BeanUtilsEx;

public abstract class ControllerForm implements Serializable {
	
	protected String scope;
	
	protected EAPController controller;
	
	protected int currentPage;
	
	protected int perpage;
	
	protected HttpServletRequest request;

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public EAPController getController() {
		return controller;
	}

	public void setController(EAPController controller) {
		this.controller = controller;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPerpage() {
		return perpage;
	}

	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Map toMap() {
		
		Map params = this.doPreToMap();
		
		params.put("currentPage", currentPage);
		params.put("perpage", perpage);
		
		return params;
	}
	
	public ValueObject toVo() {
		
		ValueObject vo = createVo();
		try {
			BeanUtilsEx.copyProperties(vo, this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return vo;
	}
	
	protected ValueObject createVo() {
		
		ValueObjectProcessor component = this.getClass().getAnnotation(ValueObjectProcessor.class);
		if (component != null && !"".equals(component.clazz())) {
			
			String voClass = component.clazz();
			try {
				Class voClazz = Class.forName(voClass);
				ValueObject vo = (ValueObject) voClazz.newInstance();
				
				return vo;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new WebException("error !", e);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new WebException("error !", e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new WebException("error !", e);
			}
		}
		else {
			
			throw new WebException("error !");
		}
	}
	
	protected abstract Map doPreToMap();
	protected abstract void doPostRequest(HttpServletRequest request);
	
	public abstract Object getGenerateKey();
}
