package com.google.demoinstagram.restController;

import com.google.demoinstagram.entity.LikePosts;
import com.google.demoinstagram.service.LikePostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/like-post")
public class LikePostsRestController {

    private LikePostsService likePostsService;

    @Autowired
    public void setLikePostsService(LikePostsService likePostsService) {
        this.likePostsService = likePostsService;
    }

    // http://localhost:8085/demoInstagram/api/like-post/create
    @PostMapping(value = "/create")
    public ResponseEntity<LikePosts> create(@RequestBody LikePosts likePosts) throws Exception {
        return new ResponseEntity<>(likePostsService.createLikePost(likePosts), HttpStatus.CREATED);
    }

    // http://localhost:8085/demoInstagram/api/like-post/1
    @GetMapping(value = {"/{postsId}"})
    public List<LikePosts> listInfoUsersLikedPost(@PathVariable("postsId") Long postsId) throws Exception {
        return likePostsService.listInfoUsersLikedPost(postsId);
    }

    // http://localhost:8085/demoInstagram/api/like-post/count-all-like/1
    @GetMapping(value = {"/count-all-like/{postsId}"})
    public Long countAllLikeByPostsId(@PathVariable("postsId") Long postsId) throws Exception {
        return likePostsService.countAllLikeByPostsId(postsId);
    }

    // http://localhost:8085/demoInstagram/api/like-post/get-all-username-liked-post/1
    @GetMapping(value = {"/get-all-username-liked-post/{postsId}"})
    public List<String> getAllUsernameLikedPostByPostId(@PathVariable("postsId") Long postsId) throws Exception {
        return likePostsService.getAllUsernameLikedPostByPostId(postsId);
    }
}
