package com.google.demoinstagram.excption.service;

import com.google.demoinstagram.entity.HashTag;
import com.google.demoinstagram.entity.Posts;
import com.google.demoinstagram.service.HashTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostsValidator {

    private HashTagService hashTagService;

    @Autowired
    public void setHashTagService(HashTagService hashTagService) {
        this.hashTagService = hashTagService;
    }

    public void validate(Posts posts) throws Exception {
        if (posts != null)
            validateHashTagId(posts);
    }

    private void validateHashTagId(Posts posts) throws Exception {
        List<HashTag> hashTag = hashTagService.listInfo();
        if (posts.getHashTags() != null && posts.getHashTags().get(0) != null && posts.getHashTags().get(0).getId() != null) {
            for (HashTag h : hashTag) {
                if (posts.getHashTags().get(0).getId().compareTo(h.getId()) == 0)
                    return;
            }
            throw new Exception("HashTagIdNotFound");
        }
    }
}
