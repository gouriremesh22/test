package com.example.blogs.controller;

import com.example.blogs.model.Blog;
import com.example.blogs.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/{id}")
    public Optional<Blog> getBlogById(@PathVariable Long id) {
        Optional<Blog> blog = blogService.getBlogById(id);
        if (blog.isPresent()) {
            return blog;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found with id " + id);
        }
    }

    @PostMapping
    public Blog createBlog(@RequestBody Blog blog) {
        return blogService.saveBlog(blog);
    }

    @PutMapping("/{id}")
    public Blog updateBlog(@PathVariable Long id, @RequestBody Blog blogDetails) {
        Optional<Blog> blogOptional = blogService.getBlogById(id);
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            blog.setTitle(blogDetails.getTitle());
            blog.setContent(blogDetails.getContent());
            blog.setAuthor(blogDetails.getAuthor());
            blog.setProductId(blogDetails.getProductId());
            return blogService.saveBlog(blog);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found with id " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id) {
        Optional<Blog> blogOptional = blogService.getBlogById(id);
        if (blogOptional.isPresent()) {
            blogService.deleteBlog(id);
            return new ResponseEntity<>("Blog deleted successfully", HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found with id " + id);
        }
    }
}
