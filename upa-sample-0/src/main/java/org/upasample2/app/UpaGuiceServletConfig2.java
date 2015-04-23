package org.upasample2.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class UpaGuiceServletConfig2 extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ControllersServletModule(), new ServicesModule());
	}

}
