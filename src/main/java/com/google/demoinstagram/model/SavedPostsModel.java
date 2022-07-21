package com.google.demoinstagram.model;

import lombok.Data;

import java.util.Date;

@Data
public class SavedPostsModel {

    private Long id;
    private Boolean saved = Boolean.FALSE;
    private UsersModel usersModel;
    private PostsModel postsModel;
    private Date createDate;

    private Long usersId;
    private Long postsId;
}
