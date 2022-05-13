package com.google.demoinstagram.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "POSTS_COMMENTS")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = PostsComments.class)
public class PostsComments {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "CommentCannotBeNull")
    @Column(name = "TEXT", nullable = false)
    private String text;

    @NotNull
    @Column(name = "COUNT_LIKE_COMMENT")
    private Long countLikeComment = 0L;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users usersId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "POSTS_ID")
    private Posts postsId;

    @OneToMany(mappedBy = "postsCommentsId", cascade = CascadeType.ALL)
    private List<LikeComments> likeComments;

    @Column(name = "CREATE_DATE", updatable = false)
    @CreationTimestamp
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @UpdateTimestamp
    private Date updateDate;
}
