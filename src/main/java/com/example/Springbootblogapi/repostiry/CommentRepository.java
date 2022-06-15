package com.example.Springbootblogapi.repostiry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Springbootblogapi.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
