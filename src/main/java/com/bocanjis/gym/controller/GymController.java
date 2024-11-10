package com.bocanjis.gym.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class GymController {

    private final ChatClient chatClient;

    @GetMapping("/ai")
    public ResponseEntity<Flux<String>> prompt(@RequestParam(defaultValue = "Tell me a joke..") String prompt) {
        Flux<String> content = chatClient.prompt()
                .user(prompt)
                .stream()
                .content()
                ;

        return ResponseEntity.ok(content);
    }
}
