package com.google.demoinstagram.model;

import com.google.demoinstagram.entity.*;
import com.google.demoinstagram.enums.DataStateEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostsModel {

    private Long id;
    private String title;
    private String description;
    private String cover;
    private Long countLike = 0L;
    private Long countComment = 0L;
    private Boolean isSavedPost = false;
    private Date createDate;
    private Date updateDate;
    private UsersModel usersModel;
    private Integer dataState = DataStateEnum.ZERO.getValue();
    private List<HashTag> hashTags;
    private List<LikePosts> likePosts;
    private List<PostsComments> postsComments;
    private List<LikeComments> likeComments;
    private List<SavedPosts> savedPosts;

    private Long usersId;
}
