package com.example.Springbootblogapi.repostiry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Springbootblogapi.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
