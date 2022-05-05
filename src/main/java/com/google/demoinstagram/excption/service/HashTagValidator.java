package com.google.demoinstagram.excption.service;

import com.google.demoinstagram.entity.HashTag;
import com.google.demoinstagram.repository.HashTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HashTagValidator {

    private HashTagRepository hashTagRepository;

    @Autowired
    public void setHashTagRepository(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    public void validate(HashTag hashTag) throws Exception {
        if (hashTag != null)
            checkDuplicateHashTagText(hashTag);
    }

    private void checkDuplicateHashTagText(HashTag hashTag) throws Exception {
        if (hashTagRepository.existsHashTagByText(hashTag.getText()))
            throw new Exception("HashTagIsDuplicated");
    }

    public void validateDeleteHashTagByText(String hashTagText) throws Exception {
        if (hashTagText == null || hashTagText.length() == 0)
            throw new Exception("HashTagTextIsEmpty");
    }
}
