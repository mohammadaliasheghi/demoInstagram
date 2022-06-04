package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.SavedPosts;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SavedPostsService {

    @Transactional
    SavedPosts addSavedPost(SavedPosts savedPosts);

    void deleteSavedPost(long id);

    Long countAllSavedPosts();

    SavedPosts getSavedPost(Long id);

    List<SavedPosts> savedPostListInfo();

    Long countSavedPostByUsersId(Long usersId);

    List<SavedPosts> getAllSavedPostByUsersId(Long usersId);
}
