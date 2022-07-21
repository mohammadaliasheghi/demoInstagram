package com.google.demoinstagram.repository;

import com.google.demoinstagram.entity.SavedPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedPostsRepository extends JpaRepository<SavedPosts, Long> {

    boolean existsSavedPostsByUsers_IdAndPosts_Id(Long usersId, Long postsId);

    SavedPosts getSavedPostsByUsers_IdAndPosts_Id(Long usersId, Long postsId);

    @Query("select count(sp.id) from SavedPosts sp")
    Long countAllSavedPosts();

    Long countByUsers_Id(Long usersId);

    List<SavedPosts> getAllByUsers_Id(Long usersId);
}
