package com.example.Springbootblogapi.service;

import java.util.List;

import com.example.Springbootblogapi.payload.UserDTO;

public interface  UserService {
	
	
	
	
	UserDTO registerUser(UserDTO userDTO);
	
	//craete User
	
	UserDTO craeteUser (UserDTO userDTO);
	
	//GETaLL uSERdTO
	
	List<UserDTO>getAllUser();
	
	
	//find By Id
	
	UserDTO findByUserId(Integer id);
	
	
	//update User
	
	UserDTO updateUser(UserDTO userDTO,Integer id);
	
	
	//delete UserDto
	
	void DeleteUserById(Integer id);

}
