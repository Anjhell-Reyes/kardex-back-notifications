package com.kardex.infrastructure.out.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class NotificationEntity {

    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private String message;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")  // Debe coincidir con StatusEntity
    private TypeEntity type;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")  // Debe coincidir con StatusEntity
    private StatusEntity status;
}
