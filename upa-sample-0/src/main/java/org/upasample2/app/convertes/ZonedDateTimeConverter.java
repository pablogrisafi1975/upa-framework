package org.upasample2.app.convertes;

import java.time.ZonedDateTime;

import org.apache.commons.beanutils.Converter;

public class ZonedDateTimeConverter implements Converter {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> type, Object value) {
		if (ZonedDateTime.class.isAssignableFrom(type) && value instanceof String) {
			return (T) ZonedDateTime.parse((String) value);
		}
		return null;
	}

}
