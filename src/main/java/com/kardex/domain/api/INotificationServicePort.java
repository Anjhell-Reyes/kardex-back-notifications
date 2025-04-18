package com.kardex.domain.api;

import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Notification;
import com.kardex.domain.utils.NotificationType;

import java.util.List;

public interface INotificationServicePort {
    Notification saveNotification(String userId, String productName, String contextualMessage, NotificationType notificationType);

    CustomPage<Notification> getAllNotifications(String userId, int page, int size, String sortBy, boolean asc);

    void updateStatusNotification(String userId, Long notificationId, Long notificationStatusId);

    void deleteNotification(Long notificationId, String userId);

    void deleteAllReadNotifications(String userId);

    List<Notification> getNotificationStatus(String userId);
}
