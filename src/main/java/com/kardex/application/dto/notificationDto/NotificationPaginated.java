package com.kardex.application.dto.notificationDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationPaginated {
    private Long id;
    private String message;
    private TypeSummaryResponse type;
    private LocalDateTime createdAt;
    private StatusSummaryResponse status;
}
