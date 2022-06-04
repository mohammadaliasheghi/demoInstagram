package com.google.demoinstagram.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "POSTS")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Posts.class)
public class Posts {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "TITLE", length = 50)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COVER")
    private String cover;

    @NotNull
    @Column(name = "COUNT_LIKE")
    private Long countLike = 0L;

    @NotNull
    @Column(name = "COUNT_COMMENT")
    private Long countComment = 0L;

    @Column(name = "CREATE_DATE", updatable = false)
    @CreationTimestamp
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @UpdateTimestamp
    private Date updateDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users usersId;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private List<HashTag> hashTags;

    @OneToMany(mappedBy = "postsId", cascade = CascadeType.ALL)
    private List<LikePosts> likePosts;

    @OneToMany(mappedBy = "postsId", cascade = CascadeType.ALL)
    private List<PostsComments> postsComments;

    @OneToMany(mappedBy = "postsId", cascade = CascadeType.ALL)
    private List<LikeComments> likeComments;

    @OneToMany(mappedBy = "postsId", cascade = CascadeType.ALL)
    private List<SavedPosts> savedPosts;
}
