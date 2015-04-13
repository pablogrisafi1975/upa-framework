package org.upasample0.upa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class MyUpaServletConfig extends GuiceServletContextListener {

	private static Logger logger = LoggerFactory.getLogger(MyUpaServletConfig.class);

	@Override
	protected Injector getInjector() {
		logger.debug("calling MyUpaServletConfig.getInjector");
		return Guice.createInjector(new ServletModule() {
			@Override
			protected void configureServlets() {
				serve("/upa/*").with(MyUpaServlet.class);
			}
		});
	}

}
