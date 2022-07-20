package com.google.demoinstagram.service;

import com.google.demoinstagram.model.UsersModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface UsersService {

    @Transactional
    UsersModel saveUser(UsersModel usersModel) throws Exception;

    Page<UsersModel> getAllUser(Pageable pageable);

    UsersModel getUserById(Long id);

    @Transactional
    UsersModel updateUser(UsersModel updateModel, Long id) throws Exception;

    void deleteUser(Long id);
}
