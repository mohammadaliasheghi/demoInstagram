package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.LikePosts;
import org.springframework.transaction.annotation.Transactional;

public interface LikePostsService {
    @Transactional
    LikePosts create(LikePosts likePosts);

    void delete(long id);

    LikePosts get(Long id);
}
