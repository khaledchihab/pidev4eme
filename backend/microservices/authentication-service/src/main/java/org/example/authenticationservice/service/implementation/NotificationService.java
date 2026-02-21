package org.example.authenticationservice.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.example.authenticationservice.dto.NotificationRequestDTO;
import org.example.authenticationservice.web.NotificationRestClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    private final NotificationRestClient notificationRestClient;

    public NotificationService(NotificationRestClient notificationRestClient) {
        this.notificationRestClient = notificationRestClient;
    }

    @Async
    public void send(String to, String subject, String body) {
        try {
            NotificationRequestDTO dto = new NotificationRequestDTO(to, subject, body);
            notificationRestClient.sendNotification(dto);
            log.info("Notification sent to {}", to);
        } catch (Exception e) {
            log.warn("Failed to send notification to {} (NOTIFICATION-SERVICE may be unavailable): {}", to,
                    e.getMessage());
        }
    }
}
