package com.mflq.galery.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class TypeFile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "PK_TYPEFILE")
	private int pkTypeFile;

	@Column
	private String typeFile;

	@JsonIgnoreProperties(value = { "typeFile", "hibernateLazyInitializer" })
	@OneToMany(mappedBy = "typeFile")
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
