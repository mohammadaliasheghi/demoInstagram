package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.Posts;
import com.google.demoinstagram.excption.ResourceNotFoundException;
import com.google.demoinstagram.repository.PostsRepository;
import com.google.demoinstagram.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    @Override
    public Posts create(Posts posts) {
        return postsRepository.save(posts);
    }

    @Transactional
    @Override
    public Posts update(Posts posts, Long id) {
        Posts existPosts = postsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Posts", "id", id));

        existPosts.setCover(posts.getCover());
        existPosts.setDescription(posts.getDescription());
        existPosts.setUsersId(posts.getUsersId());
        existPosts.setTitle(posts.getTitle());

        postsRepository.save(existPosts);
        return existPosts;
    }

    @Override
    public List<Posts> listInfo() {
        return postsRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        postsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", id));
        postsRepository.deleteById(id);
    }

    @Override
    public Posts get(Long id) {
        return postsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Posts", "id", id));
    }

    @Override
    public List<Posts> getAllFollowingPostsByUserId(Long userId) throws Exception {
        if (userId == null)
            throw new Exception("UsersIdCannotBeNull");
        return postsRepository.getAllFollowingPostsByUserId(userId);
    }

    @Override
    public List<Posts> getAllPostsByHashTag(String hashTag) throws Exception {
        if (hashTag == null || hashTag.length() == 0)
            throw new Exception("HashTagCannotBeNull");
        return postsRepository.getAllPostsByHashTag(hashTag);
    }

    @Override
    public void deleteAllPostsByHashTag(String hashTag) throws Exception {
        if (hashTag == null || hashTag.length() == 0)
            throw new Exception("HashTagCannotBeNull");
        List<Posts> posts = postsRepository.getAllPostsByHashTag(hashTag);
        postsRepository.deleteAll(posts);
    }
}
