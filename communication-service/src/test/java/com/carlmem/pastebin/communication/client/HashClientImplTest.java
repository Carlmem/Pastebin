package com.carlmem.pastebin.communication.client;

import com.carlmem.pastebin.communication.util.WebConstants;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@WireMockTest
class HashClientImplTest {

    private HashClient hashClient;

    @BeforeEach
    void before(WireMockRuntimeInfo runtimeInfo) {
        this.hashClient = new HashClientImpl(
                WebClient.builder()
                        .baseUrl("http://localhost:" + runtimeInfo.getHttpPort())
                        .build()
        );
    }

    @Test
    void generate() {
        stubFor(get(urlEqualTo(WebConstants.API + "hashes")));

        var hash = this.hashClient.generate();
        verify(getRequestedFor(urlEqualTo(WebConstants.API + "hashes")));
    }

}