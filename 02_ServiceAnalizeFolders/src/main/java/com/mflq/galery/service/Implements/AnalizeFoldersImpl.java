package com.mflq.galery.service.Implements;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mflq.galery.models.FileData;
import com.mflq.galery.models.TypeFile;
import com.mflq.galery.service.IAnalizeFoldersService;

@Service
public class AnalizeFoldersImpl implements IAnalizeFoldersService {

	@Autowired
	private RestTemplate clienteRest;

	/* Instancia al objeto photo */
	private FileData fileData;

	/* Lista para guardar los archivos principales */
	private List<FileData> lstfilespath;

	/* Ficheros de imagen que si se deben encontrar */
	private static final Pattern imgPatterns = Pattern.compile(".*(\\.(bmp|gif|jpe?g|jpg|mp4|png|tiff?))$");

	@Override
	public List<FileData> analizeFolders(String rootDirectory) {

		/*
		 * Al ejecutar el metodo inicializa el arraylist que guarda las rutas de los
		 * archivos encontrados dentro del directorio raiz
		 */
		lstfilespath = new ArrayList<FileData>();

		/*
		 * Al iniciar buscamos todos los tipos de ficheros que puede manejar la
		 * aplicacion
		 */
		List<TypeFile> listTypes = Arrays.asList(clienteRest
				.getForObject("http://localhost:8081/filedataservice/typefile/listtypefile", TypeFile[].class));

		/* declaramos un nuevo objeto file y pasamos el directorio raiz */
		File rootFolder = new File(rootDirectory);

		/*
		 * en un array list guarda la lista de archivos y subcarpetas encontradas en el
		 * directorio raiz
		 */
		ArrayList<File> lstMainPaths = new ArrayList<File>(Arrays.asList(rootFolder.listFiles()));

		/* obtenemos */
		int lstMainPathsSize = lstMainPaths.size();
		int contador = 1;

		/* Con un for recorremos la lista */
		for (int i = 0; i < lstMainPathsSize; i++) {

			/* pregunta si la ruta obtenida en la posicion es un directorio */
			if (lstMainPaths.get(i).isDirectory()) {
				/*
				 * Si es un directorio obteniene sus directorios y archivos hijos y agrega a la
				 * lista principal
				 */
				ArrayList<File> lstChildFilePath = new ArrayList<File>(Arrays.asList(lstMainPaths.get(i).listFiles()));

				/* Con el for recorremos el arreglo hijo */
				for (File childPath : lstChildFilePath) {

					/* Agrega la ruta hija al arreglo principal */
					lstMainPaths.add(childPath);

					/* por cada nueva ruta aumenta en uno el tamaño de la lista principal */
					lstMainPathsSize++;
				}
			}

			/* Valida si es un archivo y si tiene algunas de las extenciones permitidas */
			if (lstMainPaths.get(i).isFile() && imgPatterns.matcher(lstMainPaths.get(i).toString()).matches()) {

				/*
				 * si es algun archivo de imagen o video etc guarda en el arreglo que contiene
				 * los nombres y directorios ya encontrados
				 */

				// Obtenga la extencion del archivo
				String extension = lstMainPaths.get(i).toString()
						.substring(lstMainPaths.get(i).toString().lastIndexOf('.')).replace(".", "");

				/*
				 * Cada directorio de archivo encontrado se genera un nuevo objeto de tipo File
				 * Data
				 */
				fileData = new FileData(lstMainPaths.get(i).getParentFile().toString(), lstMainPaths.get(i).getName(),
						/*
						 * de la lista de tipos de archivos permitidos obtenemos el objeto de tipo
						 * extencion de archivo y agregamos
						 */
						listTypes.stream().filter(file -> file.getTypeFile().equals(extension))
								.collect(Collectors.toList()).get(0));

				/* agrega el nuevo objeto de FileData al arreglo de rutas filtradas */
				lstfilespath.add(fileData);
			}

			if (lstfilespath.size() == 10) {
				System.out.println(contador);
				HttpEntity<List<FileData>> request = new HttpEntity<List<FileData>>(lstfilespath);
				clienteRest.postForLocation("http://localhost:8081/filedataservice/filedata/savelstfiledata", request);
				lstfilespath.clear();
				contador++;
			}

		}

		HttpEntity<List<FileData>> request = new HttpEntity<List<FileData>>(lstfilespath);
		clienteRest.postForLocation("http://localhost:8081/filedataservice/filedata/savelstfiledata", request);

		/* Al finalizar todo el guardado limpiamos todas las listas */
		System.out.println("finalizado");
		return lstfilespath;

	}

}
