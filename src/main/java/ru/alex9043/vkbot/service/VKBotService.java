package ru.alex9043.vkbot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.alex9043.vkbot.API.VKBotAPI;
import ru.alex9043.vkbot.converter.VKBotConverter;
import ru.alex9043.vkbot.dto.VKBotRequest;
import ru.alex9043.vkbot.dto.VKBotResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class VKBotService {

    @Value("${vk.callback.api.confirm}")
    private String confirm;
    @Value("${vk.callback.api.secret}")
    private String secret;

    private final VKBotConverter converter;
    private final VKBotAPI api;

    public ResponseEntity<String> vkRequest(VKBotRequest request) {
        log.info("Запрос от вк");

        if (request.getSecret().equals(secret)) {
            log.info("Запрос валидный");

            if (checkRequestType(request, "confirmation")) {
                log.info("Проверка сервера");
                return ResponseEntity.ok(confirm);
            } else if (checkRequestType(request, "message_new")) {
                log.info("Новое сообщение");
                log.debug("Request data - {}", request);

                getMessage(request).setText(setNewMessage(request));

                VKBotResponse response = converter.toResponse(request);

                log.debug("Response data - {}", response);

                api.sendMessageFromBot(response);
            }
        }
        return ResponseEntity.ok("ok");
    }

    private boolean checkRequestType(VKBotRequest request, String type) {
        return request.getType().equals(type);
    }

    private VKBotRequest.VKBotRequestObject.VKBotRequestObjectMessage getMessage(VKBotRequest request) {
        return request.getObject().getMessage();
    }

    private String setNewMessage(VKBotRequest request) {
        return "Вы сказали: " + getMessage(request).getText();
    }
}
