package com.example.Springbootblogapi.payload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
	

	private int id;
	
	@NotEmpty
	@Size(min = 2, message = "First Name should have atleast 2 characters")
	private String name;

	@Email(message = "Email adress is not valid !")
	private String email;
	
	@NotNull
	@NotBlank
	@Size(min = 2,max=10, message = "Passport should have atleast 2 characters and max 10")
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto>roleDtos=new HashSet<>();

}
