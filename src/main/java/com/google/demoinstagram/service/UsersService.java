package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.Users;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    @Transactional
    Users saveUser(Users users) throws Exception;

    List<Users> getAllUser();

    Users getUserById(long id);

    @Transactional
    Users updateUser(Users users, long id) throws Exception;

    void deleteUser(long id);

    Optional<Users> findByUsername(String name);

    //TestClass
    Boolean existUsersById(long id);
}
