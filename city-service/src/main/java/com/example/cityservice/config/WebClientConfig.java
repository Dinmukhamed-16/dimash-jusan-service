package com.example.cityservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    private final WeatherServiceConfig weatherServiceConfig;

    public WebClientConfig(WeatherServiceConfig weatherServiceConfig) {
        this.weatherServiceConfig = weatherServiceConfig;
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl(weatherServiceConfig.getUrl()).build();
    }
}
