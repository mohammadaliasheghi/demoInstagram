package com.google.demoinstagram.restController;

import com.google.demoinstagram.entity.SavedPosts;
import com.google.demoinstagram.service.SavedPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/saved-post")
public class SavedPostsRestController {

    private SavedPostsService savedPostsService;

    @Autowired
    public void setSavedPostsService(SavedPostsService savedPostsService) {
        this.savedPostsService = savedPostsService;
    }

    // http://localhost:8085/demoInstagram/api/saved-post/count-all
    @GetMapping(value = {"/count-all"})
    public Long countAllSavedPosts() {
        return savedPostsService.countAllSavedPosts();
    }

    // http://localhost:8085/demoInstagram/api/saved-post/get/1
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<SavedPosts> get(@PathVariable("id") long id) {
        return new ResponseEntity<>(savedPostsService.getSavedPost(id), HttpStatus.OK);
    }

    // http://localhost:8085/demoInstagram/api/saved-post
    @GetMapping(value = {"", "/"})
    public List<SavedPosts> listInfo() {
        return savedPostsService.savedPostListInfo();
    }

    // http://localhost:8085/demoInstagram/api/saved-post/count-saved-by-users-id/1
    @GetMapping(value = "/count-saved-by-users-id/{id}")
    public Long countSavedPostByUsersId(@PathVariable("id") long id) {
        return savedPostsService.countSavedPostByUsersId(id);
    }

    // http://localhost:8085/demoInstagram/api/saved-post/get-all-saved-by-users-id/1
    @GetMapping(value = "/get-all-saved-by-users-id/{id}")
    public List<SavedPosts> getAllSavedPostByUsersId(@PathVariable("id") long id) {
        return savedPostsService.getAllSavedPostByUsersId(id);
    }
}
