package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.HashTag;
import com.google.demoinstagram.repository.HashTagRepository;
import com.google.demoinstagram.service.HashTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HashTagServiceImpl implements HashTagService {

    private final HashTagRepository hashTagRepository;

    @Override
    public void createHashTagList(List<HashTag> hashTags) {
        hashTagRepository.saveAll(hashTags);
    }

    @Override
    public List<HashTag> hashTagListInfo() {
        return hashTagRepository.findAll();
    }
}
