package com.carlmem.pastebin.communication.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class HashServiceClientImpl implements HashServiceClient {

    private static final String GENERATE_PATH = "/generate";

    private final WebClient hashServiceServiceWebClient;

    @Override
    public String generate() {
        return this.hashServiceServiceWebClient.get()
                .uri(uriBuilder -> uriBuilder.path(GENERATE_PATH)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
