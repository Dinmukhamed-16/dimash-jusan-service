package com.example.cityservice.controller;

import com.example.cityservice.dto.create.CityCreateDto;
import com.example.cityservice.dto.update.CityUpdateDto;
import com.example.cityservice.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @Operation(summary = "Create City")
    @PostMapping("/create")
    public ResponseEntity<?> createCity(@Valid @RequestBody CityCreateDto cityCreateDto) {
        cityService.createCity(cityCreateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update City")
    @PostMapping("/update")
    public ResponseEntity<?> createCity(@Valid @RequestBody CityUpdateDto cityUpdateDto) {
        cityService.updateCity(cityUpdateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete City By Id")
    @PutMapping("/delete-by-id")
    public ResponseEntity<?> deleteCityById(@RequestParam(value = "cityId") Long cityId) {
        cityService.deleteCityById(cityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get City By Id")
    @GetMapping("/get-by-id")
    public ResponseEntity<?> getCityById(@RequestParam(value = "cityId") Long cityId) {
        return new ResponseEntity<>(cityService.getCityById(cityId), HttpStatus.OK);
    }

    @Operation(summary = "Get All Enabled Cities")
    @GetMapping("/get-all-enabled")
    public ResponseEntity<?> getAllCitiesByEnabled() {
        return new ResponseEntity<>(cityService.getAllCitiesByEnabled(), HttpStatus.OK);
    }

    @Operation(summary = "Get Weather By City ID")
    @GetMapping("/get-current-weather-by-city-id")
    public ResponseEntity<?> getCurrentWeatherByCityId(@RequestParam(value = "cityId") Long cityId,
                                                       @RequestParam(value = "aqi") String aqi) {
        return new ResponseEntity<>(cityService.getCurrentWeatherByCityId(cityId, aqi), HttpStatus.OK);
    }

    @Operation(summary = "Get Forecast Weather By City ID")
    @GetMapping("/get-forecast-weather-by-city-id")
    public ResponseEntity<?> getForecastWeatherByCityId(@RequestParam(value = "cityId") Long cityId,
                                                       @RequestParam(value = "daysCnt") Integer daysCnt,
                                                       @RequestParam(value = "aqi") String aqi,
                                                       @RequestParam(value = "alerts") String alerts) {
        return new ResponseEntity<>(cityService.getForecastWeatherByCityId(cityId, daysCnt, aqi, alerts), HttpStatus.OK);
    }

}
