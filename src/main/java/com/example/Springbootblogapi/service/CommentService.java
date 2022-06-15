package com.example.Springbootblogapi.service;

import com.example.Springbootblogapi.payload.CommentDto;

public interface CommentService {
	
	
	public CommentDto createComment(CommentDto commentDto,Integer postId);
	
	
	public void deleteComment(Integer commentId);

}
