package com.mflq.galery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mflq.galery.models.entity.FileData;
import com.mflq.galery.service.IFileDataService;

@RestController
@RequestMapping("filedata")
public class FileDataController {
	@Autowired
	private IFileDataService fileDataService;

//
//	@Autowired
//	private FileDataRepository fileDataRepository;

	@GetMapping("listfiledata")
	public ResponseEntity<List<FileData>> listFileData() {

//		TypeFile tipo = typeFileRepository.findTypeFileByTypeFile("jpe");
//		System.out.println(tipo);
//		FileData datas = new FileData();
//		datas.setNameFile("imagen funciona");
//		datas.setRouteFile("ruta/jaja");
//		datas.setTypeFile(tipo);
//		fileDataRepository.save(datas);
//
//		TypeFile tipo = new TypeFile();
//		FileData datas = new FileData();
//		List<FileData> datainger = new ArrayList<FileData>();
//
//		datas.setNameFile("iamgennueva");
//		datas.setRouteFile("rutanueva/");
//		tipo.setTypeFile("crack");
//		datas.setTypeFile(tipo);
//
//		datainger.add(datas);
//
//		tipo.setFilesData(datainger);
//		typeFileRepository.save(tipo);

		return new ResponseEntity<>(fileDataService.findAllFileData(), HttpStatus.OK);
//		return null;
	}

	@GetMapping("findfiledata/{id}")
	public ResponseEntity<FileData> findFileData(@PathVariable int id) {

		return new ResponseEntity<FileData>(fileDataService.findFileDataById(id), HttpStatus.OK);
	}

	@PostMapping("savefiledata")
	public ResponseEntity<String> saveFileData(@RequestBody List<FileData> listFile) {
		return new ResponseEntity<String>(fileDataService.saveFileData(listFile), HttpStatus.OK);
	}
}
