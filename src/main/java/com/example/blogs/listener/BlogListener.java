package com.example.blogs.listener;

import com.example.blogs.config.RabbitMQConfig;
import com.example.blogs.model.Blog;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BlogListener {

    @RabbitListener(queues = RabbitMQConfig.BLOG_QUEUE)
    public void handleBlogMessage(Blog blog) {
        System.out.println("Received message: " + blog);
        // Handle the message (e.g., process it, store it in a database, etc.)
    }
}
