package org.upasample2.app.controllers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class BasicTypesController {

	/*-
	 * http://localhost:8080/upa-sample-0/upa/BasicTypes/enums?aBoolean=true&dayOfWeek=SATURDAY
	 */
	public Map<String, Object> enums(Boolean aBoolean, DayOfWeek dayOfWeek) {
		Map<String, Object> map = new HashMap<>();
		map.put("aBoolean", aBoolean);
		map.put("dayOfWeek", dayOfWeek);
		return map;
	}

	/*-
	 * http://localhost:8080/upa-sample-0/upa/BasicTypes/integers?aBigInteger=1234567890&aLong=123456789&anInteger=12345678
	  &aShort=1234567&aByte=123
	 */
	public Map<String, Object> integers(BigInteger aBigInteger, Long aLong, Integer anInteger, Short aShort, Byte aByte) {
		Map<String, Object> map = new HashMap<>();
		map.put("aBigInteger", aBigInteger);
		map.put("aLong", aLong);
		map.put("anInteger", anInteger);
		map.put("aShort", aShort);
		map.put("aByte", aByte);
		return map;
	}

	/*-
	 * http://localhost:8080/upa-sample-0/upa/BasicTypes/decimals?aBigDecimal=1.234567890&aDouble=12.3456789&aFloat=123.45678
	 */
	public Map<String, Object> decimals(BigDecimal aBigDecimal, Double aDouble, Float aFloat) {
		Map<String, Object> map = new HashMap<>();
		map.put("aBigDecimal", aBigDecimal);
		map.put("aDouble", aDouble);
		map.put("aFloat", aFloat);
		return map;
	}

	/*-
	 * http://localhost:8080/upa-sample-0/upa/BasicTypes/strings?aString=this%20is%20the%20string&aChar=g
	 */
	public Map<String, Object> strings(String aString, Character aChar) {
		Map<String, Object> map = new HashMap<>();
		map.put("aString", aString);
		map.put("aChar", aChar);
		return map;
	}

	/*-
	 * http://localhost:8080/upa-sample-0/upa/BasicTypes/times?aZonedDateTime=2007-01-22T22:33:44.567%2B01:00&aLocalDateTime=2007-01-22T22:33:44&aLocalTime=22:33:44&aLocalDate=2007-01-22
	 */
	public Map<String, Object> times(ZonedDateTime aZonedDateTime, LocalDateTime aLocalDateTime, LocalDate aLocalDate,
			LocalTime aLocalTime) {
		Map<String, Object> map = new HashMap<>();
		map.put("aZonedDateTime", aZonedDateTime);
		map.put("aLocalDatetime", aLocalDateTime);
		map.put("aLocalDate", aLocalDate);
		map.put("aLocalTime", aLocalTime);
		/*- legacy date related types are NOT handled at all and serialized like crap
		map.put("java.util.Date.from(aZonedDateTime.toInstant())", java.util.Date.from(aZonedDateTime.toInstant())); ->"Jan 22, 2007 6:33:44 PM"
		map.put("java.sql.Date.from(aZonedDateTime.toInstant())", java.sql.Date.from(aZonedDateTime.toInstant())); ->"Jan 22, 2007 6:33:44 PM"
		map.put("java.util.GregorianCalendar.from(aZonedDateTime)", java.util.GregorianCalendar.from(aZonedDateTime));->{"year":2007,"month":0,"dayOfMonth":22,"hourOfDay":22,"minute":33,"second":44}
		 */
		return map;
	}

}
