package com.test.file.service;

import org.springframework.web.multipart.MultipartFile;

import com.test.file.model.TestData;
import com.test.file.repository.TestDataDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestDataServiceImpl implements TestDataService {

	@Autowired
	protected TestDataDao dao;
	
	public TestDataServiceImpl() {
		super();
	}
	
	@Override
	@Transactional
	public void saveFile(MultipartFile file) throws RuntimeException {
		try {
			byte[] fileContent = file.getBytes();
			String fileName = file.getOriginalFilename();
			String mimeType = file.getContentType();
			
			Integer maxVersion = this.dao.findMaxFileVersionByFilename(fileName);
			Integer version = new Integer(maxVersion.intValue() + 1);
			
			TestData newRecord = new TestData();
			newRecord.setFileContent(fileContent);
			newRecord.setFilename(fileName);
			newRecord.setMimetype(mimeType);
			newRecord.setVersion(version);
			
			this.dao.save(newRecord);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
		

	}

}
