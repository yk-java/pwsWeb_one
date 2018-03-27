package com.glens.eap.platform.framework.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.glens.eap.platform.framework.context.EAPContext;

public class EAPContextListener implements ServletContextListener {

	private static final Log logger = LogFactory.getLog(EAPContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled()) {
			logger.info("系统关闭中。。。");
		}
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled()) {
			logger.info("system started");
		}
		
		ServletContext context = event.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		EAPContext.setContext(ctx);
		
		String initialzationClassName = context.getInitParameter("initialzationClass");
		if (initialzationClassName != null 
				&& !"".equals(initialzationClassName)) {
			try {
				Class initialzationClass = ClassUtils.forName(initialzationClassName, null);
				EAPInitialzation initialzation = (EAPInitialzation) initialzationClass.newInstance();
				initialzation.initialize(context);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LinkageError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
