package com.example.countryDB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CountryViewDTO {
    private String name;
    private String domain;
    private int areaKm2;
    private int inhabitants;
    private String continentName;
    private String capitalName;
    private int capitalPopulation;
}
