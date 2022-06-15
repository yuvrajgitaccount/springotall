  package com.example.Springbootblogapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Springbootblogapi.entity.Category;

import com.example.Springbootblogapi.exception.ResourseNotFoundException;
import com.example.Springbootblogapi.payload.CategoryDto;
import com.example.Springbootblogapi.repostiry.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto craeteCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub

		Category category = this.CategoryDtoToCategory(categoryDto);

		Category category2 = categoryRepository.save(category);
		return this.CategoryToCategoryDto(category2);
	}

	@Override
	public List<CategoryDto> getAllcategory() {
		// TODO Auto-generated method stub

		List<Category> list = this.categoryRepository.findAll();

		return this.CategeryAllToCategoryDto(list);
	}

	@Override
	public CategoryDto findByCategoryId(Integer categoryId) {
		// TODO Auto-generated method stub
	Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourseNotFoundException("the id is" + categoryId+ "not presnt"));
		return this.CategoryToCategoryDto(category);
	}

		

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourseNotFoundException("the id is" + categoryId+ "not presnt"));
		
		category.setCategoryId(category.getCategoryId());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		
	Category category2=	categoryRepository.save(category);
		
		return this.CategoryToCategoryDto(category2);
		
		
		
	}

	@Override
	public void DeletecategoryId(Integer categoryId) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(categoryId)
					.orElseThrow(() -> new ResourseNotFoundException("the id is" + categoryId+ "not presnt"));
		categoryRepository.delete(category);
		}

	

	public Category CategoryDtoToCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		return category;
	}

	public CategoryDto CategoryToCategoryDto(Category category) {
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

	private List<CategoryDto> CategeryAllToCategoryDto(List<Category> list) {

		return list.stream().map((cat) -> CategoryToCategoryDto(cat)).collect(Collectors.toList());
		// TODO Auto-generated method stub

	}
}
