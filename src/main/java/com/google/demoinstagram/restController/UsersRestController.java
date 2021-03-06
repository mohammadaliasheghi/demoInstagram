package com.google.demoinstagram.restController;

import com.google.demoinstagram.model.UsersModel;
import com.google.demoinstagram.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/api/users")
public class UsersRestController {

    private UsersService usersService;

    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    // http://localhost:8085/demoInstagram/api/users/register
    @PostMapping(value = "/register")
    public ResponseEntity<UsersModel> saveUser(@RequestBody UsersModel usersModel) throws Exception {
        return new ResponseEntity<>(usersService.saveUser(usersModel), HttpStatus.CREATED);
    }

    // http://localhost:8085/demoInstagram/api/users/
    @GetMapping(value = {"/", ""})
    public Page<UsersModel> getAllUser(@RequestBody UsersModel usersModel, @PageableDefault(size = 3) Pageable pageable) throws SQLException {
        return usersService.getAllUser(usersModel, pageable);
    }

    // http://localhost:8085/demoInstagram/api/users/getUserById/1
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UsersModel> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(usersService.getUserById(id), HttpStatus.OK);
    }

    // http://localhost:8085/demoInstagram/api/users/update/1
    @PutMapping("/update/{id}")
    public ResponseEntity<UsersModel> updateUser(@PathVariable("id") Long id
            , @RequestBody UsersModel usersModel) throws Exception {
        return new ResponseEntity<>(usersService.updateUser(usersModel, id), HttpStatus.OK);
    }

    // http://localhost:8085/demoInstagram/api/users/delete/1
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        // delete user from DB
        usersService.deleteUser(id);
        return new ResponseEntity<>("User Deleted Successfully!", HttpStatus.OK);
    }
}
