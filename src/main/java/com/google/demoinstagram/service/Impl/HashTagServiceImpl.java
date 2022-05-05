package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.HashTag;
import com.google.demoinstagram.excption.ResourceNotFoundException;
import com.google.demoinstagram.excption.service.HashTagValidator;
import com.google.demoinstagram.repository.HashTagRepository;
import com.google.demoinstagram.service.HashTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HashTagServiceImpl implements HashTagService {

    private final HashTagRepository hashTagRepository;
    private HashTagValidator hashTagValidator;

    @Autowired
    public void setHashTagValidator(HashTagValidator hashTagValidator) {
        this.hashTagValidator = hashTagValidator;
    }

    @Override
    public HashTag add(HashTag hashTag) throws Exception {
        hashTagValidator.validate(hashTag);
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
    public HashTag update(HashTag hashTag, Long id) throws Exception {
        hashTagValidator.validate(hashTag);
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

    @Override
    public HashTag getHashTagByText(String hashTag) {
        if (hashTagRepository.existsHashTagByText(hashTag))
            return hashTagRepository.getHashTagByText(hashTag);
        else
            return null;
    }

    @Override
    public void deleteHashTagByText(String hashTagText) throws Exception {
        hashTagValidator.validateDeleteHashTagByText(hashTagText);
        HashTag hashTag = hashTagRepository.getHashTagByText(hashTagText);
        if (hashTag != null)
            hashTagRepository.deleteById(hashTag.getId());
        else throw new Exception("TextInvalid");
    }
}
