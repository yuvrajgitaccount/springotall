package com.example.Springbootblogapi;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Springbootblogapi.config.util.AppConstants;
import com.example.Springbootblogapi.entity.Role;
import com.example.Springbootblogapi.repostiry.RoleRepository;

@SpringBootApplication
public class SpringbootBlogApiApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Role role = new Role();
		role.setId(AppConstants.ROLE_ADMIN);
		role.setName("ROLE_ADMIN");

		Role role2 = new Role();
		role2.setId(AppConstants.ROLE_USER);
		role2.setName("ROLE_USER");

		List<Role> roles = List.of(role, role2);

	List<Role>roles2=	this.roleRepository.saveAll(roles);
	
	
	for (Role role3 : roles2) {
		
		System.out.println(role3.getName());
		
	}
	}

}
