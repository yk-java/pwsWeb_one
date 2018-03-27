package com.glens.eap.platform.core.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.view.EAPView;
import com.glens.eap.platform.core.view.impl.EAPJsonView;
import com.glens.eap.platform.core.view.impl.EAPXmlView;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.core.web.EAPController;
import com.glens.eap.platform.core.web.WebException;

public class RequestUtils {

	private static final String DEFAULT_FORM_CLASS = "com.glens.eap.platform.core.web.DefaultCotrollerForm";
	
	private static final String DEFAULT_SCOPE = "request";
	
	public static ControllerForm createControllerForm(String methodName, 
			HttpServletRequest request, EAPController controller) {
		
		try {
			FormProcessor form = controller.getClass().getAnnotation(FormProcessor.class);
			String formClass = "";
			String scope = DEFAULT_SCOPE;
			if (form != null && form.clazz() != null 
					&& !"".equals(form.clazz())) {
				
				formClass = form.clazz();
				scope = form.scope();
			}
			else {
				
				formClass = DEFAULT_FORM_CLASS;
			}
			
			ControllerForm instance = lookupForm(request, formClass, scope);
			
			if (instance != null) {
				
				return (instance);
			}
			
			return createForm(request, formClass, scope);
			
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			throw new WebException("The Method can't invoke!", e);
		}
		
	}
	
	public static void processPathVariable(String methodName, 
			HttpServletRequest request, 
			ControllerForm controllerForm) {
		
		String pathInfo = request.getPathInfo();
		try {
			Method handleMethod = ReflectionUtils.findMethod(controllerForm.getController().getClass(), 
					methodName, new Class[]{HttpServletRequest.class, HttpServletResponse.class});
			RequestMapping mapping = handleMethod.getAnnotation(RequestMapping.class);
			if (mapping != null) {
				String[] paths = mapping.value();
				if (paths != null && paths.length > 0) {
					
					String path = paths[0];
					int i = path.indexOf("/");
					if (i > -1) {
						
						String pathMethod = path.substring(0, i);
						if (path.length() > (i + 1)) {
							
							String varNameString = path.substring(i + 1);
							int _i = pathInfo.indexOf(pathMethod + "/");
							if (_i > -1) {
								
								String varValueString = pathInfo.substring(_i + pathMethod.length() + 1);
								String[] varNames = varNameString.split("/");
								String[] varValues = varValueString.split("/");
								if (varNames != null && varNames.length > 0 
										&& varValues != null && varValues.length > 0 
										&& varNames.length == varValues.length) {
									HashMap properties = new HashMap();
									for (int j = 0;j < varValues.length;j++) {
										
										properties.put(varNames[j].substring(1, 
												varNames[j].length() - 2), varValues[i]);
									}
									
									BeanUtils.populate(controllerForm, properties);
								} 
							}
						}
					}
				}
			}
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			throw new WebException("The Method can't invoke!", e);
		}
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new WebException("Please check Form!", e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new WebException("Please check Form!", e);
		}
		
	}
	
	public static void processParameter(
			HttpServletRequest request, 
			ControllerForm controllerForm) {
		
		HashMap properties = new HashMap();
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			
			String name = (String) names.nextElement();
			if (!checkMutilpartParam(name, controllerForm)) {
				
				Object parameterValue = request.getParameter(name);
				
				properties.put(name, parameterValue);
			}
		}
		
		try {
			BeanUtils.populate(controllerForm, properties);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebException("Please check Form!", e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebException("Please check Form!", e);
		}
	}
	
	public static void processMutilpart(HttpServletRequest request, 
			ControllerForm controllerForm) {
		
		if (request != null && ServletFileUpload.isMultipartContent(request)) {
			
			MultipartHttpServletRequest multparReq = (MultipartHttpServletRequest) request;
			HashMap properties = new HashMap();
			Iterator<String> names = multparReq.getFileNames();
			try {
				while (names.hasNext()) {
					
					String name = (String) names.next();
					if (checkMutilpartParam(name, controllerForm)) {
						
						MultipartFile file = multparReq.getFile(name);
						if (file != null) {
							
							properties.put(name, file);
						}
						else {
							
							List<MultipartFile> files = multparReq.getFiles(name);
							if (files != null && files.size() > 0) {
								
								properties.put(name, files.toArray(new MultipartFile[0]));
							}
						}
					}
				}
				
				BeanUtils.populate(controllerForm, properties);
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public static ModelAndView pupolateView(String viewType, HttpServletRequest request, 
			HttpServletResponse response, EAPController controller, Object data) {
		
		ModelAndView modelAndView = null;
		try {
			if (EAPView.JSON_VIEW.equals(viewType)) {
				
				modelAndView = new ModelAndView();
				EAPJsonView view = new EAPJsonView();
				view.setData(data);
				modelAndView.setView(view);
			}
			else if (EAPView.JSP_VIEW.equals(viewType)) {
				
				if (data != null) {
					
					modelAndView = new ModelAndView(data.toString());
				}
			}
			else if (EAPView.XML_VIEW.equals(viewType)) {
				
				modelAndView = new ModelAndView();
				EAPXmlView view = new EAPXmlView();
				view.setData(data);
				modelAndView.setView(view);
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			throw new WebException("The Method can't invoke!", e);
		}
		
		return modelAndView; 
	}
	
	private static ControllerForm lookupForm(HttpServletRequest request, String formClass, String scope) {
		
		ControllerForm instance = null;
        HttpSession session = null;
        
        if ("request".equals(scope)) {
        	
        	instance = (ControllerForm) request.getAttribute(formClass);
        }
        else {
        	
        	session = request.getSession();
        	instance = (ControllerForm) session.getAttribute(formClass);
        }
        
        return (instance);
	}
	
	private static ControllerForm createForm(HttpServletRequest request, String formClass, String scope) {
		
		ControllerForm instance = null;
		
		try {
			Class controlFromClazz = Class.forName(formClass);
			instance = (ControllerForm) controlFromClazz.newInstance();
			instance.setScope(scope);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new WebException("Class:" + formClass + " not found", e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new WebException("Class:" + formClass + " not found", e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new WebException("Class:" + formClass + " not found", e);
		}
		
		return (instance);
	}
	
	private static boolean checkMutilpartParam(String paramName, ControllerForm form) {
		
		boolean multipartRequestParam = false;
		Method methods[] = form.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			
			Method method = methods[i];
			String name = method.getName();
			String key = "";
			if (name.startsWith("get"))
				key = name.substring(3);
			else if (name.startsWith("is"))
				key = name.substring(2);
			if (key.length() > 0 && Character.isUpperCase(key.charAt(0))
					&& method.getParameterTypes().length == 0) {
				
				if (key.length() == 1)
					key = key.toLowerCase();
				else if (!Character.isUpperCase(key.charAt(1)))
					key = (new StringBuilder(String.valueOf(key.substring(
							0, 1).toLowerCase()))).append(key.substring(1))
							.toString();
				
				if (key.equals(paramName) && method.getReturnType().isAssignableFrom(MultipartFile.class)) {
					
					multipartRequestParam = true;
				}
				else if (key.equals(paramName) && method.getReturnType().isAssignableFrom(MultipartFile[].class)) {
					
					multipartRequestParam = true;
				}
			}
		}
		
		return multipartRequestParam;
	}
}
