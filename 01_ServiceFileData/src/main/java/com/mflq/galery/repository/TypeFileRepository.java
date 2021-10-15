package com.mflq.galery.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mflq.galery.models.entity.TypeFile;

@RepositoryRestResource(path = "typesfile", collectionResourceRel = "typesFile", exported = true)
public interface TypeFileRepository extends PagingAndSortingRepository<TypeFile, Integer> {
	TypeFile findTypeFileByTypeFile(@Param("type") String type);/*posiblemente se borre*/
	
}
