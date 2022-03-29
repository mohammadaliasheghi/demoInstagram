package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.Posts;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostsService {

    @Transactional
    Posts create(Posts posts) throws Exception;

    @Transactional
    Posts update(Posts posts, Long id) throws Exception;

    @Transactional
    void updateCountLike(Posts posts, Long id);

    List<Posts> listInfo();

    void delete(Long id);

    Posts get(Long id);

    List<Posts> getAllFollowingPostsByUserId(Long userId) throws Exception;

    List<Posts> getAllPostsByHashTag(String hashTag) throws Exception;

    void deleteAllPostsByHashTag(String hashTag) throws Exception;

    Posts getPostByMaxLike();

    List<Posts> orderByCountLikeDesc();
}
