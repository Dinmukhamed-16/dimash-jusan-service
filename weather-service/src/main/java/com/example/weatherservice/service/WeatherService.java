package com.example.weatherservice.service;

import com.example.weatherservice.util.WeatherApiUriBuilder;
;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    private final WeatherApiUriBuilder uriBuilder;

    public WeatherService(RestTemplate restTemplate, WeatherApiUriBuilder uriBuilder) {
        this.restTemplate = restTemplate;
        this.uriBuilder = uriBuilder;
    }

    public String getCurrentWeatherByCityName(String cityName, String aqi) {
        String url = uriBuilder.buildCurrentWeatherUrl(cityName, aqi);
        return restTemplate.getForObject(url, String.class);
    }

    public String getForecastWeatherByCityNameForSeveralDays(String cityName, Integer daysCnt, String aqi, String alerts) {
        String url = uriBuilder.buildForecastWeatherUrl(cityName, daysCnt, aqi, alerts);
        return restTemplate.getForObject(url, String.class);
    }


}
