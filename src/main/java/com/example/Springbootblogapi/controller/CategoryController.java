package com.example.Springbootblogapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Springbootblogapi.payload.ApiResponse;
import com.example.Springbootblogapi.payload.CategoryDto;
import com.example.Springbootblogapi.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// create Api
	@PostMapping("/cat")
	public ResponseEntity<CategoryDto> createCategoryDto(@Valid@RequestBody CategoryDto categorydto) {

		CategoryDto categoryDto2 = this.categoryService.craeteCategory(categorydto);

		return new ResponseEntity<>(categoryDto2, HttpStatus.CREATED);

	}

	// getAllCategory

	@GetMapping("/cat")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		List<CategoryDto> categoryDtos = this.categoryService.getAllcategory();

		return new ResponseEntity<>(categoryDtos, HttpStatus.OK);

	}

	// get category

	@GetMapping("/cat/{categoryId}")
	public ResponseEntity<CategoryDto> getByID(@PathVariable(value = "categoryId") Integer categoryId) {

		CategoryDto categoryDto = this.categoryService.findByCategoryId(categoryId);

		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);

	}
	
	//putMpaiing
	@PutMapping("/cat/{categoryId}")
	public ResponseEntity<CategoryDto>updateCategoryEntity(@Valid @RequestBody CategoryDto categoryDto,@PathVariable(value = "categoryId")Integer categoryId)
	{
	CategoryDto categoryDto2=	  this.categoryService.updateCategory(categoryDto, categoryId);
	
	return new ResponseEntity<>(categoryDto2,HttpStatus.CREATED); 
	}
	
	
	//deleteMapping
	
	@DeleteMapping("/cat/{categoryId}")
	
	public ResponseEntity<ApiResponse>DelteCatergoryBycategoryId(@PathVariable(value = "categoryId")Integer categoryId)
	{
		 this.categoryService.DeletecategoryId(categoryId);
		 
		 return new ResponseEntity<>(new ApiResponse("category is delte", true),HttpStatus.OK);
	}
}
