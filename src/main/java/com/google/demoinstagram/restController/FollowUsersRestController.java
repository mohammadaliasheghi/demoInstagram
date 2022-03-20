package com.google.demoinstagram.restController;

import com.google.demoinstagram.entity.FollowUsers;
import com.google.demoinstagram.service.FollowUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/follow-users")
public class FollowUsersRestController {

    private FollowUsersService followUsersService;

    @Autowired
    public void setFollowUsersService(FollowUsersService followUsersService) {
        this.followUsersService = followUsersService;
    }

    // http://localhost:8085/api/follow-users/create
    @PostMapping(value = "/create")
    public ResponseEntity<FollowUsers> create(@RequestBody FollowUsers followUsers) throws Exception {
        return new ResponseEntity<>(followUsersService.create(followUsers), HttpStatus.CREATED);
    }

    // http://localhost:8085/api/follow-users/
    @GetMapping(value = {"/", ""})
    public List<FollowUsers> listInfo() {
        return followUsersService.listInfo();
    }

    // http://localhost:8085/api/follow-users/get/1
    @GetMapping("/get/{id}")
    public ResponseEntity<FollowUsers> get(@PathVariable("id") long id) {
        return new ResponseEntity<>(followUsersService.get(id), HttpStatus.OK);
    }

    // http://localhost:8085/api/follow-users/count-all-following/1
    @GetMapping(value = {"/count-all-following/{userId}"})
    public Long countAllFollowingByUserId(@PathVariable("userId") Long userId) throws Exception {
        return followUsersService.countAllFollowingByUserId(userId);
    }

    // http://localhost:8085/api/follow-users/get-all-following/1
    @GetMapping(value = {"/get-all-following/{userId}"})
    public List<String> getAllFollowingByUserId(@PathVariable("userId") Long userId) throws Exception {
        return followUsersService.getAllFollowingByUserId(userId);
    }

    // http://localhost:8085/api/follow-users/count-all-follower/1
    @GetMapping(value = {"/count-all-follower/{userId}"})
    public Long countAllFollowerByUserId(@PathVariable("userId") Long userId) throws Exception {
        return followUsersService.countAllFollowerByUserId(userId);
    }

    // http://localhost:8085/api/follow-users/get-all-follower/1
    @GetMapping(value = {"/get-all-follower/{userId}"})
    public List<String> getAllFollowerByUserId(@PathVariable("userId") Long userId) throws Exception {
        return followUsersService.getAllFollowerByUserId(userId);
    }
}
