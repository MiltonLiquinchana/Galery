package com.mflq.galery.models;

import java.io.Serializable;
import java.util.List;
public class TypeFile implements Serializable {

	private static final long serialVersionUID = 1L;

	private int pkTypeFile;


	private String typeFile;


	private List<FileData> filesData;

	public TypeFile() {
	}

	public TypeFile(int pkTypeFile, String typeFile) {
		this.pkTypeFile = pkTypeFile;
		this.typeFile = typeFile;
	}

	public int getPkTypeFile() {
		return pkTypeFile;
	}

	public void setPkTypeFile(int pkTypeFile) {
		this.pkTypeFile = pkTypeFile;
	}

	public String getTypeFile() {
		return typeFile;
	}

	public void setTypeFile(String typeFile) {
		this.typeFile = typeFile;
	}

	public List<FileData> getFilesData() {
		return filesData;
	}

	public void setFilesData(List<FileData> filesData) {
		this.filesData = filesData;
	}
}
