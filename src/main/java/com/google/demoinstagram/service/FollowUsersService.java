package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.FollowUsers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FollowUsersService {

    @Transactional
    FollowUsers addFollowerOrFollowing(FollowUsers followUsers) throws Exception;

    List<FollowUsers> FollowListInfo();

    FollowUsers getFollowById(long id);

    void deleteFollowById(long id);

    Long countAllFollowingByUserId(Long userId) throws Exception;

    List<String> getAllFollowingByUserId(Long userId) throws Exception;

    Long countAllFollowerByUserId(Long userId) throws Exception;

    List<String> getAllFollowerByUserId(Long userId) throws Exception;
}
