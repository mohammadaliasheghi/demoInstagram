package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.Users;
import com.google.demoinstagram.excption.ResourceNotFoundException;
import com.google.demoinstagram.excption.service.UsersValidator;
import com.google.demoinstagram.repository.UsersRepository;
import com.google.demoinstagram.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService { 

    private final UsersRepository usersRepository;
    private UsersValidator usersValidator;

    @Autowired
    public void setUsersValidator(UsersValidator usersValidator) {
        this.usersValidator = usersValidator;
    }

    @Transactional
    @Override
    public Users saveUser(Users users) throws Exception {
        usersValidator.validate(users);
        return usersRepository.save(users);
    }

    @Override
    public List<Users> getAllUser() {
        return usersRepository.findAll();
    }

    @Override
    public Users getUserById(long id) {
        return usersRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Users", "id", id));
    }

    @Transactional
    @Override
    public Users updateUser(Users users, long id) throws Exception {
        usersValidator.validate(users);
        // we need to check whether User with given id is Existed in DB or not
        Users existingUser = usersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Users", "id", id));

        existingUser.setUsername(users.getUsername());
        existingUser.setPassword(users.getPassword());
        existingUser.setEmail(users.getEmail());
        existingUser.setNumber(users.getNumber());
        existingUser.setCover(users.getCover());
        // save existing User to DB
        usersRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUser(long id) {
        // check whether a User exist in a DB or not
        usersRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Users", "id", id));
        usersRepository.deleteById(id);
    }

    @Override
    public Optional<Users> findByUsername(String name) {
        return usersRepository.findByUsername(name);
    }
}
