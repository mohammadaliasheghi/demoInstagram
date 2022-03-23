package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.HashTag;
import com.google.demoinstagram.excption.ResourceNotFoundException;
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
    public HashTag add(HashTag hashTag) {
        return hashTagRepository.save(hashTag);
    }

    @Override
    public List<HashTag> listInfo() {
        return hashTagRepository.findAll();
    }

    @Override
    public HashTag get(Long id) {
        return hashTagRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("HashTag", "id", id));
    }

    @Override
    public HashTag update(HashTag hashTag, Long id) {
        HashTag existHashTag = hashTagRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("HashTag", "id", id));
        existHashTag.setText(hashTag.getText());
        hashTagRepository.save(existHashTag);
        return existHashTag;
    }

    @Override
    public void delete(Long id) {
        hashTagRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("HashTag", "id", id));
        hashTagRepository.deleteById(id);
    }
}
