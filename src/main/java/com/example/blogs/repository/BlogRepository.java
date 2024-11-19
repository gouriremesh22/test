package com.example.blogs.repository;

import com.example.blogs.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    // Custom query methods can be added here if needed
}
