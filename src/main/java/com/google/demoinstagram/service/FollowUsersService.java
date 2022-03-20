package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.FollowUsers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FollowUsersService {

    @Transactional
    FollowUsers create(FollowUsers followUsers) throws Exception;

    List<FollowUsers> listInfo();

    FollowUsers get(long id);

    void delete(long id);

    Long countAllFollowingByUserId(Long userId) throws Exception;

    List<String> getAllFollowingByUserId(Long userId) throws Exception;

    Long countAllFollowerByUserId(Long userId) throws Exception;

    List<String> getAllFollowerByUserId(Long userId) throws Exception;
}
