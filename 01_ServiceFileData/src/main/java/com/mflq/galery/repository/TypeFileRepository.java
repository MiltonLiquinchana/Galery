package com.mflq.galery.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mflq.galery.models.entity.TypeFile;

@RepositoryRestResource(path = "typesfile", collectionResourceRel = "typesFile", exported = false)
public interface TypeFileRepository extends PagingAndSortingRepository<TypeFile, Integer> {
//	TypeFile findTypeFileByTypeFile(@Param("type") String type);/* posiblemente se borre */

}
