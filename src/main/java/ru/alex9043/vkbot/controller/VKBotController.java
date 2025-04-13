package ru.alex9043.vkbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex9043.vkbot.dto.VKBotRequest;
import ru.alex9043.vkbot.service.VKBotService;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class VKBotController {

    private final VKBotService service;

    @PostMapping
    public ResponseEntity<?> vkRequest(@RequestBody VKBotRequest request) {
        return service.vkRequest(request);
    }
}
