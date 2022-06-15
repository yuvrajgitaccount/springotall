package com.example.Springbootblogapi.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Springbootblogapi.entity.Category;
import com.example.Springbootblogapi.entity.Post;
import com.example.Springbootblogapi.entity.User;
import com.example.Springbootblogapi.exception.ResourseNotFoundException;
import com.example.Springbootblogapi.payload.PageResponse;
import com.example.Springbootblogapi.payload.PostDto;
import com.example.Springbootblogapi.repostiry.CategoryRepository;
import com.example.Springbootblogapi.repostiry.PostRepository;
import com.example.Springbootblogapi.repostiry.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public PostDto createpost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("the is =" + userId + "not presnt"));

		Category categor = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourseNotFoundException("the category id" + categoryId + "id not presnt"));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("yuvi.jpg");
		post.setAddedDate(new Date());
		// post.setTitle(postDto.getTitle());
		// post.setContent(postDto.getContent());
		post.setUser(user);
		post.setCategory(categor);

		Post post2 = postRepository.save(post);

		return this.modelMapper.map(post2, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPosts() {
		// TODO Auto-generated method stub

		List<Post> posts = postRepository.findAll();

		return this.postAllEntityToPostDto(posts);
	}

	private List<PostDto> postAllEntityToPostDto(List<Post> posts) {

		return posts.stream().map((post) -> postEntityToPostDto(post)).collect(Collectors.toList());
	}

	@Override
	public PostDto findByPostid(Integer postId) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourseNotFoundException("the is " + postId + "not present"));

		return this.postEntityToPostDto(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourseNotFoundException("the is " + postId + "not present"));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post post2 = postRepository.save(post);

		return this.postEntityToPostDto(post2);
	}

	@Override
	public void deleteById(Integer postId) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourseNotFoundException("the is " + postId + "not present"));

		postRepository.delete(post);

	}

	@Override
	public List<PostDto> findBypostCategoryId(Integer catgegoryId) {
		// TODO Auto-generated method stub

		Category category = categoryRepository.findById(catgegoryId)
				.orElseThrow(() -> new ResourseNotFoundException("the is " + catgegoryId + "not present"));

		List<Post> posts = postRepository.findByCategory(category);

		return this.postAllEntityToPostDto(posts);
	}

	@Override
	public List<PostDto> findPostByUserId(Integer userId) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("the is " + userId + "not present"));

		List<Post> posts = postRepository.findByUser(user);

		return this.postAllEntityToPostDto(posts);

	}

	@Override
	public List<PostDto> findByTitle(String keyword) {
	
		List<Post>posts= this.postRepository.findByTitleContaining(keyword);
		
		
		return this.postAllEntityToPostDto(posts);
	}

	public PostDto postEntityToPostDto(Post post) {
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

	public Post postEntityToPostDto(PostDto postDto) {
		Post post = this.modelMapper.map(postDto, Post.class);
		return post;
	}

	@Override
	public PageResponse getAllSorting(Integer pageNumber, Integer pageSize,String sortBy,String sortDir ) {

		Sort sort=null;
		if (sortDir.equalsIgnoreCase("asc")) {
			
			sort=Sort.by(sortBy).ascending();
		}
		else {
			
			sort=Sort.by(sortBy).descending();
			
		}
		
		
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);

		Page<Post> page = postRepository.findAll(pageable);

		List<Post> posts = page.getContent();

List<PostDto>postDtos=posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PageResponse pageResponse=new PageResponse();
		pageResponse.setContent(postDtos);
		pageResponse.setPageNumber(page.getNumber());
		pageResponse.setPageSize(page.getSize());
		pageResponse.setTotalElements(page.getTotalElements());
		pageResponse.setTotalPages(page.getTotalPages());
		pageResponse.setLastPage(page.isLast());
		
		return  pageResponse;
		
	}

	@Override
	public List<PostDto> search(String keyword) {
		// TODO Auto-generated method stub
List<Post>posts= this.postRepository.getvalueasKey("%"+keyword+"%");
	
		
		return this.postAllEntityToPostDto(posts);
	}

}
