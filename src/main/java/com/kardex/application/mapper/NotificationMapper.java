package com.kardex.application.mapper;

import com.kardex.application.dto.notificationDto.NotificationPaginated;
import com.kardex.application.dto.notificationDto.NotificationStatusResponse;
import com.kardex.application.dto.notificationDto.StatusSummaryResponse;
import com.kardex.application.dto.notificationDto.TypeSummaryResponse;
import com.kardex.domain.model.Notification;
import com.kardex.domain.model.Status;
import com.kardex.domain.model.Type;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface NotificationMapper {

    @Mapping(source = "type", target = "type", qualifiedByName = "toTypeSummaryResponse")
    @Mapping(source = "status", target = "status", qualifiedByName = "toStatusSummaryResponse")
    NotificationPaginated toNotificationPaginated(Notification notification);

    @Mapping(source = "status", target = "status", qualifiedByName = "toStatusSummaryResponse")
    NotificationStatusResponse toNotificationResponse(Notification notificationStatusResponse);

    @Named("toTypeSummaryResponse")
    default TypeSummaryResponse toTypeSummaryResponse(Type type) {
        return new TypeSummaryResponse(type.getTypeId(), type.getType());
    }

    @Named("toStatusSummaryResponse")
    default StatusSummaryResponse toStatusSummaryResponse(Status status) {
        return new StatusSummaryResponse(status.getStatusId(), status.getStatus());
    }
}
