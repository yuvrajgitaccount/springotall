package com.example.Springbootblogapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Springbootblogapi.payload.ApiResponse;
import com.example.Springbootblogapi.payload.CommentDto;
import com.example.Springbootblogapi.service.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("/comment/{postId}")
	public ResponseEntity<CommentDto>craeteComment(@RequestBody CommentDto commentDto,@PathVariable(value = "postId")Integer postId)
	{
	CommentDto commentDto2=	  commentService.createComment(commentDto, postId);
		
		return new ResponseEntity<>(commentDto2,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse>deleteComment(@PathVariable(value = "commentId")Integer commentId)
	{
		  commentService.deleteComment(commentId);
		  
		  return new ResponseEntity<>(new ApiResponse("comment is deleted",true),HttpStatus.OK);
	}

}
