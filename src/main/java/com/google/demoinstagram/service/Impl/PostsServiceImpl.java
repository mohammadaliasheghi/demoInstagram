package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.HashTag;
import com.google.demoinstagram.entity.Posts;
import com.google.demoinstagram.excption.ResourceNotFoundException;
import com.google.demoinstagram.repository.PostsRepository;
import com.google.demoinstagram.service.HashTagService;
import com.google.demoinstagram.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;
    private HashTagService hashTagService;

    @Autowired
    public void setHashTagService(HashTagService hashTagService) {
        this.hashTagService = hashTagService;
    }

    @Transactional
    @Override
    public Posts createPost(Posts posts) throws Exception {
        Posts created = postsRepository.save(posts);
        StringBuilder text = new StringBuilder(posts.getDescription()).append(" ");
        List<HashTag> hashTagsList = new ArrayList<>();
        createHashTags(hashTagsList, text, created);
        hashTagService.createHashTagList(hashTagsList);
        return created;
    }

    private void createHashTags(List<HashTag> hashTagsList, StringBuilder text, Posts created) {
        for (int i = 0; i < text.length(); i++)
            if (text.charAt(i) == '#') {
                HashTag hashTag = new HashTag();
                String tag = getHashTagText(i, String.valueOf(text));
                hashTag.setText(tag);
                hashTag.setPosts(created);
                hashTagsList.add(hashTag);
            }
    }

    private String getHashTagText(int c, String text) {
        String findHashTag = text.substring(c + 1);
        return findHashTag.substring(0, findHashTag.indexOf(" "));
    }

    @Transactional
    @Override
    public void updateCountLike(Posts posts, Long id) {
        Posts existPosts = postsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Posts", "id", id));
        existPosts.setCountLike(posts.getCountLike());
        postsRepository.save(existPosts);
    }

    @Transactional
    @Override
    public void updateCountComment(Posts posts, Long id) {
        Posts existPosts = postsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Posts", "id", id));
        existPosts.setCountComment(posts.getCountComment());
        postsRepository.save(existPosts);
    }

    @Override
    public List<Posts> postListInfo() {
        return postsRepository.findAll();
    }

    @Override
    public void deletePost(Long id) {
        postsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", id));
        postsRepository.deleteById(id);
    }

    @Override
    public Posts getPost(Long id) {
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

    @Override
    public Posts getPostByMaxLike() {
        return postsRepository.findFirstByOrderByCountLikeDesc();
    }

    @Override
    public List<Posts> orderByCountLikeDesc() {
        return postsRepository.getAllByOrderByCountLikeDesc();
    }
}
