package com.mflq.galery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("listfiledata")
	public ResponseEntity<List<FileData>> listFileData() {
		return new ResponseEntity<>(fileDataService.findAllFileData(), HttpStatus.OK);
	}

//	@GetMapping("findfiledata/{id}")
//	public ResponseEntity<FileData> findFileData(@PathVariable int id) {
//
//		return new ResponseEntity<FileData>(fileDataService.findFileDataById(id), HttpStatus.OK);
//	}

	@PostMapping("savefiledata")
	public ResponseEntity<String> saveFileData(@RequestBody List<FileData> listFile) {

		return new ResponseEntity<String>(fileDataService.saveFileData(listFile), HttpStatus.OK);

	}
}
