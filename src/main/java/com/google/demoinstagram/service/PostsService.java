package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.Posts;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostsService {

    @Transactional
    Posts create(Posts posts);

    @Transactional
    Posts update(Posts posts, Long id);

    List<Posts> listInfo();

    void delete(Long id);

    Posts get(Long id);
}
