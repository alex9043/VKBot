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

            if (request.type().equals("confirmation")) {
                log.info("Проверка сервера");
                return ResponseEntity.ok(confirm);
            }
        }
        return ResponseEntity.ok("ok");
    }
}
