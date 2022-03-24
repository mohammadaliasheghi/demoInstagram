package com.google.demoinstagram.repository;

import com.google.demoinstagram.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, Long> {

    boolean existsHashTagByText(String hashTag);

    HashTag getHashTagByText(String hashTag);
}
