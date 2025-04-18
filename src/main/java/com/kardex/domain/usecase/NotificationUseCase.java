package com.kardex.domain.usecase;

import com.kardex.domain.api.INotificationServicePort;
import com.kardex.domain.exception.*;
import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Notification;
import com.kardex.domain.model.Status;
import com.kardex.domain.spi.INotificationPersistencePort;
import com.kardex.domain.spi.IStatusPersistencePort;
import com.kardex.domain.spi.ITypePersistencePort;
import com.kardex.domain.utils.Constants;
import com.kardex.domain.utils.NotificationType;

import java.util.List;

public class NotificationUseCase implements INotificationServicePort {

    private final INotificationPersistencePort notificationPersistencePort;
    private final ITypePersistencePort typePersistencePort;
    private final IStatusPersistencePort statusPersistencePort;

    public NotificationUseCase(INotificationPersistencePort notificationPersistencePort, ITypePersistencePort typePersistencePort, IStatusPersistencePort statusPersistencePort) {
        this.notificationPersistencePort = notificationPersistencePort;
        this.typePersistencePort = typePersistencePort;
        this.statusPersistencePort = statusPersistencePort;
    }

    @Override
    public Notification saveNotification(String userId, String productName, String contextualMessage, NotificationType notificationType) {

        validateInputs(userId, productName, contextualMessage);

        String message = switch (notificationType) {
            case LOW_STOCK_ALERT -> Constants.CONTENT_LOW_STOCK_MESSAGE(productName, contextualMessage);
            case ORDER_STATUS_UPDATE -> Constants.CONTENT_ORDER_STATUS_UPDATE_MESSAGE(productName, contextualMessage);
        };

        Long typeId = switch (notificationType) {
            case LOW_STOCK_ALERT -> Constants.LOW_STOCK_TYPE_ID;
            case ORDER_STATUS_UPDATE -> Constants.ORDER_TYPE_ID;
        };

        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setType(typePersistencePort.getTypeById(typeId));
        notification.setStatus(statusPersistencePort.getStatusById(Constants.DEFAULT_STATUS_ID));

        return notificationPersistencePort.saveNotification(notification);
    }


    @Override
    public CustomPage<Notification> getAllNotifications(String userId, int page, int size, String sortBy, boolean asc) {
        if(page < 0){
            throw new InvalidPageIndexException();
        }
        int offset =page * size;
        return notificationPersistencePort.getAllNotifications(userId, offset, size, sortBy, asc);
    }

    @Override
    public void updateStatusNotification(String userId, Long notificationId, Long notificationStatusId) {
        Notification notification = notificationPersistencePort.getNotification(notificationId);

        if(!notification.getUserId().equals(userId)){
            throw new UserForbiddenException();
        }

        Status status = statusPersistencePort.getStatusById(notificationStatusId);
        notificationPersistencePort.updateStatusNotification(notificationId, status);
    }

    @Override
    public void deleteNotification(Long notificationId, String userId) {
        Notification notification = notificationPersistencePort.getNotification(notificationId);

        if(notification != null){
            notificationPersistencePort.deleteNotification(notificationId);
        }
    }

    @Override
    public void deleteAllReadNotifications(String userId) {
        notificationPersistencePort.deleteAllReadNotifications(userId);
    }

    @Override
    public List<Notification> getNotificationStatus(String userId) {
        List<Notification> notifications = notificationPersistencePort.getNotificationStatus(userId);
        if (notifications.isEmpty()) {
            throw new NotificationNotFoundException();
        }
        return notifications;
    }

    private void validateInputs(String userId, String productName, String contextualMessage) {
        if (userId == null) {
            throw new UserIdNotNullException();
        }
        if (productName == null || productName.isEmpty()) {
            throw new ProductNameNotNullException();
        }
        if (contextualMessage == null || contextualMessage.isEmpty()) {
            throw new MessageNotNullException();
        }
    }
}
