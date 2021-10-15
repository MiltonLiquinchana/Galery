package com.mflq.galery.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class FileData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "PK_FILEDATA")
	private int pkFileData;

	@Column
	private String routeFile;

	@Column
	private String nameFile;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_TYPEFILE", referencedColumnName = "PK_TYPEFILE")
	@JsonIgnoreProperties(value = { "filesData", "hibernateLazyInitializer" })
	TypeFile typeFile;

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
		return "FileData [routeFile=" + routeFile + ", nameFile=" + nameFile + ", typeFile=" + typeFile + "]";
	}

}
