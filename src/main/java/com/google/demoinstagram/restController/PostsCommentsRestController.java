package com.google.demoinstagram.restController;

import com.google.demoinstagram.entity.PostsComments;
import com.google.demoinstagram.service.PostsCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/posts-comments")
public class PostsCommentsRestController {

    private PostsCommentsService postsCommentsService;

    @Autowired
    public void setPostsCommentsService(PostsCommentsService postsCommentsService) {
        this.postsCommentsService = postsCommentsService;
    }

    // http://localhost:8085/api/posts-comments/add
    @PostMapping(value = "/add")
    public ResponseEntity<PostsComments> save(@RequestBody PostsComments postsComments) {
        return new ResponseEntity<>(postsCommentsService.add(postsComments), HttpStatus.CREATED);
    }

    // http://localhost:8085/api/posts-comments/
    @GetMapping(value = {"/", ""})
    public List<PostsComments> listInfo() {
        return postsCommentsService.list();
    }

    // http://localhost:8085/api/posts-comments/get/1
    @GetMapping("/get/{id}")
    public ResponseEntity<PostsComments> get(@PathVariable("id") long userId) {
        return new ResponseEntity<>(postsCommentsService.get(userId), HttpStatus.OK);
    }

    // http://localhost:8085/api/posts-comments/update/1
    @PutMapping("/update/{id}")
    public ResponseEntity<PostsComments> update(@PathVariable("id") long id
            , @RequestBody PostsComments postsComments) {
        return new ResponseEntity<>(postsCommentsService.update(postsComments, id), HttpStatus.OK);
    }

    // http://localhost:8085/api/posts-comments/delete/1
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {

        // delete user from DB
        postsCommentsService.delete(id);

        return new ResponseEntity<>("CommentDeletedSuccessfully!", HttpStatus.OK);
    }
}
