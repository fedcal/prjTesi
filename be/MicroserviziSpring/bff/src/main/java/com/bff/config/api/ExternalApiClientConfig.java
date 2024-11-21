package com.bff.config.api;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class ExternalApiClientConfig {
    private final RestTemplate restTemplate;

   @Primary
    @Bean
    public com.bff.botoffertebandi.invoker.ApiClient botOfferteBandiApiClient() {
        com.bff.botoffertebandi.invoker.ApiClient apiClient = new com.bff.botoffertebandi.invoker.ApiClient(restTemplate);
        apiClient.setBasePath("http://localhost:8082");
        return apiClient;
    }

    @Primary
    @Bean
    public com.bff.botai.invoker.ApiClient botAiApiClient() {
        com.bff.botai.invoker.ApiClient apiClient = new com.bff.botai.invoker.ApiClient(restTemplate);
        apiClient.setBasePath("http://localhost:8083");
        return apiClient;
    }

    @Primary
    @Bean
    public com.bff.filemanagement.invoker.ApiClient botFileManagementApiClient() {
        com.bff.filemanagement.invoker.ApiClient apiClient = new com.bff.filemanagement.invoker.ApiClient(restTemplate);
        apiClient.setBasePath("http://localhost:8081");
        return apiClient;
    }

    @Primary
    @Bean
    public com.bff.botalimentazione.invoker.ApiClient botAlimentazioneApiClient() {
        com.bff.botalimentazione.invoker.ApiClient apiClient = new com.bff.botalimentazione.invoker.ApiClient(restTemplate);
        apiClient.setBasePath("http://localhost:8084");
        return apiClient;
    }

}
