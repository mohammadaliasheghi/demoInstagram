package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.PostsComments;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostsCommentsService {

    @Transactional
    PostsComments addComment(PostsComments postsComments);

    @Transactional
    PostsComments updateComment(PostsComments postsComments, Long id);

    void deleteComment(Long id);

    PostsComments getComment(Long id);

    List<PostsComments> commentListInfo();

    List<PostsComments> listInfoCommentPostByPostsId(Long postId) throws Exception;

    Long countAllCommentByPostsId(Long postId) throws Exception;

    void updateCountLikeComment(PostsComments postsComments, Long id);
}
