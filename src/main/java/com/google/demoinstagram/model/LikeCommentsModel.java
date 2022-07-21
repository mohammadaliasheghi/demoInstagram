package com.google.demoinstagram.model;

import lombok.Data;

import java.util.Date;

@Data
public class LikeCommentsModel {

    private Long id;
    private Boolean liked = Boolean.FALSE;
    private UsersModel usersModel;
    private PostsModel postsModel;
    private PostsCommentsModel postsCommentsModel;
    private Date createDate;

    private Long postsCommentsId;
    private Long usersId;
    private Long postsId;
}
