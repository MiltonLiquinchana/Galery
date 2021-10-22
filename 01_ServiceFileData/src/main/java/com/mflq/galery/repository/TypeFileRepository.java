package com.mflq.galery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mflq.galery.models.entity.TypeFile;

@RepositoryRestResource(path = "typesfile", collectionResourceRel = "typesFile", exported = true)
public interface TypeFileRepository extends PagingAndSortingRepository<TypeFile, Integer> {
	
	/* Solo retorna dos columnas necesarias para el tipo */
	@Query("SELECT type.pkTypeFile, type.typeFile FROM TypeFile type ORDER BY type.pkTypeFile ASC")
	List<Object[]> findtypesfile();

}
