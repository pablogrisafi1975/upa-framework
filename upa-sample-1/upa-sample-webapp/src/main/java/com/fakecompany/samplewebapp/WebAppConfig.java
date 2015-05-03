package com.fakecompany.samplewebapp;

import org.upa.UpaModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class WebAppConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new WebAppModule(), new UpaModule());
	}

}
