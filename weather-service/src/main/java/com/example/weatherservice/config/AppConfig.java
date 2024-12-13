package com.example.weatherservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.example.weatherservice.util.WeatherApiUriBuilder;

@Configuration
public class AppConfig {

    @Value("${api.weatherapi.url}")
    private String weatherApiBaseUrl;

    @Value("${api.weatherapi.key}")
    private String weatherApiKey;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Backend Weather Rest API")
                        .version("1.0")
                        .description("Backend REST API for Weather Service"));
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WeatherApiUriBuilder weatherApiUriBuilder() {
        return new WeatherApiUriBuilder(weatherApiBaseUrl, weatherApiKey);
    }

}
