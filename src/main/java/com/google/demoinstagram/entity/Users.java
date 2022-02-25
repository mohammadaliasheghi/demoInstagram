package com.google.demoinstagram.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @OneToMany(mappedBy = "usersId")
    private List<Posts> posts;

    @OneToMany(mappedBy = "usersId")
    private List<LikePosts> likePosts;

    @OneToMany(mappedBy = "usersId")
    private List<PostsComments> postsComments;
}
