package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.SavedPosts;
import com.google.demoinstagram.excption.ResourceNotFoundException;
import com.google.demoinstagram.repository.SavedPostsRepository;
import com.google.demoinstagram.service.SavedPostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SavedPostsServiceImpl implements SavedPostsService {

    private final SavedPostsRepository savedPostsRepository;

    @Transactional
    @Override
    public SavedPosts create(SavedPosts savedPosts) {
        if (savedPostsRepository.existsSavedPostsByUsersId_IdAndPostsId_Id(savedPosts.getUsersId().getId(), savedPosts.getPostsId().getId())) {
            SavedPosts newSavedPosts = savedPostsRepository.getSavedPostsByUsersId_IdAndPostsId_Id(savedPosts.getUsersId().getId(), savedPosts.getPostsId().getId());
            if (newSavedPosts != null && newSavedPosts.getId() != null) {
                this.delete(newSavedPosts.getId());
            }
        } else {
            if (savedPosts.getSaved().equals(true))
                return savedPostsRepository.save(savedPosts);
            else
                return null;
        }

        return null;
    }

    @Override
    public void delete(long id) {
        savedPostsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("SavedPosts", "Id", id));
        savedPostsRepository.deleteById(id);
    }

    @Override
    public Long countAllSavedPosts() {
        return savedPostsRepository.countAllSavedPosts();
    }

    @Override
    public SavedPosts get(Long id) {
        return savedPostsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("SavedPosts", "id", id));
    }

    @Override
    public List<SavedPosts> listInfo() {
        return savedPostsRepository.findAll();
    }

    @Override
    public Long countSavedPostByUsersId(Long usersId) {
        return savedPostsRepository.countByUsersId_Id(usersId);
    }

    @Override
    public List<SavedPosts> getAllSavedPostByUsersId(Long usersId) {
        return savedPostsRepository.getAllByUsersId_Id(usersId);
    }
}
