package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.PostsComments;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostsCommentsService {

    @Transactional
    PostsComments add(PostsComments postsComments);

    @Transactional
    PostsComments update(PostsComments postsComments, Long id);

    void delete(Long id);

    PostsComments get(Long id);

    List<PostsComments> list();

    List<PostsComments> listInfoCommentPostByPostsId(Long postId) throws Exception;
}
