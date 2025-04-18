package com.kardex.infrastructure.out.adapter;

import com.kardex.domain.exception.NotDataFoundException;
import com.kardex.domain.exception.NotificationNotFoundException;
import com.kardex.domain.model.CustomPage;
import com.kardex.domain.model.Notification;
import com.kardex.domain.model.Status;
import com.kardex.domain.spi.INotificationPersistencePort;
import com.kardex.infrastructure.out.entity.NotificationEntity;
import com.kardex.infrastructure.out.mapper.NotificationEntityMapper;
import com.kardex.infrastructure.out.mapper.StatusEntityMapper;
import com.kardex.infrastructure.out.repository.INotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class NotificationJpaAdapter implements INotificationPersistencePort {

    private final INotificationRepository notificationRepository;
    private final NotificationEntityMapper notificationEntityMapper;
    private final StatusEntityMapper statusEntityMapper;

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationEntityMapper.toNotification(notificationRepository.save(notificationEntityMapper.toEntity(notification)));
    }

    @Override
    public Notification getNotification(Long id) {
        return notificationEntityMapper.toNotification(notificationRepository.findById(id).orElseThrow(NotificationNotFoundException::new));
    }

    @Override
    public CustomPage<Notification> getAllNotifications(String userId, int offset, int limit, String sortBy, boolean asc) {
        Sort sort = Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(offset / limit, limit, sort);

        // Filtramos por userId
        Page<NotificationEntity> notificationPage = notificationRepository.findAllByUserId(userId, pageable);

        if (notificationPage.isEmpty()) {
            throw new NotDataFoundException();
        }

        List<Notification> notifications = notificationPage.getContent().stream()
                .map(notificationEntityMapper::toNotification)
                .collect(Collectors.toList());

        return new CustomPage<>(
                notifications,
                notificationPage.getNumber(),
                notificationPage.getSize(),
                notificationPage.getTotalElements()
        );
    }

    @Override
    public void updateStatusNotification(Long notificationId, Status status) {

        notificationRepository.updateStatus(notificationId, statusEntityMapper.toStatusEntity(status));
    }

    @Override
    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    @Override
    public void deleteAllReadNotifications(String userId) {
        notificationRepository.deleteAllReadNotifications(userId);
    }

    @Override
    public List<Notification> getNotificationStatus(String userId) {
        List<NotificationEntity> notificationEntities = notificationRepository.findAllByUserId(userId);
        return notificationEntities.stream()
                .map(notificationEntityMapper::toNotification)
                .collect(Collectors.toList());
    }
}
