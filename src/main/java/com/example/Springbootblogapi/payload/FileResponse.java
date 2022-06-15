package com.example.Springbootblogapi.payload;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileResponse {
	
	private String filename;
private	String messageString;


}



