package com.google.demoinstagram.repository;

import com.google.demoinstagram.entity.LikePosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikePostsRepository extends JpaRepository<LikePosts, Long> {

    boolean existsLikePostsByUsersId_IdAndPostsId_Id(Long usersId, Long postsId);

    LikePosts getLikePostsByUsersId_IdAndPostsId_Id(Long usersId, Long postsId);

    List<LikePosts> getAllByPostsId_Id(Long postsId);

    Long countAllByPostsId_Id(Long postsId);
}
