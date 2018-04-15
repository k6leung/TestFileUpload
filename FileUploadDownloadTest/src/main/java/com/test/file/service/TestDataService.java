package com.test.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface TestDataService {
	
	public void saveFile(MultipartFile file);

}
