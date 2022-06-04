package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.HashTag;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HashTagService {

    @Transactional
    void createHashTagList(List<HashTag> hashTags) throws Exception;

    List<HashTag> hashTagListInfo();
}
