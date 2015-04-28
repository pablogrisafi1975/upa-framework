package org.upasample2.app.file;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * Write directly to output stream to minimize memory use
 * 
 * @author Pablo
 *
 */
public class HugeUpaFile implements UpaFile {
	public interface ContentGenerator {
		public byte[] generate();
	}

	private final String fileName;
	private final ContentGenerator contentGenerator;

	public HugeUpaFile(String fileName, ContentGenerator contentGenerator) {
		this.fileName = fileName;
		this.contentGenerator = contentGenerator;
	}

	@Override
	public void writeResponse(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", this.fileName));
		OutputStream out = response.getOutputStream();

		byte[] partialContent = contentGenerator.generate();
		while (partialContent.length > 0) {
			try (InputStream in = new ByteArrayInputStream(partialContent)) {
				byte[] buffer = new byte[4096];
				int length;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
			}
			partialContent = contentGenerator.generate();
		}
		out.flush();

	}

	@Override
	public String toString() {
		return "HugeUpaFile[fileName=" + this.fileName + "]";
	}

}
