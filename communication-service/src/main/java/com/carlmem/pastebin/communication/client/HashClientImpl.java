package com.carlmem.pastebin.communication.client;

import com.carlmem.pastebin.communication.util.WebConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class HashClientImpl implements HashClient {

    private static final String GENERATE_PATH = "/hashes";

    private final WebClient hashClientWebClient;

    @Override
    public String generate() {
        return this.hashClientWebClient.get()
                .uri(uriBuilder -> uriBuilder.path(WebConstants.API + GENERATE_PATH)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
