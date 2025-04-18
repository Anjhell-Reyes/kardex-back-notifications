package com.kardex.application.dto.notificationDto;

import lombok.Data;

@Data
public class NotificationStatusResponse {
    private Long id;
    private StatusSummaryResponse status;
}
