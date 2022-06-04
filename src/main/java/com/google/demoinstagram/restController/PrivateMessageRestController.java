package com.google.demoinstagram.restController;

import com.google.demoinstagram.entity.PrivateMessage;
import com.google.demoinstagram.service.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/message")
public class PrivateMessageRestController {

    private PrivateMessageService privateMessageService;

    @Autowired
    public void setPrivateMessageService(PrivateMessageService privateMessageService) {
        this.privateMessageService = privateMessageService;
    }

    // http://localhost:8085/demoInstagram/api/message/add
    @PostMapping(value = "/add")
    public ResponseEntity<PrivateMessage> add(@RequestBody PrivateMessage privateMessage) {
        return new ResponseEntity<>(privateMessageService.addMessage(privateMessage), HttpStatus.CREATED);
    }

    // http://localhost:8085/demoInstagram/api/message/update/1
    @PutMapping("/update/{id}")
    public ResponseEntity<PrivateMessage> update(@PathVariable("id") Long id
            , @RequestBody PrivateMessage privateMessage) {
        return new ResponseEntity<>(privateMessageService.updateMessage(privateMessage, id), HttpStatus.OK);
    }

    // http://localhost:8085/demoInstagram/api/message
    @GetMapping(value = {"", "/"})
    public List<PrivateMessage> listInfo() {
        return privateMessageService.messageListInfo();
    }

    // http://localhost:8085/demoInstagram/api/message/delete/1
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        privateMessageService.deleteMessage(id);
        return new ResponseEntity<>("Message Deleted Successfully!", HttpStatus.OK);
    }

    // http://localhost:8085/demoInstagram/api/message/get/1
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<PrivateMessage> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(privateMessageService.getMessage(id), HttpStatus.OK);
    }

    // http://localhost:8085/demoInstagram/api/message/get-all-text
    @PostMapping(value = {"/get-all-text"})
    public ResponseEntity<List<String>> getAllMessageBySendUserIdAndReceiveUserId(@RequestBody PrivateMessage privateMessage) {
        return new ResponseEntity<>(privateMessageService.getAllMessageBySendUserIdAndReceiveUserId(privateMessage), HttpStatus.OK);
    }
}
