package com.google.demoinstagram.repository;

import com.google.demoinstagram.entity.LikePosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikePostsRepository extends JpaRepository<LikePosts, Long> {
}
