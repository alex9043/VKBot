package ru.alex9043.vkbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VKBotRequest(@JsonProperty("type") String type,
                           @JsonProperty("secret") String secret,
                           @JsonProperty("object") VKBotRequestObject object) {
    public record VKBotRequestObject(@JsonProperty("message") VKBotRequestObjectMessage message) {
        public record VKBotRequestObjectMessage(@JsonProperty("from_id") String fromId,
                                                @JsonProperty("text") String text) {
        }
    }
}
