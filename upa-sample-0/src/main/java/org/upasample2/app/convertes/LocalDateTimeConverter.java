package org.upasample2.app.convertes;

import java.time.LocalDateTime;

import org.apache.commons.beanutils.Converter;

public class LocalDateTimeConverter implements Converter {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> type, Object value) {
		if (LocalDateTime.class.isAssignableFrom(type) && value instanceof String) {
			return (T) LocalDateTime.parse((String) value);
		}
		return null;
	}

}
