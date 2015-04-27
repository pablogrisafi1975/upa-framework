package org.upasample2.app.controllers;

import org.upasample2.app.file.StringUpaFile;
import org.upasample2.app.file.UpaFile;

public class FileController {
	/*-
	 * http://localhost:8080/upa-sample-0/upa/File/stringUpaFile?fileName=my+FileName%02with%20spaces.txt&&content=myFileContent
	 */
	public UpaFile stringUpaFile(String fileName, String content) {
		StringUpaFile stringUpaFile = new StringUpaFile(fileName);
		stringUpaFile.append(content);
		stringUpaFile.append("\r\n");
		stringUpaFile.append("á (aacute)\r\n");
		stringUpaFile.append("ñ (ntilde)\r\n");
		return stringUpaFile;
	}

}
