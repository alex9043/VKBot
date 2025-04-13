package ru.alex9043.vkbot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class VKBotController {
    @Value("${vk.callback.api.confirm}")
    private String confirm;

    @PostMapping
    public ResponseEntity<?> vkRequest(@RequestBody String string) {
        log.debug(string);
        return ResponseEntity.ok(confirm);
    }
}
