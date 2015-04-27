package org.upasample2.app.file;

import java.io.InputStream;

public interface UpaFile {
	String getFileName();

	InputStream getContent();

	Integer getLength();
}
