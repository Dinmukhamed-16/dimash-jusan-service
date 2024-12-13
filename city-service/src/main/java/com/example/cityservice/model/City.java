package com.example.cityservice.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity(name = "city")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "region")
    private String region;

    @Column(name = "country")
    private String country;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lon")
    private Double lon;

    @Column(name = "tz_id")
    private String tzId;

    private boolean isEnabled;

}
