package com.google.demoinstagram.excption.service;

import com.google.demoinstagram.entity.Users;
import com.google.demoinstagram.model.UsersModel;
import com.google.demoinstagram.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsersValidator {

    private final UsersRepository usersRepository;

    public void validate(UsersModel usersModel) throws Exception {

        if (usersModel != null) {
            checkPhoneNumberAndUsersDuplicateAndPassword(usersModel);
        }
    }

    public void checkPhoneNumberAndUsersDuplicateAndPassword(UsersModel usersModel) throws Exception {
        if (usersModel.getNumber().length() != 11)
            throw new Exception("phoneNumberLengthInCorrect");
        Optional<Users> usersList = usersRepository.findByUsername(usersModel.getUsername());
        if (usersList.isPresent())
            throw new Exception("UsernameDuplicate");
        if (usersModel.getPassword().length() < 8)
            throw new Exception("PasswordLengthMustBeMore8Char");
    }
}
