package com.mflq.galery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mflq.galery.models.entity.TypeFile;
import com.mflq.galery.service.ITypeFileService;

@RestController
@RequestMapping("typefile")
public class TypeFileController {

	@Autowired
	private ITypeFileService typeFileService;

	@GetMapping("listtypefile")
	public ResponseEntity<List<TypeFile>> listTypeFile() {
		return new ResponseEntity<>(typeFileService.listTypeFile(), HttpStatus.OK);
	}
}
