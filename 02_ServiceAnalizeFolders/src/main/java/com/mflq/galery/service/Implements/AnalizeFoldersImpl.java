package com.mflq.galery.service.Implements;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
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
	List<FileData> listRoutesfile;

	/* Ficheros de imagen que si se deben encontrar */
	private static final Pattern imgPatterns = Pattern.compile(".*(\\.(bmp|gif|jpe?g|jpg|mp4|png|tiff?))$");

	@Override
	public List<FileData> analizeFolders() {
		File carpeta = new File("/run/media/MiltonLQ/Disco local");

		/* Al ejecutar el metodo inicializa el arraylist */
		listRoutesfile = new ArrayList<FileData>();

		/* Convertimos a lista el arreglo con los direcorios de la ruta principal */
		ArrayList<File> mainRoutes = new ArrayList<File>(Arrays.asList(carpeta.listFiles()));

		/* Obtenemos el tamaño del array de la ruta principal */
		int listSize = mainRoutes.size();

		/* Con un for recorremos la lista */
		for (int i = 0; i < listSize; i++) {

			/* pregunnta si la ruta obtenida en la posicion es un directorio */
			if (mainRoutes.get(i).isDirectory()) {
				/*
				 * Si es una carpeta obteniene sus archivos(imganes, carpetas, etc), recorre y
				 * agrega las rutas hijas al arreglo principal
				 */
				ArrayList<File> childRoutes = new ArrayList<File>(Arrays.asList(mainRoutes.get(i).listFiles()));

				/* Con el for recorremos el arreglo hijo */
				for (File child : childRoutes) {

					/* Agrega la ruta hija al arreglo principal */
					mainRoutes.add(child);

					/* por cada nueva ruta aumenta en uno el tamaño de la lista principal */
					listSize++;
				}
			}

			/* Valida si es un archivo y si tiene algunas de las extenciones permitidas */
			if (mainRoutes.get(i).isFile() && imgPatterns.matcher(mainRoutes.get(i).toString()).matches()) {

				/*
				 * si es algun archivo de imagen o video etc guarda en el arreglo que contiene
				 * los nombres y directorios ya filtrados
				 */

				// Obtenga la extencion del archivo
				String extension = mainRoutes.get(i).toString().substring(mainRoutes.get(i).toString().lastIndexOf('.'))
						.replace(".", "");
				TypeFile typeFile = clienteRest.getForObject(
						"http://localhost:8081/filedataservice/api/typesfile/search/findTypeFileByTypeFile?type="
								+ extension,
						TypeFile.class);
				fileData = new FileData(mainRoutes.get(i).getParentFile().toString(), mainRoutes.get(i).getName(),
						typeFile);
				listRoutesfile.add(fileData);
			}
		}

		int contador = 0;
		for (FileData ruta : listRoutesfile) {
			System.out.println("conteo: " + contador + " cadena: " + ruta);
			contador++;
		}
		return listRoutesfile;
	}

}
