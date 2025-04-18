package com.kardex.infrastructure.out.repository;


import com.kardex.infrastructure.out.entity.NotificationEntity;
import com.kardex.infrastructure.out.entity.StatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface INotificationRepository extends JpaRepository<NotificationEntity, Long> {
    Page<NotificationEntity> findAllByUserId(String userId, Pageable pageable);
    List<NotificationEntity> findAllByUserId(String userId);


    @Modifying
    @Query("UPDATE NotificationEntity n SET n.status = :status WHERE n.id = :notificationId")
    void updateStatus(Long notificationId, StatusEntity status);

    @Modifying
    @Query("DELETE FROM NotificationEntity n WHERE n.userId= :userId AND n.status.statusId = 2")
    void deleteAllReadNotifications(String userId);

}
