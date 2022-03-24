package com.google.demoinstagram.repository;

import com.google.demoinstagram.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("select p from Posts p " +
            "where p.usersId.id in " +
            "(select fu.following.id from FollowUsers fu where fu.follower.id = :id)")
    List<Posts> getAllFollowingPostsByUserId(@Param("id") Long id);

    @Query("select p from Posts p join p.hashTags h where upper(h.text) = upper(:hashTagText)")
    List<Posts> getAllPostsByHashTag(@Param("hashTagText") String hashTagText);
}
