package com.example.Springbootblogapi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {


	@Override
	public String uploadFile(String path, MultipartFile file) throws IOException {
		
		
		//filename abc.png
		
		String filenameString=file.getOriginalFilename();
		
		//filepath
		
		String randomidString =UUID.randomUUID().toString();
		
String filename1=		randomidString.concat(filenameString.substring(filenameString.lastIndexOf(".")));
		
		String filepath = path +File.separator+ filename1;
		
		

		//folder
		
		File file2=new File(path);
		
		if (!file2.exists()) {
			file2.mkdir();
		}
		
		//copyof file
		
		Files.copy(file.getInputStream(), Paths.get(filepath));
		
		
		
		return filename1;
	}

	@Override
	public InputStream getsource(String path, String imagename) throws FileNotFoundException {
	
		String filepaqthString=path+File.separator+imagename;
		
		InputStream inputStream=new FileInputStream(filepaqthString);
		
		return inputStream;
	}


}
