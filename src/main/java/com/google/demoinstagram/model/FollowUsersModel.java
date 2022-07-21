package com.google.demoinstagram.model;

import com.google.demoinstagram.enums.DataStateEnum;
import lombok.Data;

import java.util.Date;

@Data
public class FollowUsersModel {

    private Long id;
    private UsersModel follower;
    private UsersModel following;
    private Date createDate;
    private Integer dataState = DataStateEnum.ZERO.getValue();

    private Long followerId;
    private Long followingId;
}
