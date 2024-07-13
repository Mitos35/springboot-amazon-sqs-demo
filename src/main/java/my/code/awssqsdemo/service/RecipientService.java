package my.code.awssqsdemo.service;

import lombok.RequiredArgsConstructor;
import my.code.awssqsdemo.dto.RecipientDto;
import my.code.awssqsdemo.entity.NotificationEntity;
import my.code.awssqsdemo.entity.RecipientEntity;
import my.code.awssqsdemo.mapper.RecipientMapper;
import my.code.awssqsdemo.repository.NotificationRepository;
import my.code.awssqsdemo.repository.RecipientRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipientService {

    private final NotificationRepository notificationRepository;
    private final RecipientRepository recipientRepository;
    private final RecipientMapper recipientMapper;

    public Mono<RecipientDto> findRecipientWithNotificationsByUid(String uid) {
        return Mono.zip(recipientRepository.findById(uid),
                        notificationRepository.findAllByRecipientUid(uid).collectList())
                .map(tuples -> {
                    RecipientEntity recipient = tuples.getT1();
                    List<NotificationEntity> notifications = tuples.getT2();
                    recipient.setNotifications(notifications);
                    return recipientMapper.map(recipient);
                });
    }
}
