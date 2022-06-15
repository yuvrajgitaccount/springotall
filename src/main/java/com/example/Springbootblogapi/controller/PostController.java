package com.example.Springbootblogapi.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Springbootblogapi.config.util.AppConstants;
import com.example.Springbootblogapi.entity.Post;
import com.example.Springbootblogapi.exception.ResourseNotFoundException;
import com.example.Springbootblogapi.payload.ApiResponse;
import com.example.Springbootblogapi.payload.FileResponse;
import com.example.Springbootblogapi.payload.PageResponse;
import com.example.Springbootblogapi.payload.PostDto;
import com.example.Springbootblogapi.service.FileService;
import com.example.Springbootblogapi.service.PostService;

@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService filsFileService;

	@Value("${project.path}")
	private String pathString;

	@PostMapping("/post/{userId}/{categoryId}")

	public ResponseEntity<PostDto> CaretePost(@Valid @RequestBody PostDto postDto,
			@PathVariable(value = "userId") Integer userId, @PathVariable(value = "categoryId") Integer categoryId) {
		PostDto post = postService.createpost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);
	}

	@GetMapping("/post/post")
	public ResponseEntity<List<PostDto>> getAllPostDtos() {
		List<PostDto> postDtos = this.postService.getAllPosts();

		return new ResponseEntity<>(postDtos, HttpStatus.OK);
	}

	@GetMapping("/post/post/{postId}")
	public ResponseEntity<PostDto> getPostByID(@PathVariable(value = "postId") Integer postId) {
		PostDto postDto = this.postService.findByPostid(postId);

		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}

	@PutMapping("/post/post/{postId}")
	public ResponseEntity<PostDto> updateThepost(@RequestBody PostDto postDto,
			@PathVariable(value = "postId") Integer postId) {

		PostDto postDto2 = this.postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(postDto2, HttpStatus.CREATED);

	}

//	@DeleteMapping("/post/post/{postId}")
//	public ResponseEntity<ApiResponse> deleteByPostId(@PathVariable(value ="postId") Integer postId) {
//
//		this.postService.deleteById(postId);)
//
//		return new ResponseEntity<>(new ApiResponse("message delte", true),HttpStatus.OK);
//
//	}
//	

	@DeleteMapping("/post/post/{postId}")
	public ResponseEntity<ApiResponse> deleteByID(@PathVariable(value = "postId") Integer postId) {
		this.postService.deleteById(postId);

		return new ResponseEntity<>(new ApiResponse("messageDelte", true), HttpStatus.OK);
	}

	@GetMapping("/post/cat/{categoryId}")

	public ResponseEntity<List<PostDto>> postDtoBycategory(@PathVariable(value = "categoryId") Integer categoryId) {
		List<PostDto> postDtos = this.postService.findBypostCategoryId(categoryId);

		return new ResponseEntity<>(postDtos, HttpStatus.OK);
	}

	@GetMapping("/post/user/{userId}")

	public ResponseEntity<List<PostDto>> postDtoByUser(@PathVariable(value = "userId") Integer userId) {
		List<PostDto> postDtos = this.postService.findPostByUserId(userId);

		return new ResponseEntity<>(postDtos, HttpStatus.OK);
	}

	@GetMapping("/post/sort")
	public ResponseEntity<PageResponse> getAllSorting(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

		PageResponse pageDtos = postService.getAllSorting(pageNumber, pageSize, sortBy, sortDir);

		return new ResponseEntity<>(pageDtos, HttpStatus.OK);

	}

	// seraching
	@GetMapping("/post/key")
	public ResponseEntity<List<PostDto>> Searching(@RequestParam(value = "title", required = false) String title)

	{
		List<PostDto> postDtos = this.postService.findByTitle(title);

		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}

	// seraching
	@GetMapping("/post/search/{title}")
	public ResponseEntity<List<PostDto>> SearchingKey(@PathVariable(value = "title") String title)

	{
		List<PostDto> postDtos = this.postService.search(title);

		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}

	@PostMapping("/image/{postId}")
	public ResponseEntity<PostDto> PostImage(@PathVariable(value = "postId") Integer postId,
			@RequestParam(value = "file") MultipartFile file) throws IOException {
		String iamgeString = filsFileService.uploadFile(pathString, file);

		PostDto postDto = postService.findByPostid(postId);

		postDto.setImageName(iamgeString);

		PostDto postDto2 = postService.updatePost(postDto, postId);
		return new ResponseEntity<>(postDto2, HttpStatus.OK);
	}

	
	
	@GetMapping(value = "/image/{imagename}", produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public void getRource(@PathVariable(value = "imagename") String imagename, HttpServletResponse response)
			throws IOException {

		InputStream inputStream = filsFileService.getsource(pathString, imagename);

		response.setContentType(org.springframework.http.MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());

	}
}
