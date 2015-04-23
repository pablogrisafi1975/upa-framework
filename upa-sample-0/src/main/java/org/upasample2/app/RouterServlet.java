package org.upasample2.app;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class RouterServlet extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(RouterServlet.class);

	private static final long serialVersionUID = 1L;

	@Inject
	private Injector injector;

	@Inject
	private Gson gson;

	@Inject
	private ControllerManager controllerManager;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		logger.debug("servlet.init");

		controllerManager.analize(injector.getBindings().entrySet());
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// logger.info("");
		// getRequestURI:/upa-sample-0/upa/search/findUser
		String name = findControllerName(req);
		logger.debug("controller name: '{}'", name);
		Object returnObject = controllerManager.invoke(((HttpServletRequest) req).getRequestURI(),
				req.getParameterMap());
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		String json = gson.toJson(returnObject);
		res.getWriter().write(json);

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
