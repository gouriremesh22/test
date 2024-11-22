package com.example.blogs.service;

import com.example.blogs.config.RabbitMQConfig;
import com.example.blogs.model.Blog;
import com.example.blogs.repository.BlogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private static final Logger logger = LoggerFactory.getLogger(BlogService.class);

    private final BlogRepository blogRepository;
    private final RabbitMQSender rabbitMQSender;

    @Autowired
    public BlogService(BlogRepository blogRepository, RabbitMQSender rabbitMQSender) {
        this.blogRepository = blogRepository;
        this.rabbitMQSender = rabbitMQSender;
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public Blog saveBlog(Blog blog) {
        Blog savedBlog = blogRepository.save(blog);
        logger.info("Saved blog: {}", savedBlog);
        rabbitMQSender.send(RabbitMQConfig.BLOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, savedBlog);
        return savedBlog;
    }

    // for delete
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
        logger.info("Deleted blog with id: {}", id);
        rabbitMQSender.send(RabbitMQConfig.BLOG_EXCHANGE, RabbitMQConfig.ROUTING_KEY, "Deleted blog with id: " + id);
    }
}
