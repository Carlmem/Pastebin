package com.carlmem.pastebin.communication.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class HashClientImpl implements HashClient {

    private static final String GENERATE_PATH = "/generate";

    private final WebClient hashClientWebClient;

    @Override
    public String generate() {
        return this.hashClientWebClient.get()
                .uri(uriBuilder -> uriBuilder.path(GENERATE_PATH)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
