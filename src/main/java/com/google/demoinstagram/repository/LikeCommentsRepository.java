package com.google.demoinstagram.repository;

import com.google.demoinstagram.entity.LikeComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeCommentsRepository extends JpaRepository<LikeComments, Long> {

    boolean existsLikeCommentsByPostsCommentsId_IdAndUsersId_IdAndPostsId_Id(Long postsComments, Long users, Long posts);

    LikeComments getLikeCommentsByPostsCommentsId_IdAndUsersId_IdAndPostsId_Id(Long postsCommentsId, Long usersId, Long postsId);

    Long countAllByPostsCommentsId_IdAndPostsId_Id(Long postsCommentsId, Long postsId);
}
