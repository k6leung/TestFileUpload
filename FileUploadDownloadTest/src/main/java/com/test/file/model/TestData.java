package com.test.file.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * The persistent class for the TEST_DATA database table.
 * 
 */
@Entity
@Table(name="TEST_DATA", schema="FILE_UPLOAD_DOWNLOAD_TEST")
public class TestData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;

	@Column(name="FILE_CONTENT")
	private byte[] fileContent;

	@Column(name="FILENAME")
	private String filename;

	@Column(name="MIMETYPE")
	private String mimetype;

	@Column(name="VERSION")
	private Integer version;

	public TestData() {
	}
	
	public TestData(String filename,
					String mimeType,
					byte[] fileContent,
					Integer version) {
		super();
		this.filename = filename;
		this.mimetype = mimeType;
		this.fileContent = fileContent;
		this.version = version;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getFileContent() {
		return this.fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMimetype() {
		return this.mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}