package ru.alex9043.vkbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VKBotRequest {
    @JsonProperty("type")
    private String type;
    @JsonProperty("secret")
    private String secret;
    @JsonProperty("object")
    private VKBotRequestObject object;

    @Getter
    @ToString
    public static class VKBotRequestObject {
        @JsonProperty("message")
        private VKBotRequestObjectMessage message;

        @Getter
        @ToString
        public static class VKBotRequestObjectMessage {
            @JsonProperty("from_id")
            private String fromId;
            @JsonProperty("text")
            private String text;

            public void setText(String text) {
                this.text = text;
            }
        }
    }
}
