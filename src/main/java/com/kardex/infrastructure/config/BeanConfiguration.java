package com.kardex.infrastructure.config;

import com.kardex.domain.api.INotificationServicePort;
import com.kardex.domain.spi.INotificationPersistencePort;
import com.kardex.domain.spi.IStatusPersistencePort;
import com.kardex.domain.spi.ITypePersistencePort;
import com.kardex.domain.usecase.NotificationUseCase;
import com.kardex.infrastructure.out.adapter.NotificationJpaAdapter;
import com.kardex.infrastructure.out.adapter.StatusJpaAdapter;
import com.kardex.infrastructure.out.adapter.TypeJpaAdapter;
import com.kardex.infrastructure.out.mapper.NotificationEntityMapper;
import com.kardex.infrastructure.out.mapper.StatusEntityMapper;
import com.kardex.infrastructure.out.mapper.TypeEntityMapper;
import com.kardex.infrastructure.out.repository.INotificationRepository;
import com.kardex.infrastructure.out.repository.IStatusRepository;
import com.kardex.infrastructure.out.repository.ITypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final INotificationRepository notificationRepository;
    private final NotificationEntityMapper notificationEntityMapper;
    private final IStatusRepository statusRepository;
    private final StatusEntityMapper statusEntityMapper;
    private final ITypeRepository typeRepository;
    private final TypeEntityMapper typeEntityMapper;

    @Bean
    public INotificationPersistencePort notificationPersistencePort(){
        return new NotificationJpaAdapter(notificationRepository, notificationEntityMapper, statusEntityMapper);
    }

    @Bean
    public IStatusPersistencePort statusPersistencePort(){
        return new StatusJpaAdapter(statusRepository, statusEntityMapper);
    }

    @Bean
    public ITypePersistencePort typePersistencePort(){
        return new TypeJpaAdapter(typeRepository, typeEntityMapper);
    }

    @Bean
    public INotificationServicePort notificationServicePort() {
        return new NotificationUseCase(notificationPersistencePort(),typePersistencePort(),statusPersistencePort());
    }
}
