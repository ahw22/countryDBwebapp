package com.example.countryDB.service;

import com.example.countryDB.dto.CountryViewDTO;
import com.example.countryDB.model.Country;
import com.example.countryDB.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    @Transactional(readOnly = true)
    public List<CountryViewDTO> getAllCountries() {
        return countryRepository.findAllWithJoins().stream()
                .map(this::toViewDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CountryViewDTO getCountryViewById(int id) {
        Country country = countryRepository.findByIdWithJoins(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));
        return toViewDTO(country);
    }

    private CountryViewDTO toViewDTO(Country country) {
        return CountryViewDTO.builder()
                .name(country.getName())
                .domain(country.getDomain())
                .areaKm2(country.getAreaKm2())
                .inhabitants(country.getInhabitants())
                .continentName(country.getContinent().getName())
                .continentShortName(country.getContinent().getShortName())
                .capitalName(country.getCapital().getName())
                .capitalPopulation(country.getCapital().getPopulation())
                .build();
    }
}
