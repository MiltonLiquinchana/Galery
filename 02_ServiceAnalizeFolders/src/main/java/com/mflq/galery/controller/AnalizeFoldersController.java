package com.mflq.galery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mflq.galery.service.IAnalizeFoldersService;

@RestController
@RequestMapping("analizefolders")
public class AnalizeFoldersController {
	@Autowired
	private IAnalizeFoldersService analizeFoldersService;

	@GetMapping("getlistfolders")
	public void getListFolders() {
		analizeFoldersService.analizeFolders();
	}
}
