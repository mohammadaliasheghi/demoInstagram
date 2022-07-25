package com.google.demoinstagram.mapper.impl;

import com.google.demoinstagram.entity.SavedPosts;
import com.google.demoinstagram.mapper.PostsMapper;
import com.google.demoinstagram.mapper.SavedPostsMapper;
import com.google.demoinstagram.mapper.UsersMapper;
import com.google.demoinstagram.model.SavedPostsModel;
import org.mapstruct.Mapper;

@Mapper
public class SavedPostsMapperImpl implements SavedPostsMapper {

    public static UsersMapper usersMapper = new UsersMapperImpl();

    public static PostsMapper postsMapper = new PostsMapperImpl();

    @Override
    public SavedPosts convertToEntity(SavedPostsModel model) {
        SavedPosts entity = new SavedPosts();
        entity.setSaved(model.getSaved());
        entity.setUsers(usersMapper.convertToEntity(model.getUsersModel()));
        entity.setPosts(postsMapper.convertToEntity(model.getPostsModel()));
        return entity;
    }

    @Override
    public SavedPostsModel convertToModel(SavedPosts entity) {
        SavedPostsModel model = new SavedPostsModel();
        model.setId(entity.getId());
        model.setSaved(entity.getSaved());
        model.setCreateDate(entity.getCreateDate());
        model.setUsersModel(usersMapper.convertToModel(entity.getUsers()));
        model.setPostsModel(postsMapper.convertToModel(entity.getPosts()));
        model.setPostsId(entity.getPosts().getId());
        model.setUsersId(entity.getUsers().getId());
        return null;
    }
}
