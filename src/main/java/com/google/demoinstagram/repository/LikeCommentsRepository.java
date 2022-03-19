package com.google.demoinstagram.repository;

import com.google.demoinstagram.entity.LikeComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeCommentsRepository extends JpaRepository<LikeComments, Long> {

    boolean existsLikeCommentsByPostsCommentsId_IdAndUsersId_IdAndPostsId_Id(Long postsComments, Long users, Long posts);

    LikeComments getLikeCommentsByPostsCommentsId_IdAndUsersId_IdAndPostsId_Id(Long postsCommentsId, Long usersId, Long postsId);

    Long countAllByPostsCommentsId_IdAndPostsId_Id(Long postsCommentsId, Long postsId);

    @Query("select lc.usersId.username from LikeComments lc " +
            "left join PostsComments pc on pc.id = lc.postsCommentsId.id " +
            "where pc.id = :id")
    List<String> findAllUsernameLikedCommentsByPostCommentId(@Param("id") Long id);
}
