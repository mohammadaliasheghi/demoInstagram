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
    @Query("select u from Users u where upper(u.username) = upper(:username)")
    Optional<Users> findByUsername(@Param("username") String username);
}
