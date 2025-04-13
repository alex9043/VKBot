package ru.alex9043.vkbot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.alex9043.vkbot.dto.VKBotRequest;

@Service
@Slf4j
public class VKBotService {
    @Value("${vk.callback.api.confirm}")
    private String confirm;
    @Value("${vk.callback.api.secret}")
    private String secret;

    public ResponseEntity<?> vkRequest(VKBotRequest request) {
        log.info("Запрос от вк");

        if (request.secret().equals(secret)) {
            log.info("Запрос валидный");

            if (checkRequestType(request, "confirmation")) {
                log.info("Проверка сервера");
                return ResponseEntity.ok(confirm);
            } else if (checkRequestType(request, "message_new")) {
                log.info("Новое сообщение");
                log.debug("Данные сообщения - {}", request);
            }
        }
        return ResponseEntity.ok("ok");
    }

    private boolean checkRequestType(VKBotRequest request, String type) {
        return request.type().equals(type);
    }
}
