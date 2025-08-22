package com.profitgate.controller;

import com.profitgate.dto.SendMessageRequest;
import com.profitgate.entity.Message;
import com.profitgate.entity.User;
import com.profitgate.repository.MessageRepository;
import com.profitgate.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageRepository messageRepo;
    private final UserRepository userRepo;

    public MessageController(MessageRepository messageRepo, UserRepository userRepo) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }

    @PostMapping
    public Message send(@RequestBody @Valid SendMessageRequest req) {
        User sender = userRepo.findById(req.senderUserId()).orElseThrow(() -> new NoSuchElementException("Sender not found"));
        User receiver = userRepo.findById(req.receiverUserId()).orElseThrow(() -> new NoSuchElementException("Receiver not found"));
        Message m = new Message();
        m.setSender(sender);
        m.setReceiver(receiver);
        m.setContent(req.message());
        return messageRepo.save(m);
    }

    @GetMapping("/inbox/{userId}")
    public List<Message> inbox(@PathVariable Integer userId) {
        User u = userRepo.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        return messageRepo.findByReceiver(u);
    }

    @GetMapping("/sent/{userId}")
    public List<Message> sent(@PathVariable Integer userId) {
        User u = userRepo.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        return messageRepo.findBySender(u);
    }
}
