package com.example.Springbootblogapi.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.Springbootblogapi.entity.Category;
import com.example.Springbootblogapi.entity.Comment;
import com.example.Springbootblogapi.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDto {
	
	private int postId;
	
	@NotEmpty
	private String title;
	
	@NotNull
	private String content;
	
	

	private String imageName;
	
	private Date addedDate;
	
	
	private UserDTO user;
	
	
	private CategoryDto category;
	
	private Set<CommentDto>comments=new HashSet<>();

	

}
