package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.Posts;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostsService {

    @Transactional
    Posts createPost(Posts posts) throws Exception;

    @Transactional
    void updateCountLike(Posts posts, Long id);

    void updateCountComment(Posts posts, Long id);

    List<Posts> postListInfo();

    void deletePost(Long id);

    Posts getPost(Long id);

    List<Posts> getAllFollowingPostsByUserId(Long userId) throws Exception;

    List<Posts> getAllPostsByHashTag(String hashTag) throws Exception;

    void deleteAllPostsByHashTag(String hashTag) throws Exception;

    Posts getPostByMaxLike();

    List<Posts> orderByCountLikeDesc();
}
