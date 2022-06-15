package com.example.Springbootblogapi.repostiry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Springbootblogapi.entity.Category;
import com.example.Springbootblogapi.entity.Post;
import com.example.Springbootblogapi.entity.User;

public     interface   PostRepository extends JpaRepository<Post,Integer> {
	
	
	
	public List<Post>findByUser(User user);
	
	public List<Post>findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
	
	@Query("select p from Post p where p.title like :key")
	List<Post>getvalueasKey(@Param("key")String title);
	 

}
