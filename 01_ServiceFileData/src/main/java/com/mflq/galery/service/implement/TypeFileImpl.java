package com.mflq.galery.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mflq.galery.models.entity.TypeFile;
import com.mflq.galery.repository.TypeFileRepository;
import com.mflq.galery.service.ITypeFileService;

@Service
public class TypeFileImpl implements ITypeFileService {
	@Autowired
	private TypeFileRepository typeFileRepository;

	@Override
	@Transactional(readOnly = true)
	public List<TypeFile> listTypeFile() {
		
		/*Consultamos(retorna una lista de arreglo de objetos) y mapeamos a un nuevo objeto de TypeFile*/
		return typeFileRepository.findtypesfile().stream().map(list -> new TypeFile((int) list[0], (String) list[1]))
				.collect(Collectors.toList());
	}

}
