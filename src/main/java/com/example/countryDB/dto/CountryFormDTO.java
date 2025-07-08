package com.example.countryDB.dto;

import com.example.countryDB.model.Continent;
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
}
