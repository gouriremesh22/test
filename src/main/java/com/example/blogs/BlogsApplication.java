package com.example.blogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.blogs", "com.example.blogs.model", "com.example.blogs.repository", "com.example.blogs.controller", "com.example.blogs.service"})
@EnableJpaRepositories(basePackages = "com.example.blogs.repository")
@EntityScan("com.example.blogs")
public class BlogsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogsApplication.class, args);
	}
}
