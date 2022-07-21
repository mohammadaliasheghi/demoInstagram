package com.google.demoinstagram.model;

import com.google.demoinstagram.entity.LikeComments;
import com.google.demoinstagram.enums.DataStateEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostsCommentsModel {

    private Long id;
    private String text;
    private Long countLikeComment = 0L;
    private UsersModel usersModel;
    private PostsModel postsModel;
    private Date createDate;
    private Date updateDate;
    private Integer dataState = DataStateEnum.ZERO.getValue();
    private List<LikeComments> likeComments;

    private Long usersId;
    private Long postsId;
}
