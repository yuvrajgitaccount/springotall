package com.example.Springbootblogapi.payload;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

	

	private int categoryId;
	

	@NotEmpty
	@Size(min = 2,message = "the category title must be 2")
	private String categoryTitle;
	
	@NotBlank
	@NotNull
	private String categoryDescription;
}
