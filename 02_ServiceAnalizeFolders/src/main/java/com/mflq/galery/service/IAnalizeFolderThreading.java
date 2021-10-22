package com.mflq.galery.service;

import org.springframework.scheduling.annotation.Async;

public interface IAnalizeFolderThreading {
	@Async
	void analizeFolders(String rootrootDirectory);
}
