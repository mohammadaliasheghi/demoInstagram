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

@Entity
@Data
@Table(name = "LIKE_POSTS")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = LikePosts.class)
public class LikePosts {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "LIKED", nullable = false)
    private Boolean liked = Boolean.FALSE;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users usersId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "POSTS_ID")
    private Posts postsId;

    @Column(name = "CREATE_DATE", updatable = false)
    @CreationTimestamp
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @UpdateTimestamp
    private Date updateDate;
}
