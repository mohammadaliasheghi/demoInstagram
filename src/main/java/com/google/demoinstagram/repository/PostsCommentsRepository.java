package com.google.demoinstagram.repository;

import com.google.demoinstagram.entity.PostsComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsCommentsRepository extends JpaRepository<PostsComments, Long> {
}
