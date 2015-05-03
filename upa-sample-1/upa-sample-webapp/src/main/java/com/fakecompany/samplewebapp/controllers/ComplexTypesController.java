package com.fakecompany.samplewebapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComplexTypesController {
	/*-
	 * http://localhost:8080/upa-sample-1-webapp/upa/ComplexTypes/lists?integerList=10&integerList=11&integerList=12&integerArray=20&integerArray=21&integerArray=22
	 */
	public Map<String, Object> lists(List<Integer> integerList, Integer[] integerArray) {
		Map<String, Object> map = new HashMap<>();
		map.put("integerList", integerList);
		map.put("integerArray", integerArray);
		return map;
	}

	/*-
	 * http://localhost:8080/upa-sample-1-webapp/upa/ComplexTypes/maps?integerStringMap[1]=aaa&integerStringMap[2]=bbb&stringIntegerMap[AAAA]=111&stringIntegerMap[BBBB]=222
	 */
	public Map<String, Object> maps(Map<Integer, String> integerStringMap, Map<String, Integer> stringIntegerMap) {
		Map<String, Object> map = new HashMap<>();
		map.put("integerStringMap", integerStringMap);
		map.put("integerStringMap.key0.class", integerStringMap.keySet().iterator().next().getClass().getSimpleName());
		map.put("stringIntegerMap", stringIntegerMap);
		return map;
	}

	/*-
	 * http://localhost:8080/upa-sample-1-webapp/upa/ComplexTypes/reqresp?anInteger=222
	 */
	public Map<String, Object> reqresp(Integer anInteger, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Map<String, Object> map = new HashMap<>();
		map.put("anInteger", anInteger);
		map.put("httpServletRequest", httpServletRequest.getRequestURI());
		map.put("httpServletResponse", httpServletResponse.getStatus());
		return map;
	}

}
