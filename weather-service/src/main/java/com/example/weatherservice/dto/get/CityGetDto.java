package com.example.weatherservice.dto.get;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityGetDto {
    private Long id;
    private String name;
}
