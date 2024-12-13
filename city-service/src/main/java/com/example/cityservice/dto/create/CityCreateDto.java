package com.example.cityservice.dto.create;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityCreateDto implements Serializable {
    private String name;
    private String region;
    private String country;
    private Double lat;
    private Double lon;
    private String tzId;
}
