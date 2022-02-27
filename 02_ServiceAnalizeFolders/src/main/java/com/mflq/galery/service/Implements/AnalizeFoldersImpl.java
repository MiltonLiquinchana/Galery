package com.mflq.galery.service.Implements;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mflq.galery.clients.ServiceFileDataClientRest;
import com.mflq.galery.models.FileData;
import com.mflq.galery.models.TypeFile;
import com.mflq.galery.service.IAnalizeFoldersService;

@Service
public class AnalizeFoldersImpl implements IAnalizeFoldersService {

	/* Inyectamos al cliente rest que se creo con feign */
	@Autowired
	private ServiceFileDataClientRest serviceFileDataClientRest;

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
		 * aplicacion con restTemplate
		 */
		List<TypeFile> listTypes = serviceFileDataClientRest.listTypeFile();
		/* declaramos un nuevo objeto file y pasamos el directorio raiz */
		File rootFolder = new File(rootDirectory);

		/*
		 * en un array list guarda la lista de archivos y subcarpetas encontradas en el
		 * directorio raiz
		 */
		ArrayList<File> lstMainPaths = new ArrayList<File>(Arrays.asList(rootFolder.listFiles()));

		/* obtenemos el tamaño de la lista lstMainPaths */
		int lstMainPathsSize = lstMainPaths.size();
		int contador = 1;

		/* Con un for recorremos la lista */
		for (int i = 0; i < lstMainPathsSize; i++) {
			System.out.println("Analizando la carpeta: " + lstMainPaths.get(i));
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

				// Obtiene la extencion del archivo
				String extension = lstMainPaths.get(i).toString()
						.substring(lstMainPaths.get(i).toString().lastIndexOf('.'));

				// Obtiene el nombre del archivo y elimina la extencion desde el punto
				String nameFile = lstMainPaths.get(i).getName().replace(extension, "");

				/*
				 * Cada directorio de archivo encontrado se genera un nuevo objeto de tipo File
				 * Data
				 */
				fileData = new FileData(lstMainPaths.get(i).getParentFile().toString(), nameFile,
						/*
						 * de la lista de tipos de archivos permitidos obtenemos el objeto de tipo
						 * extencion de archivo y agregamos
						 */
						listTypes.stream().filter(file -> file.getTypeFile().equals(extension.replace(".", "")))
								.collect(Collectors.toList()).get(0));

				/* agrega el nuevo objeto de FileData al arreglo de rutas filtradas */
				lstfilespath.add(fileData);
			}

			/* Si la lista tiene ya 50 elementos manda a guardar */
			if (lstfilespath.size() == 50) {

				/* Hacemos la peticion post con feign,pasamos la lista al metodo que guarda */
				serviceFileDataClientRest.savelstFileData(lstfilespath);

				/* Despues de guardar limpia la lista */
				lstfilespath.clear();
				contador++;
			}

		}

		/*
		 * Al finalizar se guarda la lista esto solo en caso de que al finalizar el
		 * analizis existan datos sin guardar
		 */
		if (lstfilespath.size() > 0) {
			/* Hacemos la peticion post con feign,pasamos la lista al metodo que guarda */
			serviceFileDataClientRest.savelstFileData(lstfilespath);
		}

		/* Al finalizar todo el guardado limpiamos todas las listas */
		System.out.println("finalizado");
		return lstfilespath;

	}

}
