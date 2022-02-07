package com.mflq.galery.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mflq.galery.configuration.exceptionhandler.exceptions.CustomNotFoundException;
import com.mflq.galery.models.entity.FileData;
import com.mflq.galery.repository.FileDataRepository;
import com.mflq.galery.service.IFileDataService;

@Service
public class FileDataImpl implements IFileDataService {
	@Autowired
	private FileDataRepository fileDataRepository;

	/* Servicio que busca todos los directorios guardados en la bd */
	@Override
	@Transactional(readOnly = true)
	public List<FileData> findAllFileData() {
		/*
		 * ejecuta el metodo finAll el cual busca todos los registros de la tabla
		 * FileData
		 */
		return (List<FileData>) fileDataRepository.findAll();
	}

	/* Servicio que se encarga de guardar un listado de datos */
	@Override
	@Transactional(readOnly = false)
	public List<FileData> savelstFileData(List<FileData> listFileData) {

		/*Valida que la lista recivida no este vacia*/
		if (listFileData.size() <= 0) {
			throw new CustomNotFoundException("No existen registro para guardar");
		}

		fileDataRepository.saveAll(listFileData);

		return listFileData;
	}

}
