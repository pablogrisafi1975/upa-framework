package org.upasample0.upa;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;

public class UpaServletModule extends ServletModule {
	private static Logger logger = LoggerFactory.getLogger(UpaServletModule.class);

	private final Map<String, Method> exposedMethods = new HashMap<>();

	public UpaServletModule(UpaConfig upaConfig) {
		List<Class<?>> controllers = upaConfig.getControllers();
		for (Class<?> controllerClass : controllers) {
			final String controllerName = controllerClass.getSimpleName().replace("Controller", "");
			for (Method method : controllerClass.getMethods()) {
				if (!method.getDeclaringClass().equals(Object.class)) {
					exposedMethods.put(controllerName + "/" + method.getName(), method);
				}
			}
		}

		logger.debug("Exposed methods:");
		exposedMethods.keySet().stream().sorted().forEach(k -> {
			logger.debug(k + " -> " + exposedMethods.get(k));
		});
	}

	@Override
	protected void configureServlets() {
		serve("/upa/*").with(UpaServlet.class);
	}

	@Provides
	public Map<String, Method> getExposedMethods() {
		return this.exposedMethods;
	}

}