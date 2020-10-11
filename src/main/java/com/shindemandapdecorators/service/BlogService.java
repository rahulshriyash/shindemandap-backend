package com.shindemandapdecorators.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shindemandapdecorators.dto.BlogDto;
import com.shindemandapdecorators.entity.BlogEntity;
import com.shindemandapdecorators.repository.BlogRepository;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;

	public BlogEntity addBlog(BlogDto blogDto) throws IOException {
		System.out.println("in addBlg Service");
		BlogEntity blogEntity = new BlogEntity();
		blogEntity.setBlogTitle(blogDto.getBlogTitle());

		blogEntity.setBlogDescription(blogDto.getBlogDescription());
		blogEntity.setBlogImage(blogDto.getBlogImage().getBytes());

		blogEntity = blogRepository.saveAndFlush(blogEntity);
		return blogEntity;
	}

	public BlogEntity updateBlog(BlogDto blogDto) throws IOException {
		Optional<BlogEntity> blogDB = this.blogRepository.findById(blogDto.getId());
		System.out.println("in updateBlog Service" + blogDB);
		if (blogDB.isPresent()) {
			System.out.println("in if.................");
			BlogEntity blogEntity = blogDB.get();
			System.out.println("getting title................." + blogDto.getBlogTitle());
			blogEntity.setBlogTitle(blogDto.getBlogTitle());
			System.out.println("getting desc................." + blogDto.getBlogDescription());
			blogEntity.setBlogDescription(blogDto.getBlogDescription());
			System.out.println("getting image................." + blogDto.getBlogImage().getBytes());
			blogEntity.setBlogImage(blogDto.getBlogImage().getBytes());
			blogEntity = blogRepository.saveAndFlush(blogEntity);
			return blogEntity;
		} else {
			System.out.println("Record not found with id : " + blogDB.get());
			return null;
		}

	}

	public List<BlogEntity> getBlogs() {
		System.out.println("in getBlogs");
		return blogRepository.findAll();
	}

	public BlogEntity getBlogById(int blogId) {

		Optional<BlogEntity> blogDB = this.blogRepository.findById(blogId);

		if (blogDB.isPresent()) {
			return blogDB.get();
		} else {
			System.out.println("Record not found with id : " + blogId);
			return null;
		}
	}

	public BlogEntity deleteBlog(int blogId) {
		Optional<BlogEntity> blogDB = this.blogRepository.findById(blogId);
		if (blogDB.isPresent()) {
			this.blogRepository.delete(blogDB.get());
			return blogDB.get();
		} else {
			System.out.println("Record not found with id : " + blogId);
			return null;
		}

	}
}
