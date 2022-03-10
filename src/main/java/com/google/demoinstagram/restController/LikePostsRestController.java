package com.google.demoinstagram.restController;

import com.google.demoinstagram.entity.LikePosts;
import com.google.demoinstagram.service.LikePostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/like-post")
public class LikePostsRestController {

    private LikePostsService likePostsService;

    @Autowired
    public void setLikePostsService(LikePostsService likePostsService) {
        this.likePostsService = likePostsService;
    }

    // http://localhost:8085/api/like-post/create
    @PostMapping(value = "/create")
    public ResponseEntity<LikePosts> create(@RequestBody LikePosts likePosts) {
        return new ResponseEntity<>(likePostsService.create(likePosts), HttpStatus.CREATED);
    }
}
