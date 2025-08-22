package com.profitgate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne(optional = false)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "sent_at")
    private Timestamp sentAt;
}
