package org.upasample2.app;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Binding;
import com.google.inject.Key;
import com.google.inject.Singleton;

@Singleton
public class ControllerManager {
	private static Logger logger = LoggerFactory.getLogger(ControllerManager.class);

	@Inject
	private ConvertUtilsBean convertUtilsBean;

	private final Map<String, Target> targetMap = new HashMap<>();

	static class SimpleBinding {
		private final Class<?> controllerClass;
		private final Object controller;

		SimpleBinding(Class<?> controllerClass, Object controller) {
			this.controllerClass = controllerClass;
			this.controller = controller;
		}
	}

	static class Target {
		private final Class<?> controllerClass;
		final Object controller;
		final Method method;

		public Target(Class<?> controllerClass, Object controller, Method method) {
			this.controllerClass = controllerClass;
			this.controller = controller;
			this.method = method;
		}

	}

	public void analize(Set<Entry<Key<?>, Binding<?>>> bindingSet) {
		//@formatter:off
		List<SimpleBinding> simpleBindingList = bindingSet.stream()
			.filter(binding -> binding.getKey().getTypeLiteral().getRawType().getSimpleName().endsWith("Controller"))
			.map(binding -> new SimpleBinding(binding.getKey().getTypeLiteral().getRawType(), binding.getValue().getProvider().get()))
			.collect(Collectors.toList());

		simpleBindingList.stream()
			.forEach(simpleBinding -> {
				Stream<Method> methods = Arrays.stream(simpleBinding.controllerClass.getMethods());
				methods
					.filter(method -> !method.getDeclaringClass().equals(Object.class))
					.forEach(method -> targetMap.put(
								simpleBinding.controllerClass.getSimpleName().substring(0, simpleBinding.controllerClass.getSimpleName().length() - 10) + "/" + method.getName(), 
								new Target(simpleBinding.controllerClass, simpleBinding.controller, method) ));
				});
			
		targetMap.entrySet().stream()
			.sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
			.forEach(entry -> {
				Method method = entry.getValue().method;
				logger.debug("{} -> {}.{}", entry.getKey(), entry.getValue().controllerClass.getName(), method.getName());
				Arrays.stream(method.getParameters())
					.sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
					.forEach(parameter -> {
						String parameterName = parameter.getName();
						String parameterType = parameter.getParameterizedType().getTypeName();
						logger.debug("\t\tparameter: {} {} ", parameterType, parameterName);
					});
				logger.debug("\t\treturns: {} ", method.getGenericReturnType().getTypeName());
			});
			//@formatter:on

	}

	public Object invoke(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		String requestURI = httpServletRequest.getRequestURI();
		Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
		String key = findKey(requestURI);
		logger.debug("key:{}", key);
		Target target = targetMap.get(key);
		if (target == null) {
			logger.error("No target for route {}", key);
			return null;
		} else {
			Method method = target.method;
			Object[] args = (Object[]) Array.newInstance(Object.class, method.getParameterCount());
			int i = 0;
			for (Parameter parameter : method.getParameters()) {
				String[] strings = parameterMap.get(parameter.getName());
				Class<?> parameterType = parameter.getType();
				if (HttpServletRequest.class.isAssignableFrom(parameterType)) {
					args[i] = httpServletRequest;
				} else if (HttpServletResponse.class.isAssignableFrom(parameterType)) {
					args[i] = httpServletResponse;
				} else if (strings == null) {
					args[i] = null;
				} else {
					if (parameterType.isArray()) {
						args[i] = convertUtilsBean.convert(strings, parameterType.getComponentType());
					} else if (List.class.isAssignableFrom(parameterType)) {
						ParameterizedType parameterizedType = (ParameterizedType) parameter.getParameterizedType();
						args[i] = Arrays.asList(convertUtilsBean.convert(strings,
								(Class<?>) parameterizedType.getActualTypeArguments()[0]));
					} else {
						String argument = strings[0];
						args[i] = convertUtilsBean.convert(argument, parameterType);
					}
				}
				i++;
			}
			try {
				logger.debug("args:{}", Arrays.toString(args));
				Object returnObject = method.invoke(target.controller, args);
				logger.debug("returnObject:{}", returnObject);
				return returnObject;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				logger.error("error invoking {}", key, e);
				return null;
			}

		}

	}

	private String findKey(String requestURI) {
		int upaIndex = requestURI.indexOf("/upa/") + 5;
		if (upaIndex != -1) {
			return requestURI.substring(upaIndex);
		}
		return null;
	}
}
