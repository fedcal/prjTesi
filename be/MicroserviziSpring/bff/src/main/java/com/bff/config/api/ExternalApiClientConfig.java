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

    @Primary
    @Bean
    public com.bff.mspaziente.invoker.ApiClient msPazienteApiClient() {
        com.bff.mspaziente.invoker.ApiClient apiClient = new com.bff.mspaziente.invoker.ApiClient(restTemplate);
        apiClient.setBasePath("http://localhost:8085");
        return apiClient;
    }

    @Primary
    @Bean
    public com.bff.msinfermiere.invoker.ApiClient msInfermiereApiClient() {
        com.bff.msinfermiere.invoker.ApiClient apiClient = new com.bff.msinfermiere.invoker.ApiClient(restTemplate);
        apiClient.setBasePath("http://localhost:8086");
        return apiClient;
    }

}
