package com.google.demoinstagram.repository;

import com.google.demoinstagram.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    //used in validator and service
    @Query("SELECT u FROM Users u WHERE UPPER(u.username) LIKE UPPER(:username)")
    Optional<Users> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM Users u WHERE u.id = :id AND u.dataState = 0 ")
    Optional<Users> find(@Param("id") Long id);
}
