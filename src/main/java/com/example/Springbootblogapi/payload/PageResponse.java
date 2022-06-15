package com.example.Springbootblogapi.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageResponse {
	
	private List<PostDto>content;
	
	private int pageNumber;
	
	private int pageSize;
	
	private long totalElements;
	
	private int totalPages;
	
	private boolean lastPage;

}
