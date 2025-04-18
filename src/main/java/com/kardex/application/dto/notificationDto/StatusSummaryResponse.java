package com.kardex.application.dto.notificationDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusSummaryResponse {
    private Long id;
    private String statusName;
}
