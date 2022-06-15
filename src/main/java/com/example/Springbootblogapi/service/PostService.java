package com.example.Springbootblogapi.service;

import java.util.List;

import com.example.Springbootblogapi.entity.Post;
import com.example.Springbootblogapi.payload.PageResponse;
import com.example.Springbootblogapi.payload.PostDto;

public interface PostService {
	
	
public PageResponse getAllSorting(Integer pageNumber,Integer pageSize,String sortBy,String sortDir );
	
	
	public List<PostDto>getAllPosts();
	
	public 	PostDto findByPostid(Integer postId);
	
	public PostDto updatePost(PostDto postDto,Integer postId);
	
	public void deleteById(Integer postId);
	
	
	public List<PostDto> findBypostCategoryId(Integer catgegoryId);
	
	public 	List<PostDto> findPostByUserId(Integer userId);
	
	
	public List<PostDto>findByTitle(String keyword);


	public List<PostDto>search(String keyword);

	PostDto createpost(PostDto postDto, Integer userId, Integer categoryId);

}
