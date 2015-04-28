package org.upasample2.app.controllers;

import java.nio.charset.StandardCharsets;

import org.upasample2.app.file.HugeUpaFile;
import org.upasample2.app.file.StringUpaFile;
import org.upasample2.app.file.UpaFile;

public class FileController {
	/*-
	 * http://localhost:8080/upa-sample-0/upa/File/stringUpaFile?fileName=my+FileName%20with%20spaces.txt&&content=myFileContent
	 */
	public UpaFile stringUpaFile(String fileName, String content) {
		StringUpaFile stringUpaFile = new StringUpaFile(fileName);
		stringUpaFile.append(content);
		stringUpaFile.append("\r\n");
		stringUpaFile.append("á (aacute)\r\n");
		stringUpaFile.append("ñ (ntilde)\r\n");
		return stringUpaFile;
	}

	/*-
	 * http://localhost:8080/upa-sample-0/upa/File/hugeUpaFile4M
	 */
	public UpaFile hugeUpaFile4M() {
		HugeUpaFile hugeUpaFile = new HugeUpaFile("HugeUpaFile4M.txt", new HugeUpaFile.ContentGenerator() {
			int i = 0;

			@Override
			public byte[] generate() {
				i++;
				if (i < 60_000) {
					return (i + "\t a b c ch d e f g h i j k l ll m n ñ o p q r s t u v w x y z \r\n")
							.getBytes(StandardCharsets.UTF_8);
				}
				return new byte[] {};
			}
		});
		return hugeUpaFile;
	}

	/*-
	 * http://localhost:8080/upa-sample-0/upa/File/hugeUpaFile600M
	 */
	public UpaFile hugeUpaFile600M() {
		HugeUpaFile hugeUpaFile = new HugeUpaFile("HugeUpaFile600M.txt", new HugeUpaFile.ContentGenerator() {
			int i = 0;

			@Override
			public byte[] generate() {
				i++;
				if (i < 8_500_000) {
					return (i + "\t a b c ch d e f g h i j k l ll m n ñ o p q r s t u v w x y z \r\n")
							.getBytes(StandardCharsets.UTF_8);
				}
				return new byte[] {};
			}
		});
		return hugeUpaFile;
	}

}
