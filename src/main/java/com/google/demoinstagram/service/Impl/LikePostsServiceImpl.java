package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.LikePosts;
import com.google.demoinstagram.entity.Posts;
import com.google.demoinstagram.excption.ResourceNotFoundException;
import com.google.demoinstagram.repository.LikePostsRepository;
import com.google.demoinstagram.service.LikePostsService;
import com.google.demoinstagram.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LikePostsServiceImpl implements LikePostsService {

    private final LikePostsRepository likePostsRepository;
    private final PostsService postsService;

    @Override
    public LikePosts createLikePost(LikePosts likePosts) {
        if (likePostsRepository.existsLikePostsByUsers_IdAndPosts_Id(likePosts.getUsers().getId(), likePosts.getPosts().getId())) {
            LikePosts newLikePosts = likePostsRepository.getLikePostsByUsers_IdAndPosts_Id(likePosts.getUsers().getId(), likePosts.getPosts().getId());
            if (newLikePosts != null && newLikePosts.getId() != null) {
                Posts posts = postsService.getPost(likePosts.getPosts().getId());
                Long countLikePosts = posts.getCountLike() - 1;
                posts.setCountLike(countLikePosts);
                postsService.updateCountLike(posts, likePosts.getPosts().getId());
                this.delete(newLikePosts.getId());
            }
        } else {
            if (likePosts.getLiked().equals(true)) {
                Posts posts = postsService.getPost(likePosts.getPosts().getId());
                Long countLikePosts = posts.getCountLike() + 1;
                posts.setCountLike(countLikePosts);
                postsService.updateCountLike(posts, likePosts.getPosts().getId());
                return likePostsRepository.save(likePosts);
            } else
                return null;
        }

        return null;
    }

    @Override
    public void delete(long id) {
        likePostsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("LikePosts", "Id", id));
        likePostsRepository.deleteById(id);
    }

    @Override
    public List<LikePosts> listInfoUsersLikedPost(Long postId) throws Exception {
        if (postId == null)
            throw new Exception("PostsIdCannotBeNull");
        return likePostsRepository.getAllByPosts_Id(postId);
    }

    @Override
    public Long countAllLikeByPostsId(Long postId) throws Exception {
        if (postId == null)
            throw new Exception("PostsIdCannotBeNull");
        return likePostsRepository.countAllByPosts_Id(postId);
    }

    @Override
    public List<String> getAllUsernameLikedPostByPostId(Long postId) throws Exception {
        if (postId == null)
            throw new Exception("PostsIdCannotBeNull");
        return likePostsRepository.findAllUsernameLikedPostByPostId(postId);
    }
}
