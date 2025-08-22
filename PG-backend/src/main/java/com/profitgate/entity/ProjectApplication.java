package com.profitgate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "project_applications")
public class ProjectApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 32)
    private Status status = Status.pending;

    @Column(name = "applied_at")
    private Timestamp appliedAt;

    public enum Status { pending, accepted, rejected }
}
