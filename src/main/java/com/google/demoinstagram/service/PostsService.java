package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.Posts;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostsService {

    @Transactional
    Posts savePosts(Posts posts);

    List<Posts> getAllPosts();

    void deletePosts(Long id);

    Posts getPostById(Long id);
}
