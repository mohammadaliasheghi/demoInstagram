package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.LikePosts;
import com.google.demoinstagram.entity.Users;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikePostsService {
    @Transactional
    LikePosts create(LikePosts likePosts);

    void delete(long id);

    List<LikePosts> listInfoUsersLikedPost(Long postId) throws Exception;
}
