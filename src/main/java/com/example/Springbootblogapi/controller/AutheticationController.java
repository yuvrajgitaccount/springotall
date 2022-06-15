package com.example.Springbootblogapi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Springbootblogapi.entity.User;
import com.example.Springbootblogapi.exception.UserNotFoundException;
import com.example.Springbootblogapi.payload.JwtRequest;
import com.example.Springbootblogapi.payload.JwtResponse;
import com.example.Springbootblogapi.payload.PostDto;
import com.example.Springbootblogapi.payload.UserDTO;
import com.example.Springbootblogapi.service.UserService;
import com.example.Springbootblogapi.utili.JwtUtil;

@RestController
@CrossOrigin("*")
public class AutheticationController {
	
	@Autowired
	private UserService userService;
	
	  @Autowired
	    private AuthenticationManager authenticationManager;
	  
	  @Autowired
	  private JwtUtil jwtUtil;
	  
	  
	  @Autowired
	  private UserDetailsService userDetailsService;
	  
	  
	  //generted token
	  
	  @PostMapping("/generate-token")
	  public ResponseEntity<?>genratedTokEntity(@RequestBody JwtRequest request) throws Exception
	  {
		  
		  
		  
		  try {
			  
			  authetication(request.getUsername(),request.getPassword());
			
		} catch (UserNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("user not found");
		}
		  
		  //auticatedt
		  
	UserDetails userDetails= this.userDetailsService.loadUserByUsername(request.getUsername());
		  
	
	String token=this.jwtUtil.generateToken(userDetails);
	return ResponseEntity.ok(new JwtResponse(token));
		  
		
		  
	  }
	  
	  
	  
	  
	  private void authetication(String username,String password) throws Exception
	  {
		  try {
			
			  
			  
			  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			  
			  
		} catch (DisabledException e) {
			
			throw new Exception("uerDesiable "+e.getMessage());
			// TODO: handle exception
		}
		  catch (Exception e) {
			// TODO: handle exception
			  throw new Exception("Invalied Exception,"+e.getMessage());
		}
	  }
	  
	  
	  //get current user (admin or normal)
	  
	  @GetMapping("/current-user")
	  public User currentUser(Principal principal)
	  {
		  return((User) this.userDetailsService.loadUserByUsername(principal.getName()));
		  
		  
	  }
	  
	  
	  @PostMapping("/register")
	  public ResponseEntity<UserDTO>registerPost(@RequestBody UserDTO userDTO)
	  {
		UserDTO userDTO2=userService.registerUser(userDTO);
		
		return new ResponseEntity<UserDTO>(userDTO2,HttpStatus.CREATED);
	  }
}
