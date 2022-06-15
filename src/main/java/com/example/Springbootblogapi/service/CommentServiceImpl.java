package com.example.Springbootblogapi.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Springbootblogapi.entity.Comment;
import com.example.Springbootblogapi.entity.Post;
import com.example.Springbootblogapi.exception.ResourseNotFoundException;
import com.example.Springbootblogapi.payload.CommentDto;
import com.example.Springbootblogapi.repostiry.CommentRepository;
import com.example.Springbootblogapi.repostiry.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourseNotFoundException("the is =" + postId + "not presnt"));

		Comment comment = modelMapper.map(commentDto, Comment.class);

		comment.setPost(post);

		Comment comment2 = commentRepository.save(comment);

		return this.modelMapper.map(comment2, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourseNotFoundException("the is =" + commentId + "not presnt"));
		commentRepository.delete(comment);
	}

}
