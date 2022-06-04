package com.google.demoinstagram.restController;

import com.google.demoinstagram.entity.Posts;
import com.google.demoinstagram.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/posts")
public class PostsRestController {

    private PostsService postsService;

    @Autowired
    public void setPostsService(PostsService postsService) {
        this.postsService = postsService;
    }

    // http://localhost:8085/demoInstagram/api/posts/create
    @PostMapping(value = "/create")
    public ResponseEntity<Posts> create(@RequestBody Posts posts) throws Exception {
        return new ResponseEntity<>(postsService.createPost(posts), HttpStatus.CREATED);
    }

    // http://localhost:8085/demoInstagram/api/posts
    @GetMapping(value = {"", "/"})
    public List<Posts> listInfo() {
        return postsService.postListInfo();
    }

    // http://localhost:8085/demoInstagram/api/posts/delete/1
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        postsService.deletePost(id);
        return new ResponseEntity<>("Post Deleted Successfully!", HttpStatus.OK);
    }

    // http://localhost:8085/demoInstagram/api/posts/getPostById/1
    @GetMapping(value = "/getPostById/{id}")
    public ResponseEntity<Posts> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postsService.getPost(id), HttpStatus.OK);
    }

    // http://localhost:8085/demoInstagram/api/posts/get-all-following-posts/1
    @GetMapping(value = {"/get-all-following-posts/{userId}"})
    public List<Posts> getAllFollowingPostsByUserId(@PathVariable("userId") Long userId) throws Exception {
        return postsService.getAllFollowingPostsByUserId(userId);
    }

    // http://localhost:8085/demoInstagram/api/posts/get-all-posts-by-hashTag/sport
    @GetMapping(value = {"/get-all-posts-by-hashTag/{hashTag}"})
    public List<Posts> getAllPostsByHashTag(@PathVariable("hashTag") String hashTag) throws Exception {
        return postsService.getAllPostsByHashTag(hashTag);
    }

    // http://localhost:8085/demoInstagram/api/posts/delete-all-posts-by-hashTag/sport
    @DeleteMapping(value = "/delete-all-posts-by-hashTag/{hashTag}")
    public ResponseEntity<String> deleteAllPostsByHashTag(@PathVariable("hashTag") String hashTag) throws Exception {
        postsService.deleteAllPostsByHashTag(hashTag);
        return new ResponseEntity<>("Posts Deleted Successfully!", HttpStatus.OK);
    }

    // http://localhost:8085/demoInstagram/api/posts/get-post-by-max-like
    @GetMapping(value = "/get-post-by-max-like")
    public ResponseEntity<Posts> getPostByMaxLike() {
        return new ResponseEntity<>(postsService.getPostByMaxLike(), HttpStatus.OK);
    }

    // http://localhost:8085/demoInstagram/api/posts/order-by-count-like-desc
    @GetMapping(value = "/order-by-count-like-desc")
    public List<Posts> orderByCountLikeDesc() {
        return postsService.orderByCountLikeDesc();
    }
}
