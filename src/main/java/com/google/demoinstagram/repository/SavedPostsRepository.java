package com.google.demoinstagram.repository;

import com.google.demoinstagram.entity.SavedPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedPostsRepository extends JpaRepository<SavedPosts, Long> {

    boolean existsSavedPostsByUsersId_IdAndPostsId_Id(Long usersId, Long postsId);

    SavedPosts getSavedPostsByUsersId_IdAndPostsId_Id(Long usersId, Long postsId);

    @Query("select count(sp.id) from SavedPosts sp")
    Long countAllSavedPosts();

    Long countByUsersId_Id(Long usersId);

    List<SavedPosts> getAllByUsersId_Id(Long usersId);
}
