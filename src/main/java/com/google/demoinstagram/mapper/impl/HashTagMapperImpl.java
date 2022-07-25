package com.google.demoinstagram.mapper.impl;

import com.google.demoinstagram.entity.HashTag;
import com.google.demoinstagram.mapper.HashTagMapper;
import com.google.demoinstagram.mapper.PostsMapper;
import com.google.demoinstagram.model.HashTagModel;
import org.mapstruct.Mapper;

@Mapper
public class HashTagMapperImpl implements HashTagMapper {

    public static PostsMapper postsMapper = new PostsMapperImpl();

    @Override
    public HashTag convertToEntity(HashTagModel model) {
        HashTag entity = new HashTag();
        entity.setDataState(model.getDataState());
        entity.setText(entity.getText());
        entity.setPosts(postsMapper.convertToEntity(model.getPostsModel()));
        return entity;
    }

    @Override
    public HashTagModel convertToModel(HashTag entity) {
        HashTagModel model = new HashTagModel();
        model.setId(entity.getId());
        model.setDataState(entity.getDataState());
        model.setText(model.getText());
        model.setPostsModel(postsMapper.convertToModel(entity.getPosts()));
        model.setPostsId(entity.getPosts().getId());
        return null;
    }
}
