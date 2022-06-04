package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.LikeComments;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikeCommentsService {

    @Transactional
    LikeComments createLikeComment(LikeComments likeComments);

    void delete(long id);

    Long countAllLikeCommentsByPostsCommentsId(Long postsCommentsId) throws Exception;

    List<String> getAllUsernameLikedCommentsByPostCommentId(Long postsCommentsId) throws Exception;
}
