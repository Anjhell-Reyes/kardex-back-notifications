package com.kardex.application.dto.notificationDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TypeSummaryResponse {
    private Long id;
    private String typeName;
}
