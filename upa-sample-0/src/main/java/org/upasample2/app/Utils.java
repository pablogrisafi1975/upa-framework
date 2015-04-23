package org.upasample2.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	private static Logger logger = LoggerFactory.getLogger(Utils.class);

	public static List<String> UNREADABLE_METHODS = Arrays.asList("getPathTranslated", "getPathInfo", "getServletPath",
			"getReader");

	public static void printInfo(ServletRequest req, ServletResponse res) throws IOException {
		final Map<String, String[]> parameterMap = req.getParameterMap();
		final PrintWriter writer = res.getWriter();
		writer.println("parameterMap:{");
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			writer.println("  " + entry.getKey() + ":" + Arrays.toString(entry.getValue()));
		}
		writer.println("}");

		for (Method method : req.getClass().getMethods()) {
			if (method.getName().startsWith("get") && method.getParameterCount() == 0
					&& !UNREADABLE_METHODS.contains(method.getName())) {
				try {
					writer.println(method.getName() + ":" + method.invoke(req, new Object[] {}));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					logger.error("Error reading method '{}'", method.getName(), e);
				}
			}

		}

	}

}
