package com.google.demoinstagram.model;

import com.google.demoinstagram.enums.DataStateEnum;
import lombok.Data;

@Data
public class HashTagModel {

    private Long id;
    private String text;
    private PostsModel postsModel;
    private Integer dataState = DataStateEnum.ZERO.getValue();

    private Long postsId;
}
