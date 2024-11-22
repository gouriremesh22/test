package com.example.blogs.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String BLOG_EXCHANGE = "blogExchange";
    public static final String BLOG_QUEUE = "blogQueue";
    public static final String ROUTING_KEY = "blogRoutingKey";

    @Bean
    public Exchange blogExchange() {
        return ExchangeBuilder.topicExchange(BLOG_EXCHANGE).durable(true).build();
    }

    @Bean
    public Queue blogQueue() {
        return QueueBuilder.durable(BLOG_QUEUE).build();
    }

    @Bean
    public Binding binding(Queue blogQueue, Exchange blogExchange) {
        return BindingBuilder.bind(blogQueue).to(blogExchange).with(ROUTING_KEY).noargs();
    }
}
