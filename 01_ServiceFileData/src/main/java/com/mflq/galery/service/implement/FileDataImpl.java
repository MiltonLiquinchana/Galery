package com.mflq.galery.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mflq.galery.models.entity.FileData;
import com.mflq.galery.models.entity.TypeFile;
import com.mflq.galery.repository.FileDataRepository;
import com.mflq.galery.repository.TypeFileRepository;
import com.mflq.galery.service.IFileDataService;

@Service
public class FileDataImpl implements IFileDataService {
	@Autowired
	private FileDataRepository fileDataRepository;

	@Autowired
	private TypeFileRepository typeFileRepository;

	@Override
	@Transactional(readOnly = true)
	public List<FileData> findAllFileData() {
		return (List<FileData>) fileDataRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public FileData findFileDataById(int Id) {
		// TODO Auto-generated method stub
		return fileDataRepository.findById(Id).orElse(null);
	}

	@Override
	@Transactional(readOnly = false)
	public String saveFileData(List<FileData> listFileData) {
		List<FileData> listaFileData = new ArrayList<FileData>();
		List<TypeFile> listTypeData = (List<TypeFile>) typeFileRepository.findAll();

//		List<TypeFile> lista=listTypeData.stream().filter(file -> file.getTypeFile().equals("png")).collect(Collectors.toList());

		for (FileData fileData : listFileData) {
			List<TypeFile> lista = listTypeData.stream()
					.filter(file -> file.getTypeFile().equals(fileData.getTypeFile().getTypeFile()))
					.collect(Collectors.toList());
			fileData.setTypeFile(lista.get(0));
			listaFileData.add(fileData);
		}

		fileDataRepository.saveAll(listaFileData);
		
		return "Guardado exitosamente";
	}

}
