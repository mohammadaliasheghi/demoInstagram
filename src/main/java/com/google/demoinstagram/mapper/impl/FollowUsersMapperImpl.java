package com.google.demoinstagram.mapper.impl;

import com.google.demoinstagram.entity.FollowUsers;
import com.google.demoinstagram.mapper.FollowUsersMapper;
import com.google.demoinstagram.mapper.UsersMapper;
import com.google.demoinstagram.model.FollowUsersModel;
import org.mapstruct.Mapper;

@Mapper
public class FollowUsersMapperImpl implements FollowUsersMapper {

    public static UsersMapper usersMapper = new UsersMapperImpl();

    @Override
    public FollowUsers convertToEntity(FollowUsersModel model) {
        FollowUsers entity = new FollowUsers();
        entity.setId(model.getId());
        entity.setFollower(usersMapper.convertToEntity(model.getFollower()));
        entity.setFollowing(usersMapper.convertToEntity(model.getFollowing()));
        entity.setCreateDate(model.getCreateDate());
        entity.setDataState(model.getDataState());
        return entity;
    }

    @Override
    public FollowUsersModel convertToModel(FollowUsers entity) {
        FollowUsersModel model = new FollowUsersModel();
        model.setId(entity.getId());
        model.setFollower(usersMapper.convertToModel(entity.getFollower()));
        model.setFollowerId(entity.getFollower().getId());
        model.setFollowing(usersMapper.convertToModel(entity.getFollowing()));
        model.setFollowingId(entity.getFollowing().getId());
        model.setCreateDate(entity.getCreateDate());
        model.setDataState(entity.getDataState());
        return model;
    }
}
