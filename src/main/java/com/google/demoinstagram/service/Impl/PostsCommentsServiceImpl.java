package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.PostsComments;
import com.google.demoinstagram.entity.Users;
import com.google.demoinstagram.excption.ResourceNotFoundException;
import com.google.demoinstagram.repository.PostsCommentsRepository;
import com.google.demoinstagram.service.PostsCommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsCommentsServiceImpl implements PostsCommentsService {

    private final PostsCommentsRepository postsCommentsRepository;

    @Transactional
    @Override
    public PostsComments add(PostsComments postsComments) {
        return postsCommentsRepository.save(postsComments);
    }

    @Transactional
    @Override
    public PostsComments update(PostsComments postsComments, Long id) {
        PostsComments existPostsComments = postsCommentsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("PostsComments", "id", id));

        existPostsComments.setText(existPostsComments.getText());
        existPostsComments.setUsersId(existPostsComments.getUsersId());
        existPostsComments.setPostsId(existPostsComments.getPostsId());

        postsCommentsRepository.save(existPostsComments);
        return existPostsComments;
    }

    @Override
    public void delete(Long id) {
        postsCommentsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("postsComments", "id", id));
        postsCommentsRepository.deleteById(id);
    }

    @Override
    public PostsComments get(Long id) {
        return postsCommentsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("postsComments", "id", id));
    }

    @Override
    public List<PostsComments> list() {
        return postsCommentsRepository.findAll();
    }

    public List<PostsComments> listInfoCommentPostByPostsId(Long postId) throws Exception {
        if (postId == null)
            throw new Exception("PostsIdCannotBeNull");
        return postsCommentsRepository.getAllByPostsId_Id(postId);
    }
}
