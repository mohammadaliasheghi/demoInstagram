package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.Posts;
import com.google.demoinstagram.entity.PostsComments;
import com.google.demoinstagram.excption.ResourceNotFoundException;
import com.google.demoinstagram.repository.PostsCommentsRepository;
import com.google.demoinstagram.service.PostsCommentsService;
import com.google.demoinstagram.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsCommentsServiceImpl implements PostsCommentsService {

    private final PostsCommentsRepository postsCommentsRepository;
    private final PostsService postsService;

    @Transactional
    @Override
    public PostsComments addComment(PostsComments postsComments) {
        Posts posts = postsService.getPost(postsComments.getPosts().getId());
        Long countComment = posts.getCountComment() + 1;
        posts.setCountComment(countComment);
        postsService.updateCountComment(posts, postsComments.getPosts().getId());
        return postsCommentsRepository.save(postsComments);
    }

    @Transactional
    @Override
    public PostsComments updateComment(PostsComments postsComments, Long id) {
        PostsComments existPostsComments = postsCommentsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("PostsComments", "id", id));

        existPostsComments.setText(postsComments.getText());
        existPostsComments.setUsers(postsComments.getUsers());
        existPostsComments.setPosts(postsComments.getPosts());

        postsCommentsRepository.save(existPostsComments);
        return existPostsComments;
    }

    @Transactional
    @Override
    public void updateCountLikeComment(PostsComments postsComments, Long id) {
        PostsComments existPostsComment = postsCommentsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Posts", "id", id));
        existPostsComment.setCountLikeComment(postsComments.getCountLikeComment());
        postsCommentsRepository.save(existPostsComment);
    }

    @Override
    public void deleteComment(Long id) {
        PostsComments postsComments = postsCommentsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("postsComments", "id", id));
        postsCommentsRepository.deleteById(id);
        Posts posts = postsService.getPost(postsComments.getPosts().getId());
        Long countComment = posts.getCountComment() - 1;
        posts.setCountComment(countComment);
        postsService.updateCountComment(posts, postsComments.getPosts().getId());
    }

    @Override
    public PostsComments getComment(Long id) {
        return postsCommentsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("postsComments", "id", id));
    }

    @Override
    public List<PostsComments> commentListInfo() {
        return postsCommentsRepository.findAll();
    }

    @Override
    public List<PostsComments> listInfoCommentPostByPostsId(Long postId) throws Exception {
        if (postId == null)
            throw new Exception("PostsIdCannotBeNull");
        return postsCommentsRepository.getAllByPosts_Id(postId);
    }

    @Override
    public Long countAllCommentByPostsId(Long postId) throws Exception {
        if (postId == null)
            throw new Exception("PostsIdCannotBeNull");
        return postsCommentsRepository.countAllByPosts_Id(postId);
    }
}
