package com.example.countryDB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ContinentDTO {
    private int id;
    private String name;
    private String shortName;
}
