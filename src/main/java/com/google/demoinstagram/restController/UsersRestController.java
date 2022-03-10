package com.google.demoinstagram.restController;

import com.google.demoinstagram.entity.Users;
import com.google.demoinstagram.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/users")
public class UsersRestController {

    private UsersService usersService;

    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    // http://localhost:8085/api/users/register
    @PostMapping(value = "/register")
    public ResponseEntity<Users> saveUser(@RequestBody Users users) throws Exception {
        return new ResponseEntity<>(usersService.saveUser(users), HttpStatus.CREATED);
    }

    // http://localhost:8085/api/users/
    @GetMapping(value = {"/", ""})
    public List<Users> getAllUser() {
        return usersService.getAllUser();
    }

    // http://localhost:8085/api/users/getUserById/1
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") long userId) {
        return new ResponseEntity<>(usersService.getUserById(userId), HttpStatus.OK);
    }

    //http://localhost:8085/api/users/getUserByUsername/username
    @GetMapping("/getUserByUsername/{username}")
    public Optional<Users> getUserByUsername(@PathVariable("username") String name) {
        return usersService.findByUsername(name);
    }

    // http://localhost:8085/api/users/update/1
    @PutMapping("/update/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable("id") long id
            , @RequestBody Users users) throws Exception {
        return new ResponseEntity<>(usersService.updateUser(users, id), HttpStatus.OK);
    }

    // http://localhost:8085/api/users/delete/1
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {

        // delete user from DB
        usersService.deleteUser(id);

        return new ResponseEntity<>("UserDeletedSuccessfully!", HttpStatus.OK);
    }

}
