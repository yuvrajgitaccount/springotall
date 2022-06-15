package com.example.Springbootblogapi.repostiry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Springbootblogapi.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	
	
	public User findByName(String name);

}
