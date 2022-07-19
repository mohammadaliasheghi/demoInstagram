package com.google.demoinstagram.service;

import com.google.demoinstagram.model.UsersModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsersService {

    @Transactional
    UsersModel saveUser(UsersModel usersModel) throws Exception;

    List<UsersModel> getAllUser();

    UsersModel getUserById(Long id);

    @Transactional
    UsersModel updateUser(UsersModel updateModel, Long id) throws Exception;

    void deleteUser(Long id);

    UsersModel findByUsername(String name);
}
