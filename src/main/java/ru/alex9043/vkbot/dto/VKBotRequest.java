package ru.alex9043.vkbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VKBotRequest(@JsonProperty("type") String type, @JsonProperty("secret") String secret) {
}
