package org.upa.beanutilsconverters;

import java.time.LocalDate;

import org.apache.commons.beanutils.Converter;

public class LocalDateConverter implements Converter {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> type, Object value) {
		if (LocalDate.class.isAssignableFrom(type) && value instanceof String) {
			return (T) LocalDate.parse((String) value);
		}
		return null;
	}

}
