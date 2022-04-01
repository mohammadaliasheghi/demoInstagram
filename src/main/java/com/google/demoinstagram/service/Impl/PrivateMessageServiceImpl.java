package com.google.demoinstagram.service.Impl;

import com.google.demoinstagram.entity.PrivateMessage;
import com.google.demoinstagram.excption.ResourceNotFoundException;
import com.google.demoinstagram.repository.PrivateMessageRepository;
import com.google.demoinstagram.service.PrivateMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PrivateMessageServiceImpl implements PrivateMessageService {

    private final PrivateMessageRepository privateMessageRepository;

    @Transactional
    @Override
    public PrivateMessage add(PrivateMessage privateMessage) {
        return privateMessageRepository.save(privateMessage);
    }

    @Transactional
    @Override
    public PrivateMessage update(PrivateMessage privateMessage, Long id) {
        PrivateMessage existingPrivateMessage = privateMessageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("PrivateMessage", "id", id));
        existingPrivateMessage.setText(privateMessage.getText());
        privateMessageRepository.save(existingPrivateMessage);
        return existingPrivateMessage;
    }

    @Override
    public PrivateMessage get(Long id) {
        return privateMessageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("PrivateMessage", "id", id));
    }

    @Override
    public List<PrivateMessage> listInfo() {
        return privateMessageRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        privateMessageRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Users", "id", id));
        privateMessageRepository.deleteById(id);
    }

    @Override
    public List<String> getAllMessageBySendUserIdAndReceiveUserId(PrivateMessage privateMessage) {
        if (privateMessage != null && privateMessage.getSendUser() != null && privateMessage.getReceiveUser() != null) {
            if (privateMessageRepository.existsPrivateMessageBySendUser_IdAndReceiveUser_Id(privateMessage.getSendUser().getId(), privateMessage.getReceiveUser().getId())) {
                return privateMessageRepository.getAllMessageBySendUser_IdAndReceiveUser_Id(privateMessage.getSendUser().getId(), privateMessage.getReceiveUser().getId());
            }
        }
        return null;
    }
}
