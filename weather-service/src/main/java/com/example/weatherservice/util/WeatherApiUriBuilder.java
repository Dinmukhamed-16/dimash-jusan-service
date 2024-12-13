package com.example.weatherservice.util;

import org.springframework.web.util.UriComponentsBuilder;

public class WeatherApiUriBuilder {
    private final String baseUrl;
    private final String apiKey;

    public WeatherApiUriBuilder(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public String buildCurrentWeatherUrl(String cityName, String aqi) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/current.json")
                .queryParam("key", apiKey)
                .queryParam("q", cityName)
                .queryParam("aqi", aqi)
                .toUriString();
    }

    public String buildForecastWeatherUrl(String cityName, Integer days, String aqi, String alerts) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/forecast.json")
                .queryParam("key", apiKey)
                .queryParam("q", cityName)
                .queryParam("days", days)
                .queryParam("aqi", aqi)
                .queryParam("alerts", alerts)
                .toUriString();
    }
}
