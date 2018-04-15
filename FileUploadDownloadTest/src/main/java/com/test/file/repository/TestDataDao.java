package com.test.file.repository;

import com.test.file.model.TestData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TestDataDao extends JpaRepository<TestData, Integer> {
	
	
	@Query("select coalesce(max(d.version), 0) " +
		   "from TestData d " +
		   "where d.filename = :filename")
	public Integer findMaxFileVersionByFilename(@Param("filename")String fileName);
	
	@Query("select d " +
		   "from TestData d " +
		   "where d.filename = :filename " +
		   "and version = (select max(innerD.version) " +
						  "from TestData innerD " +
						  "where innerD.filename = :filename)")
	public TestData findFileByFilename(@Param("filename")String filename);

}
