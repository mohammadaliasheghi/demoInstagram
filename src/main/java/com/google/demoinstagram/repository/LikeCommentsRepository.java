package com.google.demoinstagram.repository;

import com.google.demoinstagram.entity.LikeComments;
import com.google.demoinstagram.entity.Posts;
import com.google.demoinstagram.entity.PostsComments;
import com.google.demoinstagram.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeCommentsRepository extends JpaRepository<LikeComments, Long> {

    //improve performance(using ids)
    boolean existsLikeCommentsByPostsCommentsIdAndUsersIdAndPostsId(PostsComments postsComments, Users users, Posts posts);

    LikeComments getLikeCommentsByPostsCommentsId_IdAndUsersId_IdAndPostsId_Id(Long postsCommentsId, Long usersId, Long postsId);

    Long countAllByPostsCommentsId_IdAndPostsId_Id(Long postsCommentsId, Long postsId);
}
