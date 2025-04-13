package ru.alex9043.vkbot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VKBotResponse {
    private String message;
    private String userId;
    private String accessToken;
    private String version;
    private String randomId;
}
