package com.test.file.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.file.model.TestData;
import com.test.file.repository.TestDataDao;
import com.test.file.service.TestDataService;

@RequestMapping("/api")
@RestController
public class TestDataRestController {
	
	@Autowired
	protected TestDataDao testDataDao;
	
	@Autowired
	protected TestDataService testDataService;
	
	public TestDataRestController() {
		super();
	}
	
	@RequestMapping("/download/{filename}")
	public ResponseEntity<byte[]> findFileContentByFilename(@PathVariable("filename") String fileName) {
		ResponseEntity<byte[]> result = null;
		
		TestData target = this.testDataDao.findFileByFilename(fileName);
		
		if(target != null) {
			HttpHeaders headers = new HttpHeaders(); 
			headers.set("Content-Type", target.getMimetype());
			
			result = new ResponseEntity<byte[]>(target.getFileContent(), headers, HttpStatus.OK);
		} else {
			result = new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
			//should throw and customize with controller advice
		}
		
		return result;
	}
	
	@RequestMapping(value="/upload",
					method=RequestMethod.POST)
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		
		this.testDataService.saveFile(file);
		
		return "OK";
	}

}
