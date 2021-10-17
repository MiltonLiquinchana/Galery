package com.mflq.galery.controller;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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

		/* muestra el cuadro de dialogo que permite seleccionar carpetas y archivos */
		JFileChooser fileChooser = new JFileChooser();
		
		/* Especifica que solo se pueda seleccionar carpetas */
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		/* en una variable obtenemos que boton se preciono */
		int btnPush = fileChooser.showOpenDialog(null);

		/* si no se preciono el boton cancelar(es decir se preciono el boton aceptar) */
		if (btnPush != JFileChooser.CANCEL_OPTION) {
			/*
			 * En un objeto de tipo archivo guardamos la carpeta que se selecciono con el
			 * chooser
			 */
			File rootDirectory = fileChooser.getSelectedFile();

			/*
			 * pasamos la ruta a el servicio que analiza las la carpeta en busca de
			 * archivos(imagenes, etc, todo lo que se permita en la galeria)
			 */
//			System.out.println(rootDirectory.getAbsolutePath());
			analizeFoldersService.analizeFolders(rootDirectory.getAbsolutePath());
		} else {
			System.out.println("Se cancelo la apertura de la carpeta");
		}

	}
}
