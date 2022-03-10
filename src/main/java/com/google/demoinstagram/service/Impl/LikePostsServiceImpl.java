package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.LikePosts;
import com.google.demoinstagram.excption.ResourceNotFoundException;
import com.google.demoinstagram.repository.LikePostsRepository;
import com.google.demoinstagram.service.LikePostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikePostsServiceImpl implements LikePostsService {

    private final LikePostsRepository likePostsRepository;

    @Override
    public LikePosts create(LikePosts likePosts) {
        if (likePostsRepository.existsLikePostsByUsersIdAndPostsId(likePosts.getUsersId(), likePosts.getPostsId())) {
            LikePosts newLikePosts = likePostsRepository.getLikePostsByUsersId_IdAndPostsId_Id(likePosts.getUsersId().getId(), likePosts.getPostsId().getId());
            if (newLikePosts.getId() != null) {
                this.delete(newLikePosts.getId());
            }
        } else
            return likePostsRepository.save(likePosts);

        return null;
    }

    @Override
    public void delete(long id) {
        likePostsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("LikePosts", "Id", id));
        likePostsRepository.deleteById(id);
    }
}
