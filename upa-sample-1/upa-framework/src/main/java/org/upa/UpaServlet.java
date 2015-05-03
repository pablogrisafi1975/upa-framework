package org.upa;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.upa.file.UpaFile;

import com.google.gson.Gson;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
// only needed for uploading files
public class UpaServlet extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(UpaServlet.class);

	private static final long serialVersionUID = 1L;

	@Inject
	private Injector injector;

	@Inject
	private Gson gson;

	@Inject
	private UpaManager upaManager;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		logger.debug("servlet.init");

		upaManager.analize(injector.getBindings().entrySet());
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// logger.info("");
		// getRequestURI:/upa-sample-1-webapp/upa/search/findUser
		String name = findControllerName(request);
		logger.debug("controller name: '{}'", name);
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		Object returnObject = upaManager.invoke(httpServletRequest, httpServletResponse);
		if (returnObject instanceof UpaFile) {
			UpaFile upaFile = (UpaFile) returnObject;
			upaFile.writeResponse(httpServletResponse);
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			String json = gson.toJson(returnObject);
			response.getWriter().write(json);
		}

		// UpaUtils.printInfo(req, res);
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
