package com.example.Springbootblogapi.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	

	public String uploadFile(String path,MultipartFile file) throws IOException;
	
	public InputStream getsource(String path,String imagename) throws FileNotFoundException;


}
