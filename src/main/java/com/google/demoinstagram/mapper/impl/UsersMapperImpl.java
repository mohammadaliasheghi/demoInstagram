package com.google.demoinstagram.mapper.impl;

import com.google.demoinstagram.entity.Users;
import com.google.demoinstagram.mapper.UsersMapper;
import com.google.demoinstagram.model.UsersModel;
import org.mapstruct.Mapper;

@Mapper
public class UsersMapperImpl implements UsersMapper {
    @Override
    public Users convertToEntity(UsersModel usersModel) {
        Users users = new Users();
        users.setUsername(usersModel.getUsername());
        users.setEmail(usersModel.getEmail());
        users.setNumber(usersModel.getNumber());
        users.setPassword(usersModel.getPassword());
        users.setCover(usersModel.getCover());
        return users;
    }

    @Override
    public UsersModel convertToModel(Users users) {
        UsersModel usersModel = new UsersModel();
        usersModel.setId(users.getId());
        usersModel.setUsername(users.getUsername());
        usersModel.setPassword(users.getPassword());
        usersModel.setEmail(users.getEmail());
        usersModel.setNumber(users.getNumber());
        usersModel.setCreateDate(users.getCreateDate());
        usersModel.setUpdateDate(users.getUpdateDate());
        return usersModel;
    }
}
