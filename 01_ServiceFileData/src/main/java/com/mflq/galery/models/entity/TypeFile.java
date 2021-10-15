package com.mflq.galery.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class TypeFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "PK_TYPEFILE")
	private int pkTypeFile;

	@Column
	private String typeFile;

	@OneToMany(mappedBy = "typeFile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<FileData> filesData;

	public TypeFile() {
	}

	public TypeFile(int pkTypeFile, String typeFile, List<FileData> filesData) {
		this.pkTypeFile = pkTypeFile;
		this.typeFile = typeFile;
		this.filesData = filesData;
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

	@Override
	public String toString() {
		return "TypeFile [typeFile=" + typeFile + ", filesData=" + filesData + "]";
	}

}
