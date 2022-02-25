package com.google.demoinstagram.repository;

import com.google.demoinstagram.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
