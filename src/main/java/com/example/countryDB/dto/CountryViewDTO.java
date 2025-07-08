package com.example.countryDB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Set;

@Data
@Getter
@Builder
@AllArgsConstructor
public class CountryViewDTO {
    private Integer id;
    private String name;
    private String domain;
    private int areaKm2;
    private Long inhabitants;
    private Set<ContinentDTO> continents;
    private String capitalName;
    private int capitalPopulation;
}
