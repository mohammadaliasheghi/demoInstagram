package com.google.demoinstagram.excption.service;

import com.google.demoinstagram.entity.Users;
import com.google.demoinstagram.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersValidator {

    private UsersRepository usersRepository;

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void validate(Users users) throws Exception {

        if (users != null) {
            checkPhoneNumberAndUsersDuplicateAndPassword(users);
        }
    }

    public void checkPhoneNumberAndUsersDuplicateAndPassword(Users users) throws Exception {
        if (users.getNumber().length() != 11)
            throw new Exception("phoneNumberLengthInCorrect");
        Optional<Users> usersList = usersRepository.findByUsername(users.getUsername());
        if (usersList.isPresent())
            throw new Exception("UsernameDuplicate");
        if (users.getPassword().length() < 8)
            throw new Exception("PasswordLengthMustBeMore8Char");
    }
}
