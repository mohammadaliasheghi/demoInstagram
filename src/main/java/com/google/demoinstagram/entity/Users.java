package com.google.demoinstagram.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.demoinstagram.enums.DataStateEnum;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "USERS")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Users.class)
public class Users {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(name = "USERNAME", length = 100, unique = true, nullable = false)
    private String username;

    @NotBlank
    @Column(name = "PASSWORD", length = 100, nullable = false)
    private String password;

    @NotBlank
    @Column(name = "EMAIL", length = 100, nullable = false)
    @Email
    private String email;

    @NotBlank
    @Column(name = "NUMBER", length = 11, nullable = false)
    @NumberFormat
    private String number;

    @Column(name = "COVER")
    private String cover;

    @Column(name = "CREATE_DATE", updatable = false)
    @CreationTimestamp
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @UpdateTimestamp
    private Date updateDate;

    @NotNull
    @Column(name = "DATA_STATE", length = 3, nullable = false)
    private Integer dataState = DataStateEnum.ZERO.getValue();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Posts> posts;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<LikePosts> likePosts;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<PostsComments> postsComments;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<LikeComments> likeComments;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<SavedPosts> savedPosts;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private List<FollowUsers> followers;

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    private List<FollowUsers> followings;

    @OneToMany(mappedBy = "sendUser", cascade = CascadeType.ALL)
    private List<PrivateMessage> sendUsers;

    @OneToMany(mappedBy = "receiveUser", cascade = CascadeType.ALL)
    private List<PrivateMessage> receiveUser;
}
