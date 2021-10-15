package com.mflq.galery.models;

import java.io.Serializable;
import java.util.List;

public class TypeFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int pkTypeFile;

	private String typeFile;

	List<FileData> filesData;

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

	@Override
	public String toString() {
		return "TypeFile [pkTypeFile=" + pkTypeFile + ", typeFile=" + typeFile + ", filesData=" + filesData + "]";
	}

	
}
