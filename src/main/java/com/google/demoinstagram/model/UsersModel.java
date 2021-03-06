package com.google.demoinstagram.model;

import com.google.demoinstagram.entity.*;
import com.google.demoinstagram.enums.DataStateEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UsersModel {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String number;
    private String cover;
    private Date createDate;
    private Date updateDate;
    private Integer dataState = DataStateEnum.ZERO.getValue();

    private List<Posts> posts;
    private List<LikePosts> likePosts;
    private List<PostsComments> postsComments;
    private List<LikeComments> likeComments;
    private List<SavedPosts> savedPosts;
    private List<FollowUsers> followers;
    private List<FollowUsers> followings;
    private List<PrivateMessage> sendUsers;
    private List<PrivateMessage> receiveUser;
}
