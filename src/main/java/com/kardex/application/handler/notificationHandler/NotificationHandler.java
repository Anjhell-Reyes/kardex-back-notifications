package com.kardex.application.handler.notificationHandler;

import com.kardex.application.dto.notificationDto.NotificationPaginated;
import com.kardex.application.dto.notificationDto.NotificationStatusResponse;
import com.kardex.application.mapper.NotificationMapper;
import com.kardex.domain.api.INotificationServicePort;
import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Notification;
import com.kardex.domain.utils.NotificationType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationHandler implements INotificationHandler {

    private final INotificationServicePort notificationServicePort;
    private final NotificationMapper notificationMapper;

    private String getAuthenticatedUserId() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public void saveOrderNotification(String userId, String productName, String contextualMessage) {
        notificationServicePort.saveNotification(userId,productName,contextualMessage,NotificationType.ORDER_STATUS_UPDATE);
    }

    @Override
    public void saveProductNotification(String userId, String productName, String contextualMessage) {
        notificationServicePort.saveNotification(userId,productName,contextualMessage,NotificationType.LOW_STOCK_ALERT);
    }

    @Override
    public Page<NotificationPaginated> getAllNotifications(int page, int size, String sortBy, boolean asc) {
        String userId = getAuthenticatedUserId();
        CustomPage<Notification> customPage = notificationServicePort.getAllNotifications(userId, page, size, sortBy, asc);

        List<NotificationPaginated> paginatedNotifications = customPage.getContent().stream()
                .map(notificationMapper::toNotificationPaginated)
                .collect(Collectors.toList());

        return new PageImpl<>(paginatedNotifications, PageRequest.of(customPage.getPageNumber(), customPage.getPageSize()), customPage.getTotalElements());
    }

    @Override
    public void updateStatusNotification(Long notificationId, Long notificationStatusId) {
        String userId = getAuthenticatedUserId();
        notificationServicePort.updateStatusNotification(userId, notificationId, notificationStatusId);
    }

    @Override
    public void deleteNotification(Long notificationId) {
        String userId = getAuthenticatedUserId();
        notificationServicePort.deleteNotification(notificationId, userId);
    }

    @Override
    public void deleteAllReadNotifications() {
        String userId = getAuthenticatedUserId();
        notificationServicePort.deleteAllReadNotifications(userId);
    }

    @Override
    public List<NotificationStatusResponse> getNotificationStatus() {
        String userId = getAuthenticatedUserId();
        List<Notification> notificationStatusResponses = notificationServicePort.getNotificationStatus(userId);
        return notificationStatusResponses.stream()
                .map(notificationMapper::toNotificationResponse)
                .collect(Collectors.toList());
    }
}
