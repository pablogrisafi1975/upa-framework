package org.upa.file;

public class UpaUploadFile {
	private final String name;
	private final byte[] content;

	public UpaUploadFile(String name, byte[] content) {
		this.name = name;
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public byte[] getContent() {
		return content;
	}

}
