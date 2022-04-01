package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.PrivateMessage;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PrivateMessageService {

    @Transactional
    PrivateMessage add(PrivateMessage privateMessage);

    @Transactional
    PrivateMessage update(PrivateMessage privateMessage, Long id);

    PrivateMessage get(Long id);

    List<PrivateMessage> listInfo();

    void delete(Long id);

    List<String> getAllMessageBySendUserIdAndReceiveUserId(PrivateMessage privateMessage);
}
