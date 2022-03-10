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

    // http://localhost:8085/api/posts/create
    @PostMapping(value = "/create")
    public ResponseEntity<Posts> create(@RequestBody Posts posts) {
        return new ResponseEntity<>(postsService.create(posts), HttpStatus.CREATED);
    }

    // http://localhost:8085/api/posts/update/1
    @PutMapping("/update/{id}")
    public ResponseEntity<Posts> update(@PathVariable("id") long id
            , @RequestBody Posts posts) {
        return new ResponseEntity<>(postsService.update(posts, id), HttpStatus.OK);
    }

    // http://localhost:8085/api/posts
    @GetMapping(value = {"", "/"})
    public List<Posts> listInfo() {
        return postsService.listInfo();
    }

    // http://localhost:8085/api/posts/delete/1
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        postsService.delete(id);
        return new ResponseEntity<>("PostDeletedSuccessfully!", HttpStatus.OK);
    }

    // http://localhost:8085/api/posts/getPostById/1
    @GetMapping(value = "/getPostById/{id}")
    public ResponseEntity<Posts> get(@PathVariable("id") long id) {
        return new ResponseEntity<>(postsService.get(id), HttpStatus.OK);
    }
}
