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

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ConvertUtilsBeanTest {

	private ConvertUtilsBean convertUtilsBean = new ControllersServletModule().convertUtilsBeanProvider();

	@DataProvider
	public Object[][] convertUtilsBeanDataProvider() {
		//@formatter:off
		return new Object[][]{
			{"123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789", BigInteger.class, new BigInteger("123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789123456789")},
			{"1234567891234567891", Long.class, Long.valueOf(1234567891234567891L)},
			{"1234567891", Integer.class, Integer.valueOf(1234567891)},
			{"12345", Short.class, Short.decode("12345")},
			{"123", Byte.class, Byte.valueOf("123")},
			
			{"123456789123456789123456789123456789123456789123456789123456789.123456789123456789123456789123456789123456789123456789123456789", BigDecimal.class, new BigDecimal("123456789123456789123456789123456789123456789123456789123456789.123456789123456789123456789123456789123456789123456789123456789")},
			{"1.23456789123456", Double.class, Double.valueOf("1.23456789123456")},
			{"12.345", Float.class, Float.valueOf("12.345")},
			
			{"true", Boolean.class, Boolean.TRUE},
			{"SATURDAY", DayOfWeek.class, DayOfWeek.SATURDAY},
			
			{"2007-01-22", LocalDate.class, LocalDate.of(2007, 1, 22)},
			{"22:33:44", LocalTime.class, LocalTime.of(22, 33, 44)},
			{"2007-01-22T22:33:44", LocalDateTime.class, LocalDateTime.of(2007, 1, 22, 22, 33, 44)},
			{"2007-01-22T22:33:44.567+01:00", ZonedDateTime.class, ZonedDateTime.of(2007, 1, 22, 22, 33, 44, 567000000, ZoneOffset.ofHoursMinutes(1, 0))},
			
			
		};
		//@formatter:on
	}

	@Test(dataProvider = "convertUtilsBeanDataProvider")
	public void fromText(String text, Class<?> clazz, Object expected) throws Exception {
		assertThat(convertUtilsBean.convert(text, clazz)).isEqualTo(expected);
	}

}
