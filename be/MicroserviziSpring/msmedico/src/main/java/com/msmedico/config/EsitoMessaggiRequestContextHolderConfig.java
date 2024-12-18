package com.msmedico.config;

import com.msmedico.esito.EsitoMessaggiRequestContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class EsitoMessaggiRequestContextHolderConfig {
    @Bean
    @RequestScope
    public EsitoMessaggiRequestContextHolder esitoMessaggiRequestContextHolder() {
        return new EsitoMessaggiRequestContextHolder();
    }
}
