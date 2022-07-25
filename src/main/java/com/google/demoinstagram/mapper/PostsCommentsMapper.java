package com.google.demoinstagram.mapper;

import com.google.demoinstagram.baseData.BaseMapperConfig;
import com.google.demoinstagram.entity.PostsComments;
import com.google.demoinstagram.model.PostsCommentsModel;
import org.mapstruct.Mapper;

@Mapper
public interface PostsCommentsMapper extends BaseMapperConfig<PostsComments, PostsCommentsModel> {

    PostsComments convertToEntity(PostsCommentsModel model);

    PostsCommentsModel convertToModel(PostsComments entity);
}
