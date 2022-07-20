package com.google.demoinstagram.service;

import com.google.demoinstagram.model.UsersModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

public interface UsersService {

    @Transactional
    UsersModel saveUser(UsersModel usersModel) throws Exception;

    Page<UsersModel> getAllUser(UsersModel usersModel, Pageable pageable) throws SQLException;

    UsersModel getUserById(Long id);

    @Transactional
    UsersModel updateUser(UsersModel updateModel, Long id) throws Exception;

    void deleteUser(Long id);
}
