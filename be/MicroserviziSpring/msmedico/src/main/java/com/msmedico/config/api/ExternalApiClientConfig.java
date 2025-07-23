package com.msmedico.config.api;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class ExternalApiClientConfig {
    private final RestTemplate restTemplate;
}
