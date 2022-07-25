package com.google.demoinstagram.mapper.impl;

import com.google.demoinstagram.entity.PrivateMessage;
import com.google.demoinstagram.mapper.PrivateMessageMapper;
import com.google.demoinstagram.mapper.UsersMapper;
import com.google.demoinstagram.model.PrivateMessageModel;
import org.mapstruct.Mapper;

@Mapper
public class PrivateMessageMapperImpl implements PrivateMessageMapper {

    public static UsersMapper usersMapper = new UsersMapperImpl();

    @Override
    public PrivateMessage convertToEntity(PrivateMessageModel model) {
        PrivateMessage entity = new PrivateMessage();
        entity.setText(model.getText());
        entity.setDataState(model.getDataState());
        entity.setReceiveUser(usersMapper.convertToEntity(model.getReceiveUser()));
        entity.setSendUser(usersMapper.convertToEntity(model.getSendUser()));
        return entity;
    }

    @Override
    public PrivateMessageModel convertToModel(PrivateMessage entity) {
        PrivateMessageModel model = new PrivateMessageModel();
        model.setId(entity.getId());
        model.setDataState(entity.getDataState());
        model.setText(entity.getText());
        model.setCreateDate(entity.getCreateDate());
        model.setUpdateDate(entity.getUpdateDate());
        model.setReceiveUser(usersMapper.convertToModel(entity.getReceiveUser()));
        model.setSendUser(usersMapper.convertToModel(entity.getSendUser()));
        model.setReceiveUserId(entity.getReceiveUser().getId());
        model.setSendUserId(entity.getSendUser().getId());
        return model;
    }
}
