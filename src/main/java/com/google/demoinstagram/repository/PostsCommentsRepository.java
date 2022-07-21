package com.google.demoinstagram.repository;

import com.google.demoinstagram.entity.PostsComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsCommentsRepository extends JpaRepository<PostsComments, Long> {

    List<PostsComments> getAllByPosts_Id(Long postsId);

    Long countAllByPosts_Id(Long id);
}
