package com.profitgate.repository;

import com.profitgate.entity.Message;
import com.profitgate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findBySenderAndReceiver(User sender, User receiver);
    List<Message> findBySender(User sender);
    List<Message> findByReceiver(User receiver);
}
