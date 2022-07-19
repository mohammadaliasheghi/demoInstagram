package com.google.demoinstagram.model;

import lombok.Data;

import java.util.Date;

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
}
