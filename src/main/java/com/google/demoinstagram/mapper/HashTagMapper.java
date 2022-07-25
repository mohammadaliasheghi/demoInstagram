package com.google.demoinstagram.mapper;

import com.google.demoinstagram.baseData.BaseMapperConfig;
import com.google.demoinstagram.entity.HashTag;
import com.google.demoinstagram.model.HashTagModel;
import org.mapstruct.Mapper;


@Mapper
public interface HashTagMapper extends BaseMapperConfig<HashTag, HashTagModel> {

    @Override
    HashTag convertToEntity(HashTagModel model);

    @Override
    HashTagModel convertToModel(HashTag entity);
}