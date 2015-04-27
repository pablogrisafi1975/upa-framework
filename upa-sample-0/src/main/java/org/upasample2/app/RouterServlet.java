package org.upasample2.app;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

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
import org.upasample2.app.file.UpaFile;

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
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// logger.info("");
		// getRequestURI:/upa-sample-0/upa/search/findUser
		String name = findControllerName(request);
		logger.debug("controller name: '{}'", name);
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		Object returnObject = controllerManager.invoke(httpServletRequest, httpServletResponse);
		if (returnObject instanceof UpaFile) {
			UpaFile upaFile = (UpaFile) returnObject;
			response.setContentType("application/octet-stream");
			if (upaFile.getLength() != null) {
				response.setContentLength((int) upaFile.getLength());
			}
			httpServletResponse.setHeader("Content-Disposition",
					String.format("attachment; filename=\"%s\"", upaFile.getFileName()));
			OutputStream out = response.getOutputStream();
			try (BufferedInputStream in = new BufferedInputStream(upaFile.getContent())) {
				byte[] buffer = new byte[4096];
				int length;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
			}
			out.flush();
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			String json = gson.toJson(returnObject);
			response.getWriter().write(json);
		}

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
