package com.google.demoinstagram.repository;

import com.google.demoinstagram.entity.PrivateMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Long> {

    boolean existsPrivateMessageBySendUser_IdAndReceiveUser_Id(Long senderId, Long receiverId);

    @Query("select pm.text from PrivateMessage pm where (pm.sendUser.id = :senderId and pm.receiveUser.id = :receiverId)" +
            "or (pm.receiveUser.id = :senderId and pm.sendUser.id = :receiverId)")
    List<String> getAllMessageBySendUser_IdAndReceiveUser_Id(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
}
