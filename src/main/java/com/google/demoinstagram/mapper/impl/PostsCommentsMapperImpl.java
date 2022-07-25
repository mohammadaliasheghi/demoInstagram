package com.google.demoinstagram.mapper.impl;

import com.google.demoinstagram.entity.PostsComments;
import com.google.demoinstagram.mapper.PostsCommentsMapper;
import com.google.demoinstagram.mapper.PostsMapper;
import com.google.demoinstagram.mapper.UsersMapper;
import com.google.demoinstagram.model.PostsCommentsModel;
import org.mapstruct.Mapper;

@Mapper
public class PostsCommentsMapperImpl implements PostsCommentsMapper {

    public static PostsMapper postsMapper = new PostsMapperImpl();

    public static UsersMapper usersMapper = new UsersMapperImpl();

    @Override
    public PostsComments convertToEntity(PostsCommentsModel model) {
        PostsComments entity = new PostsComments();
        entity.setCountLikeComment(model.getCountLikeComment());
        entity.setText(model.getText());
        entity.setDataState(model.getDataState());
        entity.setPosts(postsMapper.convertToEntity(model.getPostsModel()));
        entity.setUsers(usersMapper.convertToEntity(model.getUsersModel()));
        return null;
    }

    @Override
    public PostsCommentsModel convertToModel(PostsComments entity) {
        PostsCommentsModel model = new PostsCommentsModel();
        model.setCountLikeComment(entity.getCountLikeComment());
        model.setId(entity.getId());
        model.setDataState(entity.getDataState());
        model.setText(entity.getText());
        model.setCreateDate(entity.getCreateDate());
        model.setUpdateDate(entity.getUpdateDate());
        model.setUsersModel(usersMapper.convertToModel(entity.getUsers()));
        model.setPostsModel(postsMapper.convertToModel(entity.getPosts()));
        model.setUsersId(entity.getUsers().getId());
        model.setPostsId(entity.getPosts().getId());
        return model;
    }
}
