package org.upasample2.app;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Part;

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

	public static String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE
																													// fix.
			}
		}
		return null;
	}

	public static byte[] readFully(InputStream is) throws IOException {
		return readFully(is, -1, true);
	}

	public static byte[] readFully(InputStream is, int length, boolean readAll) throws IOException {
		byte[] output = {};
		if (length == -1)
			length = Integer.MAX_VALUE;
		int pos = 0;
		while (pos < length) {
			int bytesToRead;
			if (pos >= output.length) { // Only expand when there's no room
				bytesToRead = Math.min(length - pos, output.length + 1024);
				if (output.length < pos + bytesToRead) {
					output = Arrays.copyOf(output, pos + bytesToRead);
				}
			} else {
				bytesToRead = output.length - pos;
			}
			int cc = is.read(output, pos, bytesToRead);
			if (cc < 0) {
				if (readAll && length != Integer.MAX_VALUE) {
					throw new EOFException("Detect premature EOF");
				} else {
					if (output.length != pos) {
						output = Arrays.copyOf(output, pos);
					}
					break;
				}
			}
			pos += cc;
		}
		return output;
	}

}
