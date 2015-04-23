package org.upasample0.upa;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.inject.Singleton;

@Singleton
public class UpaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private Map<String, Method> exposedMethods;

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

		res.getWriter().println("Hello world!! upa servlet" + exposedMethods);
	}
}
