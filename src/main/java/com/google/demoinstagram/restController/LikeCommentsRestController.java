package com.google.demoinstagram.restController;

import com.google.demoinstagram.entity.LikeComments;
import com.google.demoinstagram.service.LikeCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/like-comments")
public class LikeCommentsRestController {

    private LikeCommentsService likeCommentsService;

    @Autowired
    public void setLikeCommentsService(LikeCommentsService likeCommentsService) {
        this.likeCommentsService = likeCommentsService;
    }

    // http://localhost:8085/api/like-comments/create
    @PostMapping(value = "/create")
    public ResponseEntity<LikeComments> create(@RequestBody LikeComments likeComments) {
        return new ResponseEntity<>(likeCommentsService.create(likeComments), HttpStatus.CREATED);
    }

    // http://localhost:8085/api/like-comments/count-all-like/1/1
    @GetMapping(value = {"/count-all-like/{postsCommentsId}/{postsId}"})
    public Long countAllLikeByPostsId(@PathVariable("postsCommentsId") Long postsCommentsId,
                                      @PathVariable("postsId") Long postsId) throws Exception {
        return likeCommentsService.countAllLikeCommentsByPostsCommentsIdAndPostsId(postsCommentsId, postsId);
    }

    // http://localhost:8085/api/like-comments/get-all-username-liked-comment/1
    @GetMapping(value = {"/get-all-username-liked-comment/{postsCommentsId}"})
    public List<String> getAllUsernameLikedCommentsByPostsCommentsId(@PathVariable("postsCommentsId") Long postsCommentsId) throws Exception {
        return likeCommentsService.getAllUsernameLikedCommentsByPostCommentId(postsCommentsId);
    }
}
