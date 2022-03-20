package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.FollowUsers;
import com.google.demoinstagram.excption.ResourceNotFoundException;
import com.google.demoinstagram.repository.FollowUsersRepository;
import com.google.demoinstagram.service.FollowUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowUsersServiceImpl implements FollowUsersService {

    private final FollowUsersRepository followUsersRepository;

    @Override
    public FollowUsers create(FollowUsers followUsers) throws Exception {
        if (followUsers.getFollower().getId().compareTo(followUsers.getFollowing().getId()) == 0)
            throw new Exception("CannotBeFollowYourSelf");
        if (followUsersRepository.existsFollowUsersByFollower_IdAndFollowing_Id(followUsers.getFollower().getId(), followUsers.getFollowing().getId())) {
            FollowUsers newFollowUsers = followUsersRepository.getFollowUsersByFollower_IdAndFollowing_Id(followUsers.getFollower().getId(), followUsers.getFollowing().getId());
            if (newFollowUsers != null && newFollowUsers.getId() != null)
                this.delete(newFollowUsers.getId());
        } else
            return followUsersRepository.save(followUsers);

        return null;
    }

    @Override
    public List<FollowUsers> listInfo() {
        return followUsersRepository.findAll();
    }

    @Override
    public FollowUsers get(long id) {
        return followUsersRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("FollowUsers", "id", id));
    }

    @Override
    public void delete(long id) {
        followUsersRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("LikePosts", "Id", id));
        followUsersRepository.deleteById(id);
    }

    @Override
    public Long countAllFollowingByUserId(Long userId) throws Exception {
        if (userId == null)
            throw new Exception("UsersIdCannotBeNull");
        return followUsersRepository.countAllFollowingByUserId(userId);
    }

    @Override
    public List<String> getAllFollowingByUserId(Long userId) throws Exception {
        if (userId == null)
            throw new Exception("UsersIdCannotBeNull");
        return followUsersRepository.getAllFollowingByUserId(userId);
    }

    @Override
    public Long countAllFollowerByUserId(Long userId) throws Exception {
        if (userId == null)
            throw new Exception("UsersIdCannotBeNull");
        return followUsersRepository.countAllFollowerByUserId(userId);
    }

    @Override
    public List<String> getAllFollowerByUserId(Long userId) throws Exception {
        if (userId == null)
            throw new Exception("UsersIdCannotBeNull");
        return followUsersRepository.getAllFollowerByUserId(userId);
    }
}
