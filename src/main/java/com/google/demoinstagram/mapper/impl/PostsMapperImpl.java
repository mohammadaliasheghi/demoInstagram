package com.google.demoinstagram.mapper.impl;

import com.google.demoinstagram.entity.Posts;
import com.google.demoinstagram.mapper.PostsMapper;
import com.google.demoinstagram.mapper.UsersMapper;
import com.google.demoinstagram.model.PostsModel;
import org.mapstruct.Mapper;

@Mapper
public class PostsMapperImpl implements PostsMapper {

    public static UsersMapper usersMapper = new UsersMapperImpl();

    @Override
    public Posts convertToEntity(PostsModel model) {
        Posts entity = new Posts();
        entity.setDataState(model.getDataState());
        entity.setCountComment(model.getCountComment());
        entity.setCountLike(model.getCountLike());
        entity.setCover(model.getCover());
        entity.setDescription(model.getDescription());
        entity.setIsSavedPost(model.getIsSavedPost());
        entity.setTitle(model.getTitle());
        entity.setUsers(usersMapper.convertToEntity(model.getUsersModel()));
        return entity;
    }

    @Override
    public PostsModel convertToModel(Posts entity) {
        PostsModel model = new PostsModel();
        model.setCreateDate(entity.getCreateDate());
        model.setDataState(entity.getDataState());
        model.setCountComment(entity.getCountComment());
        model.setCountLike(entity.getCountLike());
        model.setIsSavedPost(entity.getIsSavedPost());
        model.setTitle(entity.getTitle());
        model.setUpdateDate(entity.getUpdateDate());
        model.setUsersModel(usersMapper.convertToModel(entity.getUsers()));
        model.setUsersId(entity.getUsers().getId());
        model.setDescription(entity.getDescription());
        return model;
    }
}
