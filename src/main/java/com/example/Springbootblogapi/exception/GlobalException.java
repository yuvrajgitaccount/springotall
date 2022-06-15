package com.example.Springbootblogapi.exception;

import java.net.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.Springbootblogapi.payload.ApiErrors;
import com.example.Springbootblogapi.payload.ResponseEntityBuilder;

@RestControllerAdvice
public class GlobalException  {
	
	

	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<Object>responseEntity(ResourseNotFoundException ex)
	{
		
		List<String>details = new ArrayList<String>();
		
		
		details.add(ex.getMessage());
		
		ApiErrors apiErrors=new ApiErrors(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND,
				"Resource not found",
				details
				
				
				);
				
				return ResponseEntityBuilder.errEntity(apiErrors);
		
		
	}
	
	
//	  @ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
//		  
//		  List<String>list=new ArrayList<String>();		
//		  list.add(ex.getBindingResult().toString());
//	    
//			ApiErrors apiErrors=new ApiErrors(
//					LocalDateTime.now(),
//					HttpStatus.BAD_REQUEST,
//					"vaqlidation error",
//				list
//					
//					
//					);
//	    return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
//	  } 
//	  
	  
	
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  
	  public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex)
	  {
		  Map<String, String>map=new HashMap<>();
		  
		  ex.getAllErrors().forEach ((error)->{
			  
			   String message= error.getDefaultMessage();
			   
			   
			    String fieldNameString =((FieldError)error).getField();
			    
			    map.put(fieldNameString,message);
			  
			  
		  });
		  
		  return new ResponseEntity<Map<String,String>>(map, HttpStatus.BAD_REQUEST);
	  }
	  
}


