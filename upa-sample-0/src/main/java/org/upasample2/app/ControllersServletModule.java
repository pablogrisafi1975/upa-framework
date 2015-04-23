package org.upasample2.app;

import org.upasample2.app.controllers.JobController;
import org.upasample2.app.controllers.SearchController;
import org.upasample2.app.controllers.UserController;

import com.google.inject.servlet.ServletModule;

public class ControllersServletModule extends ServletModule {
	@Override
	protected void configureServlets() {
		bind(UserController.class);
		bind(SearchController.class);
		bind(JobController.class);
		serve("/upa/*").with(RouterServlet.class);
	}
}
