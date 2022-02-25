package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.Posts;
import com.google.demoinstagram.excption.ResourceNotFoundException;
import com.google.demoinstagram.repository.PostsRepository;
import com.google.demoinstagram.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;

    @Override
    public Posts savePosts(Posts posts) {
        return postsRepository.save(posts);
    }

    @Override
    public List<Posts> getAllPosts() {
        return postsRepository.findAll();
    }

    @Override
    public void deletePosts(Long id) {
        postsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", id));
        postsRepository.deleteById(id);
    }

    @Override
    public Posts getPostById(Long id) {
        return postsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Posts", "id", id));
    }
}
