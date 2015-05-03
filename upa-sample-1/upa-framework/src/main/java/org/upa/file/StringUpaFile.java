package org.upa.file;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

/**
 * StringBuffered based. Uses UTF-8 without BOM as recommended by Unicode
 * standard <br/>
 * 2.6 Encoding Schemes ... Use of a BOM is neither required nor recommended
 * UTF-8 <see http://www.unicode.org/versions/Unicode5.0.0/ch16.pdf/>
 * 
 * @author Pablo
 *
 */
public class StringUpaFile implements UpaFile {
	private final String fileName;
	private final StringBuffer stringBuffer = new StringBuffer();

	public StringUpaFile(String fileName) {
		this.fileName = fileName;
	}

	public void append(String string) {
		stringBuffer.append(string);
	}

	@Override
	public void writeResponse(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		response.setContentLength(stringBuffer.length());
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", this.fileName));
		OutputStream out = response.getOutputStream();
		try (InputStream in = new ByteArrayInputStream(stringBuffer.toString().getBytes(StandardCharsets.UTF_8))) {
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
		}
		out.flush();

	}

	@Override
	public String toString() {
		return "StringUpaFile[fileName=" + this.fileName + "]";
	}

}
