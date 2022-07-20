package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.Users;
import com.google.demoinstagram.excption.ResourceNotFoundException;
import com.google.demoinstagram.excption.service.UsersValidator;
import com.google.demoinstagram.mapper.UsersMapper;
import com.google.demoinstagram.mapper.impl.UsersMapperImpl;
import com.google.demoinstagram.model.UsersModel;
import com.google.demoinstagram.repository.UsersRepository;
import com.google.demoinstagram.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    public static UsersMapper usersMapper = new UsersMapperImpl();
    private final UsersRepository usersRepository;
    private final UsersValidator usersValidator;

    @Transactional
    @Override
    public UsersModel saveUser(UsersModel usersModel) throws Exception {
        usersValidator.validate(usersModel);
        return usersMapper.convertToModel(usersRepository.save(usersMapper.convertToEntity(usersModel)));
    }

    @Override
    public Page<UsersModel> getAllUser(Pageable pageable) {
        Page<Users> usersPage = usersRepository.findAll(pageable);
        List<UsersModel> usersModels = usersMapper.convertToModels(usersPage.getContent());
        return new PageImpl<>(usersModels, pageable, usersModels.size());
    }

    @Override
    public UsersModel getUserById(Long id) {
        return usersMapper.convertToModel(usersRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Users", "id", id)));
    }

    @Transactional
    @Override
    public UsersModel updateUser(UsersModel updateModel, Long id) throws Exception {
        usersValidator.validate(updateModel);
        Users existingUser = usersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Users", "id", id));
        existingUser.setUsername(updateModel.getUsername());
        existingUser.setPassword(updateModel.getPassword());
        existingUser.setEmail(updateModel.getEmail());
        existingUser.setNumber(updateModel.getNumber());
        existingUser.setCover(updateModel.getCover());
        Users updatedModel = usersRepository.save(existingUser);
        return usersMapper.convertToModel(updatedModel);
    }

    @Override
    public void deleteUser(Long id) {
        // check whether a User exist in a DB or not
        usersRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Users", "id", id));
        usersRepository.deleteById(id);
    }
}
