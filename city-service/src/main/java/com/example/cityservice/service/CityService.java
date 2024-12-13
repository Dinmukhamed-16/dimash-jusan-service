package com.example.cityservice.service;

import com.example.cityservice.dto.create.CityCreateDto;
import com.example.cityservice.dto.update.CityUpdateDto;
import com.example.cityservice.model.City;
import com.example.cityservice.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.example.cityservice.service.specification.CitySpecification.byEnabled;
import static com.example.cityservice.util.UtilUrl.ErrorMessages.FORBIDDEN_ERROR;
import static com.example.cityservice.util.UtilUrl.ErrorMessages.SERVICE_UNAVAILABLE_ERROR;


@Service
public class CityService {
    private static final Logger logger = LoggerFactory.getLogger(CityService.class);


    private final WebClient webClient;
    private final CityRepository cityRepository;

    public CityService(WebClient webClient, CityRepository cityRepository) {
        this.webClient = webClient;
        this.cityRepository = cityRepository;
    }

    @Transactional
    public void createCity(CityCreateDto city) {
        City cityCreate = new City();
        setupFields(city, cityCreate);
        cityRepository.save(cityCreate);
    }

    @Transactional
    public void updateCity(CityUpdateDto city) {
        Long id = city.getId();
        City cityUpdate = getCityById(id);
        setupFields(city, cityUpdate);
        cityRepository.save(cityUpdate);
    }

    @Transactional
    public void deleteCityById(Long id) {
        City city = getCityById(id);
        city.setEnabled(false);
        cityRepository.save(city);
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Не найден город с данным ID : " + id));
    }

    @Transactional(readOnly = true)
    public List<City> getAllCitiesByEnabled() {
        return cityRepository.findAll(getSpecificationAllEnabled());
    }

    public String getCurrentWeatherByCityId(Long cityId, String aqi) {
        City city = getCityById(cityId);
        logger.info("Считывание данных погоды на данный момент для города: {}", city.getName());
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/get-current-by-city-id")
                        .queryParam("cityName", city.getName())
                        .queryParam("aqi", aqi)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(data -> {
                    logger.info("Успешно получен прогноз погоды для города: {}", city.getName());
                })
                .doOnError(WebClientResponseException.class, ex -> {
                    logger.error("Ошибка при запросе погоды для города: {}. Код ошибки: {}, Причина: {}", city.getName(), ex.getStatusCode(), ex.getMessage());
                })
                .onErrorResume(WebClientResponseException.class, ex -> {
                    if (ex.getStatusCode() == HttpStatus.FORBIDDEN) {
                        return Mono.just(FORBIDDEN_ERROR);
                    } else if (ex.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                        return Mono.just(SERVICE_UNAVAILABLE_ERROR);
                    }
                    return Mono.error(ex);
                })
                .block();
    }

    public String getForecastWeatherByCityId(Long cityId, Integer daysCnt, String aqi, String alerts) {
        City city = getCityById(cityId);
        logger.info("Считывание прогноза погоды для города: {} на {} дней", city.getName(), daysCnt);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/get-forecast-by-city-id")
                        .queryParam("cityName", city.getName())
                        .queryParam("daysCnt", daysCnt)
                        .queryParam("aqi", aqi)
                        .queryParam("alerts", alerts)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(data -> {
                    logger.info("Успешно получен прогноз погоды для города: {} на {} дней", city.getName(), daysCnt);
                })
                .doOnError(WebClientResponseException.class, ex -> {
                    logger.error("Ошибка при запросе прогноза погоды для города: {} на {} дней. Код ошибки: {}, Причина: {}", city.getName(), daysCnt, ex.getStatusCode(), ex.getMessage());
                })
                .onErrorResume(WebClientResponseException.class, ex -> {
                    if (ex.getStatusCode() == HttpStatus.FORBIDDEN) {
                        return Mono.just(FORBIDDEN_ERROR);
                    } else if (ex.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                        return Mono.just(SERVICE_UNAVAILABLE_ERROR);
                    }
                    return Mono.error(ex);
                })
                .block();
    }

    private void setupFields(CityCreateDto cityCreateDto, City cityCreate) {
        cityCreate.setEnabled(true);
        cityCreate.setName(cityCreateDto.getName());
        cityCreate.setRegion(cityCreateDto.getRegion());
        cityCreate.setCountry(cityCreateDto.getCountry());
        cityCreate.setLat(cityCreateDto.getLat());
        cityCreate.setLon(cityCreateDto.getLon());
        cityCreate.setTzId(cityCreateDto.getTzId());
    }

    private Specification<City> getSpecificationAllEnabled() {
        return byEnabled(true);
    }
}
