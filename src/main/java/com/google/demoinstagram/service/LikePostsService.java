package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.LikePosts;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikePostsService {
    @Transactional
    LikePosts createLikePost(LikePosts likePosts) throws Exception;

    void delete(long id);

    List<LikePosts> listInfoUsersLikedPost(Long postId) throws Exception;

    Long countAllLikeByPostsId(Long postId) throws Exception;

    List<String> getAllUsernameLikedPostByPostId(Long postId) throws Exception;
}
