package com.example.Springbootblogapi.service;

import java.util.List;

import com.example.Springbootblogapi.payload.CategoryDto;


public interface CategoryService {
	
	
	

	//craete category
	
	CategoryDto craeteCategory (CategoryDto categoryDto);
	
	//GETaLL categoryall
	
	List<CategoryDto>getAllcategory();
	
	
	//find By Id
	
	CategoryDto findByCategoryId(Integer categoryId);
	
	
	//update category
	
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId );
	
	
	//delete category
	
	void DeletecategoryId(Integer categoryId  );


}
