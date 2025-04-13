package ru.alex9043.vkbot.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.alex9043.vkbot.dto.VKBotRequest;
import ru.alex9043.vkbot.dto.VKBotResponse;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class VKBotConverter {

    @Value("${vk.api.key}")
    private String accessToken;
    @Value("${vk.callback.api.version}")
    private String version;

    /**
     * Конвертирует VKBotRequest в VKBotResponse
     */
    public VKBotResponse toResponse(VKBotRequest request) {
        VKBotResponse vkBotResponse = new VKBotResponse();
        vkBotResponse.setMessage(request.getObject().getMessage().getText());
        vkBotResponse.setUserId(request.getObject().getMessage().getFromId());
        vkBotResponse.setAccessToken(accessToken);
        vkBotResponse.setVersion(version);
        vkBotResponse.setRandomId(String.valueOf(new Random().nextInt()));
        return vkBotResponse;
    }

    /**
     * Конвертирует VKBotResponse в MultiValueMap<String, String> для отправки x-www-form-urlencoded
     */
    public MultiValueMap<String, String> toFormData(VKBotResponse response) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("user_id", response.getUserId());
        body.add("message", response.getMessage());
        body.add("access_token", response.getAccessToken());
        body.add("v", response.getVersion());
        body.add("random_id", response.getRandomId());
        return body;
    }
}
