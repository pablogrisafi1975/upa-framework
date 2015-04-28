package org.upasample2.app.file;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface UpaFile {

	void writeResponse(HttpServletResponse httpServletResponse) throws IOException;
}
