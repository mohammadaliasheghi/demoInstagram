package com.google.demoinstagram.mapper.impl;

import com.google.demoinstagram.entity.FollowUsers;
import com.google.demoinstagram.mapper.FollowUsersMapper;
import com.google.demoinstagram.model.FollowUsersModel;
import org.mapstruct.Mapper;

@Mapper
public class FollowUsersMapperImpl implements FollowUsersMapper {
    @Override
    public FollowUsers convertToEntity(FollowUsersModel model) {
        return null;
    }

    @Override
    public FollowUsersModel convertToModel(FollowUsers entity) {
        return null;
    }
}
