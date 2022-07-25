package com.google.demoinstagram.mapper;

import com.google.demoinstagram.baseData.BaseMapperConfig;
import com.google.demoinstagram.entity.SavedPosts;
import com.google.demoinstagram.model.SavedPostsModel;
import org.mapstruct.Mapper;

@Mapper
public interface SavedPostsMapper extends BaseMapperConfig<SavedPosts, SavedPostsModel> {

    SavedPosts convertToEntity(SavedPostsModel model);

    SavedPostsModel convertToModel(SavedPosts entity);
}
