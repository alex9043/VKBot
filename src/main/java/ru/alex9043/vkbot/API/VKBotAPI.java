package ru.alex9043.vkbot.API;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.alex9043.vkbot.converter.VKBotConverter;
import ru.alex9043.vkbot.dto.VKBotResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class VKBotAPI {

    private final RestTemplate restTemplate;
    private final VKBotConverter converter;

    /**
     * Отправляет ответ от бота пользователю
     */
    public void sendMessageFromBot(VKBotResponse response) {
        log.info("Отправка сообщения в вк");

        MultiValueMap<String, String> formData = converter.toFormData(response);

        formData.forEach((key, value) -> log.debug("key - {}, value - {}", key, value));

        restTemplate.postForObject(
                "https://api.vk.com/method/messages.send",
                formData,
                String.class
        );
    }
}
