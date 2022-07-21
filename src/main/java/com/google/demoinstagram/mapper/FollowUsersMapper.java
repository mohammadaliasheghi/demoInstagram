package com.google.demoinstagram.mapper;

import com.google.demoinstagram.baseData.BaseMapperConfig;
import com.google.demoinstagram.entity.FollowUsers;
import com.google.demoinstagram.model.FollowUsersModel;
import org.mapstruct.Mapper;

@Mapper
public interface FollowUsersMapper extends BaseMapperConfig<FollowUsers, FollowUsersModel> {

    @Override
    FollowUsers convertToEntity(FollowUsersModel model);

    @Override
    FollowUsersModel convertToModel(FollowUsers entity);
}
