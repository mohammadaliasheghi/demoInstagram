package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.HashTag;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HashTagService {

    @Transactional
    HashTag add(HashTag hashTag) throws Exception;

    List<HashTag> listInfo();

    HashTag get(Long id);

    @Transactional
    HashTag update(HashTag hashTag, Long id) throws Exception;

    void delete(Long id);
}
