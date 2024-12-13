package com.example.weatherservice.controller;

import com.example.weatherservice.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather/api")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Operation(summary = "Get current weather by city name")
    @GetMapping("/get-current-by-city-id")
    public String getCurrentWeather(@RequestParam(value = "cityName") String cityName,
                                    @RequestParam(value = "aqi") String aqi) {
        return weatherService.getCurrentWeatherByCityName(cityName, aqi);
    }

    @Operation(summary = "Get forecast weather by city name for several days")
    @GetMapping("/get-forecast-by-city-id")
    public String getForecastWeather(@RequestParam(value = "cityName") String cityName,
                                     @RequestParam(value = "daysCnt") Integer daysCnt,
                                     @RequestParam(value = "aqi") String aqi,
                                     @RequestParam(value = "alerts") String alerts) {
        return weatherService.getForecastWeatherByCityNameForSeveralDays(cityName, daysCnt, aqi, alerts);
    }
}
