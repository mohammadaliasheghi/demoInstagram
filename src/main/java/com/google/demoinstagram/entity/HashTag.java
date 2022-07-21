package com.google.demoinstagram.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.demoinstagram.enums.DataStateEnum;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "HASH-TAG")
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = HashTag.class)
public class HashTag {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(name = "TEXT", nullable = false)
    private String text;

    @ManyToOne
    private Posts posts;

    @NotNull
    @Column(name = "DATA_STATE", length = 3, nullable = false)
    private Integer dataState = DataStateEnum.ZERO.getValue();
}
