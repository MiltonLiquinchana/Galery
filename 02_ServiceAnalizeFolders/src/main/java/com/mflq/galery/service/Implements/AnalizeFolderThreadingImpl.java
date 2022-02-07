package com.mflq.galery.service.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mflq.galery.service.IAnalizeFolderThreading;
import com.mflq.galery.service.IAnalizeFoldersService;

@Service
public class AnalizeFolderThreadingImpl implements IAnalizeFolderThreading {
	@Autowired
	private IAnalizeFoldersService analizeFoldersService;

	/* Este metodo es de tipo asincronico es decir se ejecuta en un hilo */
	@Override
	public void analizeFolders(String rootrootDirectory) {
		/*
		 * El metodo asincronico ejecuta un metodo que se encarga de analizar los
		 * archivos en la ruta especificada por el usuario
		 */
		analizeFoldersService.analizeFolders(rootrootDirectory);
	}

}
