package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.LikeComments;
import com.google.demoinstagram.entity.PostsComments;
import com.google.demoinstagram.excption.ResourceNotFoundException;
import com.google.demoinstagram.repository.LikeCommentsRepository;
import com.google.demoinstagram.service.LikeCommentsService;
import com.google.demoinstagram.service.PostsCommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LikeCommentsServiceImpl implements LikeCommentsService {

    private final LikeCommentsRepository likeCommentsRepository;
    private final PostsCommentsService postsCommentsService;

    @Transactional
    @Override
    public LikeComments createLikeComment(LikeComments likeComments) {
        if (likeCommentsRepository.existsLikeCommentsByPostsComments_IdAndUsers_IdAndPosts_Id(likeComments.getPostsComments().getId(),
                likeComments.getUsers().getId(), likeComments.getPosts().getId())) {
            LikeComments newLikePosts = likeCommentsRepository.getLikeCommentsByPostsComments_IdAndUsers_IdAndPosts_Id(likeComments.getPostsComments().getId(),
                    likeComments.getUsers().getId(), likeComments.getPosts().getId());
            if (newLikePosts != null && newLikePosts.getId() != null) {
                this.delete(newLikePosts.getId());
                PostsComments postsComments = postsCommentsService.getComment(likeComments.getPostsComments().getId());
                Long countLikeComment = postsComments.getCountLikeComment() - 1;
                postsComments.setCountLikeComment(countLikeComment);
                postsCommentsService.updateCountLikeComment(postsComments, likeComments.getPostsComments().getId());
            }
        } else {
            if (likeComments.getLiked().equals(true)) {
                LikeComments saveLikeComment = likeCommentsRepository.save(likeComments);
                PostsComments postsComments = postsCommentsService.getComment(likeComments.getPostsComments().getId());
                Long countLikeComment = postsComments.getCountLikeComment() + 1;
                postsComments.setCountLikeComment(countLikeComment);
                postsCommentsService.updateCountLikeComment(postsComments, likeComments.getPostsComments().getId());
                return saveLikeComment;
            } else
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
    public Long countAllLikeCommentsByPostsCommentsId(Long postsCommentsId) throws Exception {
        if (postsCommentsId == null)
            throw new Exception("likeCommentsCannotBeNull");
        return likeCommentsRepository.countAllByPostsCommentsId_Id(postsCommentsId);
    }

    @Override
    public List<String> getAllUsernameLikedCommentsByPostCommentId(Long postsCommentsId) throws Exception {
        if (postsCommentsId == null)
            throw new Exception("likeCommentsCannotBeNull");
        return likeCommentsRepository.findAllUsernameLikedCommentsByPostCommentId(postsCommentsId);
    }
}
