package com.example.Springbootblogapi.repostiry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Springbootblogapi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
