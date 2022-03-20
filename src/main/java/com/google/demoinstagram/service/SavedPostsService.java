package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.SavedPosts;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SavedPostsService {

    @Transactional
    SavedPosts create(SavedPosts savedPosts);

    void delete(long id);

    Long countAllSavedPosts();

    SavedPosts get(Long id);

    List<SavedPosts> listInfo();

    Long countSavedPostByUsersId(Long usersId);

    List<SavedPosts> getAllSavedPostByUsersId(Long usersId);
}
