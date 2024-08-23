package com.carlmem.pastebin.communication.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@WireMockTest
class HashServiceClientImplTest {

    private HashServiceClient hashServiceClient;

    @BeforeEach
    void before(WireMockRuntimeInfo runtimeInfo) {
        this.hashServiceClient = new HashServiceClientImpl(
                WebClient.builder()
                        .baseUrl("http://localhost:" + runtimeInfo.getHttpPort())
                        .build()
        );
    }

    @Test
    void generate() throws JsonProcessingException {
        stubFor(get(urlEqualTo("/generate")));

        var hash = this.hashServiceClient.generate();
        verify(getRequestedFor(urlEqualTo("/generate")));
    }

}