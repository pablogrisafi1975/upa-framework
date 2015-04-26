package org.upasample2.app;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;

public class GsonTest {

	private Gson gson = new ControllersServletModule().gsonProvider();

	@DataProvider
	public Object[][] gsonDataProvider() {
		//@formatter:off
		return new Object[][]{
			{"123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789", BigInteger.class, new BigInteger("123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789"), false},
			{"1234567891234567891", Long.class, Long.valueOf(1234567891234567891L), false},
			{"1234567891", Integer.class, Integer.valueOf(1234567891), false},
			{"12345", Short.class, Short.decode("12345"), false},
			{"123", Byte.class, Byte.valueOf("123"), false},
			
			{"123456789123456789123456789123456789123456789123456789123456789.123456789123456789123456789123456789123456789123456789123456789", BigDecimal.class, new BigDecimal("123456789123456789123456789123456789123456789123456789123456789.123456789123456789123456789123456789123456789123456789123456789"), false},
			{"1.23456789123456", Double.class, Double.valueOf("1.23456789123456"), false},
			{"12.345", Float.class, Float.valueOf("12.345"), false},
			
			{"true", Boolean.class, Boolean.TRUE, false},
			{"SATURDAY", DayOfWeek.class, DayOfWeek.SATURDAY, true},
			
			{"2007-01-22", LocalDate.class, LocalDate.of(2007, 1, 22), true},
			{"22:33:44", LocalTime.class, LocalTime.of(22, 33, 44), true},
			{"2007-01-22T22:33:44", LocalDateTime.class, LocalDateTime.of(2007, 1, 22, 22, 33, 44), true},
			{"2007-01-22T22:33:44.567+01:00", ZonedDateTime.class, ZonedDateTime.of(2007, 1, 22, 22, 33, 44, 567000000, ZoneOffset.ofHoursMinutes(1, 0)), true},
			
			
		};
		//@formatter:on
	}

	@Test(dataProvider = "gsonDataProvider")
	public void fromJson(String json, Class<?> clazz, Object expected, boolean forcedString) throws Exception {
		if (forcedString) {
			assertThat(gson.fromJson('"' + json + '"', clazz)).isEqualTo(expected);
		} else {
			assertThat(gson.fromJson(json, clazz)).isEqualTo(expected);
		}
	}

	@Test(dataProvider = "gsonDataProvider")
	public void toJson(String expected, Class<?> clazz, Object object, boolean forcedString) throws Exception {
		if (forcedString) {
			assertThat(gson.toJson(object)).isEqualTo('"' + expected + '"');
		} else {
			assertThat(gson.toJson(object)).isEqualTo(expected);
		}
	}

}
