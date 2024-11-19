package com.example.blogs;

import com.example.blogs.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class BlogsApplicationTests {

	@Autowired
	private BlogService blogService;

	@Test
	void contextLoads() {
		// Test logic
	}
}
