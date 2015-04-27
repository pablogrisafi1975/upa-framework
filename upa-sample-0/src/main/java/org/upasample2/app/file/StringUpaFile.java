package org.upasample2.app.file;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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

	@Override
	public String getFileName() {
		return fileName;
	}

	@Override
	public InputStream getContent() {
		return new ByteArrayInputStream(stringBuffer.toString().getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public Integer getLength() {
		return stringBuffer.length();
	}

	public void append(String string) {
		stringBuffer.append(string);
	}

}
