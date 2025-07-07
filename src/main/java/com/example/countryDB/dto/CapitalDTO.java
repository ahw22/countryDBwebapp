package com.example.countryDB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CapitalDTO {
    private int id;
    private String name;
    private int population;
}
