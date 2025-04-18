package com.kardex.domain.spi;

import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Notification;
import com.kardex.domain.model.Status;

import java.util.List;

public interface INotificationPersistencePort {
    Notification saveNotification(Notification notification);

    Notification getNotification(Long notificationId);

    CustomPage<Notification> getAllNotifications(String userId, int offset, int limit, String sortBy, boolean asc);

    void updateStatusNotification(Long notificationId, Status status);

    void deleteNotification(Long notificationId);

    void deleteAllReadNotifications(String userId);

    List<Notification> getNotificationStatus(String userId);
}
