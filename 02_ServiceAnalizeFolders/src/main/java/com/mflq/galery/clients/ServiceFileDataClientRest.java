package com.mflq.galery.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mflq.galery.models.FileData;
import com.mflq.galery.models.TypeFile;

/*Con esta anotacion se define que esta interfaz es un cliente feign*/
@FeignClient(name = "servicio-FileData", url = "localhost:8081/filedataservice") // Indicamos el nombre del microservicio al cual
																	// queremos conectar, la url y el puerto
public interface ServiceFileDataClientRest {

	/*
	 * Aqui definimos dos metodos con anotaciones, los cuales sirven para hacer
	 * peticiones al otro servicio, este primer metodo consulta el typo de ficheros
	 * permitidos
	 */
	@GetMapping("typefile/listtypefile")
	List<TypeFile> listTypeFile();

	/* Metodo que llama al endpoint que guarda los datos encontrados */
	@PostMapping("filedata/savelstfiledata")
	public List<FileData> savelstFileData(@RequestBody List<FileData> listFile);
}
