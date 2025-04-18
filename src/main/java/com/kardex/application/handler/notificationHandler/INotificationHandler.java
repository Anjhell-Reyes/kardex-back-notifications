package com.kardex.application.handler.notificationHandler;


import com.kardex.application.dto.notificationDto.NotificationPaginated;
import com.kardex.application.dto.notificationDto.NotificationStatusResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface INotificationHandler {

    void saveOrderNotification(@RequestParam String userId, String productName, String contextualMessage);

    void saveProductNotification(@RequestParam String userId, String productName, String contextualMessage);

    void deleteNotification(Long productId);

    Page<NotificationPaginated> getAllNotifications(int page, int size, String sortBy, boolean asc);

    void updateStatusNotification(Long notificationId, Long notificationStatusId);

    void deleteAllReadNotifications();

    List<NotificationStatusResponse> getNotificationStatus();
}
