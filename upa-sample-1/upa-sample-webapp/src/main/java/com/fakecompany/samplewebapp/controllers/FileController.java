package com.fakecompany.samplewebapp.controllers;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.upa.file.HugeUpaFile;
import org.upa.file.StringUpaFile;
import org.upa.file.UpaFile;
import org.upa.file.UpaUploadFile;

public class FileController {
	/*-
	 * http://localhost:8080/upa-sample-1-webapp/upa/File/stringUpaFile?fileName=my+FileName%20with%20spaces.txt&&content=myFileContent
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
	 * http://localhost:8080/upa-sample-1-webapp/upa/File/hugeUpaFile4M
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
	 * http://localhost:8080/upa-sample-1-webapp/upa/File/hugeUpaFile600M
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

	// http://localhost:8080/upa-sample-1-webapp/FileUploadSimple.html
	// http://localhost:8080/upa-sample-1-webapp/FileUploadAjax.html
	public Map<String, String> uploadOneFile(UpaUploadFile file) {
		Map<String, String> map = new HashMap<>();
		map.put("fileName", file.getName());
		byte[] bytes = file.getContent().length < 200 ? file.getContent() : Arrays.copyOfRange(file.getContent(), 0,
				200);
		map.put("fileContent", new String(bytes, StandardCharsets.UTF_8));
		return map;
	}

	// http://localhost:8080/upa-sample-1-webapp/FileUploadAjaxArray.html
	public Map<String, String> uploadManyFiles(UpaUploadFile[] files) {
		Map<String, String> map = new HashMap<>();
		for (UpaUploadFile file : files) {
			byte[] bytes = file.getContent().length < 200 ? file.getContent() : Arrays.copyOfRange(file.getContent(),
					0, 200);
			map.put(file.getName(), new String(bytes, StandardCharsets.UTF_8));
		}
		return map;
	}

	// http://localhost:8080/upa-sample-1-webapp/FileUploadAjaxList.html
	public Map<String, String> uploadManyFilesList(List<UpaUploadFile> files) {
		Map<String, String> map = new HashMap<>();
		for (UpaUploadFile file : files) {
			byte[] bytes = file.getContent().length < 200 ? file.getContent() : Arrays.copyOfRange(file.getContent(),
					0, 200);
			map.put(file.getName(), new String(bytes, StandardCharsets.UTF_8));
		}
		return map;
	}

}
