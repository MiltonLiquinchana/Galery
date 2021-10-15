package com.mflq.galery.models;

import java.io.Serializable;

public class FileData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int pkFileData;

	private String routeFile;

	private String nameFile;

	private TypeFile typeFile;
	

	public FileData() {
		
	}

	public FileData(String routeFile, String nameFile, TypeFile typeFile) {
		super();
		this.routeFile = routeFile;
		this.nameFile = nameFile;
		this.typeFile = typeFile;
	}

	public String getRouteFile() {
		return routeFile;
	}

	public void setRouteFile(String routeFile) {
		this.routeFile = routeFile;
	}

	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	public TypeFile getTypeFile() {
		return typeFile;
	}

	public void setTypeFile(TypeFile typeFile) {
		this.typeFile = typeFile;
	}

	@Override
	public String toString() {
		return "FileData [pkFileData=" + pkFileData + ", routeFile=" + routeFile + ", nameFile=" + nameFile
				+ ", typeFile=" + typeFile + "]";
	}

	
}
