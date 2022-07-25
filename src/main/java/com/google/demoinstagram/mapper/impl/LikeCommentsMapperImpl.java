package com.google.demoinstagram.mapper.impl;

import com.google.demoinstagram.entity.LikeComments;
import com.google.demoinstagram.mapper.LikeCommentsMapper;
import com.google.demoinstagram.mapper.PostsCommentsMapper;
import com.google.demoinstagram.mapper.PostsMapper;
import com.google.demoinstagram.mapper.UsersMapper;
import com.google.demoinstagram.model.LikeCommentsModel;
import org.mapstruct.Mapper;

@Mapper
public class LikeCommentsMapperImpl implements LikeCommentsMapper {

    public static PostsMapper postsMapper = new PostsMapperImpl();
    public static UsersMapper usersMapper = new UsersMapperImpl();

    public static PostsCommentsMapper postsCommentsMapper = new PostsCommentsMapperImpl();

    @Override
    public LikeComments convertToEntity(LikeCommentsModel model) {
        LikeComments entity = new LikeComments();
        entity.setLiked(model.getLiked());
        entity.setPosts(postsMapper.convertToEntity(model.getPostsModel()));
        entity.setUsers(usersMapper.convertToEntity(model.getUsersModel()));
        entity.setPostsComments(postsCommentsMapper.convertToEntity(model.getPostsCommentsModel()));
        return entity;
    }

    @Override
    public LikeCommentsModel convertToModel(LikeComments entity) {
        LikeCommentsModel model = new LikeCommentsModel();
        model.setLiked(entity.getLiked());
        model.setCreateDate(entity.getCreateDate());
        model.setId(entity.getId());
        model.setUsersModel(usersMapper.convertToModel(entity.getUsers()));
        model.setPostsModel(postsMapper.convertToModel(entity.getPosts()));
        model.setPostsCommentsModel(postsCommentsMapper.convertToModel(entity.getPostsComments()));
        model.setUsersId(entity.getUsers().getId());
        model.setPostsId(entity.getPosts().getId());
        model.setPostsCommentsId(entity.getPostsComments().getId());
        return model;
    }
}
