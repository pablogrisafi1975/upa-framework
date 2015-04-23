package org.upasample0.upa;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class UpaServletConfig extends GuiceServletContextListener {

	private UpaServletModule upaServletModule;
	private static Logger logger = LoggerFactory.getLogger(UpaServletConfig.class);

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		String upaConfigImplClassName = servletContextEvent.getServletContext().getInitParameter("upa-config-impl");
		logger.debug("upaConfigImplClassName:" + upaConfigImplClassName);
		UpaConfig upaConfig = null;
		try {
			upaConfig = (UpaConfig) Class.forName(upaConfigImplClassName).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			logger.error("can not initialize {} ", upaConfigImplClassName, e);
			throw new UpaInitilizationException(e);
		}
		upaServletModule = new UpaServletModule(upaConfig);
		super.contextInitialized(servletContextEvent);
	}

	@Override
	protected Injector getInjector() {
		logger.debug("UpaServletConfig.getInjector called");
		return Guice.createInjector(upaServletModule);
	}

}
