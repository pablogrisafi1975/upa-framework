package org.upasample2.app;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.upasample2.app.controllers.JobController;
import org.upasample2.app.controllers.SearchController;
import org.upasample2.app.controllers.UserController;

import com.google.inject.Binding;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Singleton;

@Singleton
public class RouterServlet extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(RouterServlet.class);

	@Inject
	private JobController jobController;

	@Inject
	private SearchController searchController;

	@Inject
	private UserController userController;

	private static final long serialVersionUID = 1L;

	@Inject
	private Injector injector;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		logger.debug("servlet.init");

		Map<Key<?>, Binding<?>> bindings = injector.getBindings();

		for (Entry<Key<?>, Binding<?>> binding : bindings.entrySet()) {

			Class<?> rawType = binding.getKey().getTypeLiteral().getRawType();
			if (rawType.getSimpleName().endsWith("Controller")) {
				logger.debug("Controller {} ", rawType);
				Object controller = binding.getValue().getProvider().get();
				for (Method method : controller.getClass().getMethods()) {
					if (!method.getDeclaringClass().equals(Object.class)) {
						logger.debug("\tMethod: {}.{} ", rawType, method.getName());
						for (Parameter parameter : method.getParameters()) {
							String paremeterName = parameter.getName();
							String paremeterType = parameter.getType().getCanonicalName();
							logger.debug("\t\tparameter: {} {} ", paremeterType, paremeterName);
						}
						logger.debug("\t\treturns: {} ", method.getReturnType());

					}
				}
			}
			// logger.debug("key: {} value:{}", binding.getKey(),
			// binding.getValue());
		}
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// logger.info("");
		// getRequestURI:/upa-sample-0/upa/search/findUser

		String name = findControllerName(req);
		logger.debug("controller name: '{}'", name);
		List<Object> controllers = Arrays.asList(jobController, userController, searchController);

		logger.info("jobController.class {}", jobController.getClass());
		// Utils.printInfo(req, res);
	}

	private String findControllerName(ServletRequest req) {
		String requestURI = ((HttpServletRequest) req).getRequestURI();
		int upaIndex = requestURI.indexOf("/upa/") + 5;
		int endIndex = requestURI.indexOf('/', upaIndex);
		if (upaIndex != -1 && endIndex != -1) {
			return requestURI.substring(upaIndex, endIndex);
		}
		return null;
	}
}
