package com.example.Springbootblogapi.exception;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public UserNotFoundException()
	{
		super("alredy name is present in db");
		
		
	}
	
	public UserNotFoundException(String msg)
	{
		super(msg);
	}
}
