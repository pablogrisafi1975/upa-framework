package org.upasample2.app.controllers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public class BasicTypesController {

	public String enums(Boolean aBoolean, DayOfWeek dayOfWeek) {
		return "";
	}

	public String integer(BigInteger aBigInteger, Long aLong, Integer anInteger, Short aShort, Byte aByte,
			Character aCharacter) {
		return "";
	}

	public String decimal(BigDecimal aBigDecimal, Double aDouble, Float aFloat) {
		return "";
	}

	public String string(String aString, char aChar) {
		return "";
	}

	public String time(ZonedDateTime aZonedDateTime, LocalDateTime aLocalDatetime, LocalDate aLocalDate,
			LocalTime aLocalTime) {
		return "zonedDateTime:" + aZonedDateTime + "localDatetime:" + aLocalDatetime + " localDate:" + aLocalDate
				+ " localTime:" + aLocalTime;
	}

}
