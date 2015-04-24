package org.upasample2.app;

import java.time.LocalDate;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.upasample2.app.controllers.JobController;
import org.upasample2.app.controllers.SearchController;
import org.upasample2.app.controllers.UserController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;

public class ControllersServletModule extends ServletModule {
	@Override
	protected void configureServlets() {
		bind(ControllerManager.class);
		bind(UserController.class);
		bind(SearchController.class);
		bind(JobController.class);
		bind(ConvertUtilsBean.class);
		serve("/upa/*").with(RouterServlet.class);
	}

	@Provides
	@Singleton
	public Gson gsonProvider() {
		GsonBuilder gson = new GsonBuilder();
		gson.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
		return gson.create();
	}
}
