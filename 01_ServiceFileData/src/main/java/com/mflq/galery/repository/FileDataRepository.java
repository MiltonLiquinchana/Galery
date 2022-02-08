package com.mflq.galery.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mflq.galery.models.entity.FileData;

@RepositoryRestResource(path = "filesdatas", collectionResourceRel = "FilesData")
public interface FileDataRepository extends PagingAndSortingRepository<FileData, Integer> {
}
