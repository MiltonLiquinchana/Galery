package com.mflq.galery.controller;

import java.io.File;

import javax.swing.JFileChooser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mflq.galery.service.IAnalizeFolderThreading;

@RestController
@RequestMapping("analyze")
public class AnalizeFoldersController {

	@Autowired
	private IAnalizeFolderThreading analizeFolderThreading;

	/*
	 * Servicio que se encarga una tarea en segundo plano la cual se encarga de
	 * analizar una carpeta en busca de archivos para mostrar en la galeria
	 */
	@PostMapping("files")
	public ResponseEntity<?> analyzefiles() {

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
			analizeFolderThreading.analizeFolders(rootDirectory.getAbsolutePath());
			return new ResponseEntity<>(
					"Se inicio el analisis de la carpeta: '" + rootDirectory + "' en busca de archivos",
					HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Se cancelo la apertura de la carpeta", HttpStatus.OK);
		}
	}
}
