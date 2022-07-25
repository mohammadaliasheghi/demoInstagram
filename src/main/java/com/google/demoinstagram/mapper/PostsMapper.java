package com.google.demoinstagram.mapper;

import com.google.demoinstagram.baseData.BaseMapperConfig;
import com.google.demoinstagram.entity.Posts;
import com.google.demoinstagram.model.PostsModel;
import org.mapstruct.Mapper;

@Mapper
public interface PostsMapper extends BaseMapperConfig<Posts, PostsModel> {

    Posts convertToEntity(PostsModel model);

    PostsModel convertToModel(Posts entity);
}
