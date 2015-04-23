package org.upasample0.app.upa;

import org.upasample0.app.controllers.JobController;
import org.upasample0.app.controllers.SearchController;
import org.upasample0.app.controllers.UserController;
import org.upasample0.app.services.SearchService;
import org.upasample0.app.services.UserService;

import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(JobController.class);
		bind(SearchController.class);
		bind(UserController.class);
		bind(SearchService.class);
		bind(UserService.class);

	}

}
