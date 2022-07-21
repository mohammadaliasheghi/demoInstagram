package com.google.demoinstagram.repository;

import com.google.demoinstagram.entity.LikePosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikePostsRepository extends JpaRepository<LikePosts, Long> {

    boolean existsLikePostsByUsers_IdAndPosts_Id(Long usersId, Long postsId);

    LikePosts getLikePostsByUsers_IdAndPosts_Id(Long usersId, Long postsId);

    List<LikePosts> getAllByPosts_Id(Long postsId);

    Long countAllByPosts_Id(Long postsId);

    @Query("select lp.users.username from LikePosts lp " +
            "left join Posts p on p.id = lp.posts.id " +
            "where p.id = :id")
    List<String> findAllUsernameLikedPostByPostId(@Param("id") Long id);
}
