package my.code.awssqsdemo.service;

import lombok.RequiredArgsConstructor;
import my.code.awssqsdemo.dto.NotificationDto;
import my.code.awssqsdemo.mapper.NotificationMapper;
import my.code.awssqsdemo.repository.NotificationRepository;
import my.code.awssqsdemo.repository.RecipientRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final RecipientRepository recipientRepository;
    private final NotificationMapper notificationMapper;

    public Mono<NotificationDto> findNotificationByUid(String uid) {
        return notificationRepository.findById(uid)
                .map(notificationMapper::map);
    }

    public Mono<NotificationDto> findNotificationWithRecipientByUid(String uid) {
        return notificationRepository.findById(uid)
                .flatMap(notificationEntity -> recipientRepository.findById(notificationEntity.getRecipientUid())
                        .map(recipientEntity -> {
                            notificationEntity.setRecipient(recipientEntity);
                            return notificationEntity;
                        }).map(notificationMapper::map));
    }
}
