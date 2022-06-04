package com.google.demoinstagram.service;

import com.google.demoinstagram.entity.PrivateMessage;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PrivateMessageService {

    @Transactional
    PrivateMessage addMessage(PrivateMessage privateMessage);

    @Transactional
    PrivateMessage updateMessage(PrivateMessage privateMessage, Long id);

    PrivateMessage getMessage(Long id);

    List<PrivateMessage> messageListInfo();

    void deleteMessage(Long id);

    List<String> getAllMessageBySendUserIdAndReceiveUserId(PrivateMessage privateMessage);
}
