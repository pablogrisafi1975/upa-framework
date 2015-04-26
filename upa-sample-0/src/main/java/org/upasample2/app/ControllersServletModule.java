package org.upasample2.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.upasample2.app.controllers.BasicTypesController;
import org.upasample2.app.controllers.JobController;
import org.upasample2.app.controllers.SearchController;
import org.upasample2.app.controllers.UserController;
import org.upasample2.app.convertes.LocalDateConverter;
import org.upasample2.app.convertes.LocalDateTimeConverter;
import org.upasample2.app.convertes.LocalTimeConverter;
import org.upasample2.app.convertes.ZonedDateTimeConverter;
import org.upasample2.app.gsonadapters.LocalDateAdapter;
import org.upasample2.app.gsonadapters.LocalDateTimeAdapter;
import org.upasample2.app.gsonadapters.LocalTimeAdapter;
import org.upasample2.app.gsonadapters.ZonedDateTimeAdapter;

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
		bind(BasicTypesController.class);
		serve("/upa/*").with(RouterServlet.class);
	}

	@Provides
	@Singleton
	public Gson gsonProvider() {
		GsonBuilder gson = new GsonBuilder();
		gson.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
		gson.registerTypeAdapter(LocalTime.class, new LocalTimeAdapter());
		gson.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
		gson.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter());
		return gson.create();
	}

	@Provides
	@Singleton
	public ConvertUtilsBean convertUtilsBeanProvider() {
		ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean() {
			@SuppressWarnings("unchecked")
			@Override
			public Object convert(String value, @SuppressWarnings("rawtypes") Class clazz) {
				if (clazz.isEnum()) {
					return Enum.valueOf(clazz, value);
				} else {
					return super.convert(value, clazz);
				}
			}
		};
		convertUtilsBean.register(new LocalDateConverter(), LocalDate.class);
		convertUtilsBean.register(new LocalTimeConverter(), LocalTime.class);
		convertUtilsBean.register(new LocalDateTimeConverter(), LocalDateTime.class);
		convertUtilsBean.register(new ZonedDateTimeConverter(), ZonedDateTime.class);
		return convertUtilsBean;
	}
}
