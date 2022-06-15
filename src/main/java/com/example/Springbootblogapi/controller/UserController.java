package com.example.Springbootblogapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Springbootblogapi.payload.ApiResponse;
import com.example.Springbootblogapi.payload.UserDTO;
import com.example.Springbootblogapi.service.UserService;

@RestController

public class UserController {

	@Autowired
	private UserService userService;

	// create Api
	@PostMapping("/post")
	public ResponseEntity<UserDTO> createUser(@Valid@RequestBody UserDTO userDTO) {

		UserDTO userDTO2 = this.userService.craeteUser(userDTO);

		return new ResponseEntity<>(userDTO2, HttpStatus.CREATED);

	}

	@GetMapping("/post")
	public ResponseEntity<List<UserDTO>> getAllUser() {
		List<UserDTO> userDTOs = this.userService.getAllUser();

		return ResponseEntity.ok(userDTOs);
	}

	// getMap bYi

	@GetMapping("/post/{id}")
	public ResponseEntity<UserDTO> getByID(@PathVariable(value = "id") Integer id) {
		UserDTO userDTO = this.userService.findByUserId(id);

		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	// update Byid
	@PutMapping("/post/{id}")
	public ResponseEntity<UserDTO> updateById(@Valid@RequestBody UserDTO userDTO, @PathVariable(value = "id") Integer id) {
		UserDTO updateUserDTO = this.userService.updateUser(userDTO, id);

		return new ResponseEntity<>(updateUserDTO, HttpStatus.CREATED);
	}

	// delte Byid
@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/post/{id}")
	public ResponseEntity<ApiResponse> deleteByID(@PathVariable(value = "id") Integer id) {
		this.userService.DeleteUserById(id);

		return new ResponseEntity<>(new ApiResponse("messageDelte", true), HttpStatus.OK);
	}

}
