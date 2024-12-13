package com.example.cityservice.dto.update;

import com.example.cityservice.dto.create.CityCreateDto;
import lombok.Data;

@Data
public class CityUpdateDto extends CityCreateDto {
    private Long id;
}
