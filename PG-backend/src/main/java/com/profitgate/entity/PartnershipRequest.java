package com.profitgate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "partnership_requests")
public class PartnershipRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_company_id", nullable = false)
    private Company senderCompany;

    @ManyToOne(optional = false)
    @JoinColumn(name = "receiver_company_id", nullable = false)
    private Company receiverCompany;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 32)
    private Status status = Status.pending;

    @Column(name = "sent_at")
    private Timestamp sentAt;

    public enum Status { pending, accepted, rejected }
}
