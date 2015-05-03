package org.upa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.upa.beanutilsconverters.LocalDateConverter;
import org.upa.beanutilsconverters.LocalDateTimeConverter;
import org.upa.beanutilsconverters.LocalTimeConverter;
import org.upa.beanutilsconverters.ZonedDateTimeConverter;
import org.upa.gsonadapters.LocalDateAdapter;
import org.upa.gsonadapters.LocalDateTimeAdapter;
import org.upa.gsonadapters.LocalTimeAdapter;
import org.upa.gsonadapters.ZonedDateTimeAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class UpaModule extends AbstractModule {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub

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
