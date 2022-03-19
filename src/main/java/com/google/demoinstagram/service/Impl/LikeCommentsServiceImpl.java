package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.LikeComments;
import com.google.demoinstagram.excption.ResourceNotFoundException;
import com.google.demoinstagram.repository.LikeCommentsRepository;
import com.google.demoinstagram.service.LikeCommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikeCommentsServiceImpl implements LikeCommentsService {

    private final LikeCommentsRepository likeCommentsRepository;

    @Transactional
    @Override
    public LikeComments create(LikeComments likeComments) {
        if (likeCommentsRepository.existsLikeCommentsByPostsCommentsIdAndUsersIdAndPostsId(likeComments.getPostsCommentsId(),
                likeComments.getUsersId(), likeComments.getPostsId())) {
            LikeComments newLikePosts = likeCommentsRepository.getLikeCommentsByPostsCommentsId_IdAndUsersId_IdAndPostsId_Id(likeComments.getPostsCommentsId().getId(),
                    likeComments.getUsersId().getId(), likeComments.getPostsId().getId());
            if (newLikePosts != null && newLikePosts.getId() != null) {
                this.delete(newLikePosts.getId());
            }
        } else {
            if (likeComments.getLiked().equals(true))
                return likeCommentsRepository.save(likeComments);
            else
                return null;
        }

        return null;
    }

    @Override
    public void delete(long id) {
        likeCommentsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("LikeComments", "Id", id));
        likeCommentsRepository.deleteById(id);
    }

    @Override
    public Long countAllLikeCommentsByPostsCommentsIdAndPostsId(Long postsCommentsId, Long postsId) throws Exception {
        if (postsCommentsId == null || postsId == null)
            throw new Exception("likeCommentsCannotBeNull");
        return likeCommentsRepository.countAllByPostsCommentsId_IdAndPostsId_Id(postsCommentsId, postsId);
    }
}
