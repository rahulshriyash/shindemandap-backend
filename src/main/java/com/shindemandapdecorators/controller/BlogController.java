package com.shindemandapdecorators.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.shindemandapdecorators.dto.BlogDto;

import com.shindemandapdecorators.entity.BlogEntity;
import com.shindemandapdecorators.exception.RecordNotFoundException;
import com.shindemandapdecorators.service.BlogService;

@RestController
@RequestMapping
public class BlogController {
	
	@Autowired
	private BlogService blogService;

	
	@PostMapping("/blog")
	public BlogDto addEnquiry(@ModelAttribute BlogDto blogDto) throws IOException {
		System.out.println("in post Blog getBlogTitle");
		
		BlogEntity blogEntity = blogService.addBlog(blogDto);
		blogDto.setId(blogEntity.getId());
		return blogDto;
	}
	
	@PutMapping("/blog/{id}")
	public BlogDto updateBlog(@PathVariable int id, @ModelAttribute BlogDto blogDto) throws IOException {
		blogDto.setId(id);
		blogService.updateBlog(blogDto);
		return blogDto;
	}
	
	@GetMapping("/blogs")
	public List<BlogEntity> getBlogs() {
		System.out.println("in getBlogs contoller git testing");
		return blogService.getBlogs();
	}
	
	@GetMapping("/blog/{id}")
	public ResponseEntity<BlogEntity> getBlogById(@PathVariable int id) {
		BlogEntity blogEntity = blogService.getBlogById(id);
		if (blogEntity == null) {
			throw new RecordNotFoundException("Invalid enquiry id : " + id);
		}
		return new ResponseEntity<BlogEntity>(blogEntity, HttpStatus.OK);

	}

	@DeleteMapping("/blog/{id}")
	public ResponseEntity<BlogEntity> deleteBlog(@PathVariable int id) {
		BlogEntity blogEntity = this.blogService.deleteBlog(id);
		if (blogEntity == null) {
			throw new RecordNotFoundException("Invalid enquiry id : " + id);
		}
		return new ResponseEntity<BlogEntity>(blogEntity, HttpStatus.OK);

	}
}
