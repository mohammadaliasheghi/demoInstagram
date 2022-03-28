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

@Entity
@Data
@Table(name = "PRIVATE_MESSAGE")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = PrivateMessage.class)
public class PrivateMessage {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "TextCannotBeNull")
    @Column(name = "TEXT", nullable = false)
    private String text;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "SEND_USER")
    private Users sendUser;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "RECEIVE_USER")
    private Users receiveUser;

    @Column(name = "CREATE_DATE", updatable = false)
    @CreationTimestamp
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @UpdateTimestamp
    private Date updateDate;
}
