package com.google.demoinstagram.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.demoinstagram.enums.DataStateEnum;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name = "FOLLOW_USERS")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = FollowUsers.class)
public class FollowUsers {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "FOLLOWER")
    private Users follower;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "FOLLOWING")
    private Users following;

    @Column(name = "CREATE_DATE", updatable = false)
    @CreationTimestamp
    private Date createDate;

    @NotNull
    @Column(name = "DATA_STATE", length = 3, nullable = false)
    private Integer dataState = DataStateEnum.ZERO.getValue();
}
