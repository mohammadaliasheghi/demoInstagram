package com.google.demoinstagram.mapper;

import com.google.demoinstagram.baseData.BaseMapperConfig;
import com.google.demoinstagram.entity.PrivateMessage;
import com.google.demoinstagram.model.PrivateMessageModel;
import org.mapstruct.Mapper;

@Mapper
public interface PrivateMessageMapper extends BaseMapperConfig<PrivateMessage, PrivateMessageModel> {

    PrivateMessage convertToEntity(PrivateMessageModel model);

    PrivateMessageModel convertToModel(PrivateMessage entity);
}
