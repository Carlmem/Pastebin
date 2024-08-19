package com.carlmem.pastebin.communication.configuration.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URL;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "service.hash-service")
public class HashServiceProperties {

    private URL url;

    @Configuration
    static class HashServiceWebClientConfiguration {

        @Bean
        public WebClient hashServiceServiceWebClient(HashServiceProperties properties) {
            return WebClient.builder()
                    .baseUrl(properties.getUrl().toString())
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();
        }
    }
}
