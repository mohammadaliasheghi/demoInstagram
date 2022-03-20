package com.google.demoinstagram.repository;

import com.google.demoinstagram.entity.FollowUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowUsersRepository extends JpaRepository<FollowUsers, Long> {

    boolean existsFollowUsersByFollower_IdAndFollowing_Id(Long followerId, Long followingId);

    FollowUsers getFollowUsersByFollower_IdAndFollowing_Id(Long followerId, Long followingId);

    @Query("select count(fu.following.id) from FollowUsers fu " +
            "left join Users u on fu.follower.id = u.id " +
            "where u.id = :id")
    Long countAllFollowingByUserId(@Param("id") Long id);

    @Query("select fu.following.username from FollowUsers fu " +
            "left join Users u on fu.follower.id = u.id " +
            "where u.id = :id")
    List<String> getAllFollowingByUserId(@Param("id") Long id);

    @Query("select count(fu.follower.id) from FollowUsers fu " +
            "left join Users u on fu.following.id = u.id " +
            "where u.id = :id")
    Long countAllFollowerByUserId(@Param("id") Long id);

    @Query("select fu.follower.username from FollowUsers fu " +
            "left join Users u on fu.following.id = u.id " +
            "where u.id = :id")
    List<String> getAllFollowerByUserId(@Param("id") Long id);
}
