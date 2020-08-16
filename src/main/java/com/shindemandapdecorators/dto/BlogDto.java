package com.shindemandapdecorators.dto;

import org.springframework.web.multipart.MultipartFile;

public class BlogDto {
	
	private int id;
	
	private String blogTitle;
	
	private String blogDescription;
	
	private MultipartFile blogImage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogDescription() {
		return blogDescription;
	}

	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}

	public MultipartFile getBlogImage() {
		return blogImage;
	}

	public void setBlogImage(MultipartFile blogImage) {
		this.blogImage = blogImage;
	}

	
	

}
