package org.upasample2.app.convertes;

import java.time.LocalTime;

import org.apache.commons.beanutils.Converter;

public class LocalTimeConverter implements Converter {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> type, Object value) {
		if (LocalTime.class.isAssignableFrom(type) && value instanceof String) {
			return (T) LocalTime.parse((String) value);
		}
		return null;
	}

}
