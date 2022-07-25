package com.google.demoinstagram.mapper;

import com.google.demoinstagram.baseData.BaseMapperConfig;
import com.google.demoinstagram.entity.LikePosts;
import com.google.demoinstagram.model.LikePostsModel;
import org.mapstruct.Mapper;

@Mapper
public interface LikePostsMapper extends BaseMapperConfig<LikePosts, LikePostsModel> {

    LikePosts convertToEntity(LikePostsModel model);

    LikePostsModel convertToModel(LikePosts entity);
}
