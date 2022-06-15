package com.example.Springbootblogapi.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "post")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = "comments")

public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	@Column(name = "post_title",length = 100,nullable = false)
	private String title;
	
	@Column(length = 1000)
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "category_categoryId",referencedColumnName = "categoryId")
	private Category category;
	
	//@OneToMany(cascade = CascadeType.ALL,mappedBy = "post")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "post")
	private Set<Comment>comments=new HashSet<Comment>();

}
