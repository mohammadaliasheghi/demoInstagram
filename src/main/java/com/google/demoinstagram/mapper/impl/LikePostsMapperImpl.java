package com.google.demoinstagram.mapper.impl;

import com.google.demoinstagram.entity.LikePosts;
import com.google.demoinstagram.mapper.LikePostsMapper;
import com.google.demoinstagram.mapper.PostsMapper;
import com.google.demoinstagram.mapper.UsersMapper;
import com.google.demoinstagram.model.LikePostsModel;
import org.mapstruct.Mapper;

@Mapper
public class LikePostsMapperImpl implements LikePostsMapper {

    public static UsersMapper usersMapper = new UsersMapperImpl();

    public static PostsMapper postsMapper = new PostsMapperImpl();

    @Override
    public LikePosts convertToEntity(LikePostsModel model) {
        LikePosts entity = new LikePosts();
        entity.setLiked(model.getLiked());
        entity.setPosts(postsMapper.convertToEntity(model.getPostsModel()));
        entity.setUsers(usersMapper.convertToEntity(model.getUsersModel()));
        return entity;
    }

    @Override
    public LikePostsModel convertToModel(LikePosts entity) {
        LikePostsModel model = new LikePostsModel();
        model.setLiked(entity.getLiked());
        model.setId(entity.getId());
        model.setCreateDate(entity.getCreateDate());
        model.setPostsModel(postsMapper.convertToModel(entity.getPosts()));
        model.setUsersModel(usersMapper.convertToModel(entity.getUsers()));
        model.setPostsId(entity.getPosts().getId());
        model.setUsersId(entity.getUsers().getId());
        return model;
    }
}
