package com.google.demoinstagram.model;

import com.google.demoinstagram.enums.DataStateEnum;
import lombok.Data;

import java.util.Date;

@Data
public class PrivateMessageModel {

    private Long id;
    private String text;
    private UsersModel sendUser;
    private UsersModel receiveUser;
    private Date createDate;
    private Date updateDate;
    private Integer dataState = DataStateEnum.ZERO.getValue();

    private Long sendUserId;
    private Long receiveUserId;
}
