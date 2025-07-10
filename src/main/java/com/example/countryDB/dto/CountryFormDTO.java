package com.example.countryDB.dto;

import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CountryFormDTO {
    private Integer id;
    private String name;
    private String domain;
    private int areaKm2;
    private Long inhabitants;
    private Set<Integer> continentIds;
    private String capitalName;
    private int capitalPopulation;

    public Boolean isCountryIsInDB() {
        return id != null;
    }

    public Boolean isContentIdsNotEmpty() {
        return continentIds != null && !continentIds.isEmpty();
    }
}
