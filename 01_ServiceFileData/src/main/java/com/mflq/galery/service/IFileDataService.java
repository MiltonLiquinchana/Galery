package com.mflq.galery.service;

import java.util.List;

import com.mflq.galery.models.entity.FileData;

public interface IFileDataService {
	List<FileData> findAllFileData();

	FileData findFileDataById(int Id);

	String saveFileData(List<FileData> listFileData);

}
