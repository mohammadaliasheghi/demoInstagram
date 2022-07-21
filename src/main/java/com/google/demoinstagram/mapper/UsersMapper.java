package com.google.demoinstagram.mapper;

import com.google.demoinstagram.baseData.BaseMapperConfig;
import com.google.demoinstagram.entity.Users;
import com.google.demoinstagram.model.UsersModel;
import org.mapstruct.Mapper;

@Mapper
public interface UsersMapper extends BaseMapperConfig<Users, UsersModel> {

    @Override
    Users convertToEntity(UsersModel usersModel);

    @Override
    UsersModel convertToModel(Users users);
}
