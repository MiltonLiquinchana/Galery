package com.mflq.galery.service;

import java.util.List;

import com.mflq.galery.models.FileData;

public interface IAnalizeFoldersService {
	List<FileData> analizeFolders(String rootDirectory);
}
