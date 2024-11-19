package com.example.blogs.service;

import com.example.blogs.model.Blog;
import com.example.blogs.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    // To delete - another change
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
