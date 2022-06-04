package com.google.demoinstagram.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
}
