package com.google.demoinstagram.mapper;

import com.google.demoinstagram.baseData.BaseMapperConfig;
import com.google.demoinstagram.entity.LikeComments;
import com.google.demoinstagram.model.LikeCommentsModel;
import org.mapstruct.Mapper;

@Mapper
public interface LikeCommentsMapper extends BaseMapperConfig<LikeComments, LikeCommentsModel> {

    LikeComments convertToEntity(LikeCommentsModel model);

    LikeCommentsModel convertToModel(LikeComments entity);
}
