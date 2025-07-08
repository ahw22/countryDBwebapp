package com.example.countryDB.service;

import com.example.countryDB.dto.ContinentDTO;
import com.example.countryDB.dto.CountryFormDTO;
import com.example.countryDB.dto.CountryViewDTO;
import com.example.countryDB.model.Capital;
import com.example.countryDB.model.Continent;
import com.example.countryDB.model.Country;
import com.example.countryDB.repository.CapitalRepository;
import com.example.countryDB.repository.ContinentRepository;
import com.example.countryDB.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    ContinentRepository continentRepository;

    @Autowired
    CapitalRepository capitalRepository;

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

    @Transactional
    public void save(CountryFormDTO dto) {
        // Fetch all continents by IDs
        Set<Continent> continents = new HashSet<>();
        if (dto.getContinentIds() != null && !dto.getContinentIds().isEmpty()) {
            for (Integer continentId : dto.getContinentIds()) {
                Continent continent = continentRepository.findById(continentId)
                        .orElseThrow(() -> new RuntimeException("Continent not found: " + continentId));
                continents.add(continent);
            }
        }

        Capital capital = Capital.builder()
                .name(dto.getCapitalName())
                .population(dto.getCapitalPopulation())
                .build();

        capital = capitalRepository.save(capital);

        Country country;

        if (dto.getId() != null) {
            country = countryRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Country not found"));

            country.getContinents().clear();

            country.setName(dto.getName());
            country.setDomain(dto.getDomain());
            country.setAreaKm2(dto.getAreaKm2());
            country.setInhabitants(dto.getInhabitants());
            country.setCapital(capital);

            for (Continent continent : continents) {
                country.addContinent(continent);
            }
        } else {
            country = Country.builder()
                    .name(dto.getName())
                    .domain(dto.getDomain())
                    .areaKm2(dto.getAreaKm2())
                    .inhabitants(dto.getInhabitants())
                    .capital(capital)
                    .continents(new HashSet<>())
                    .build();

            for (Continent continent : continents) {
                country.addContinent(continent);
            }
        }

        countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(int id) {
        try {
            // First, find the country
            Optional<Country> countryOpt = countryRepository.findById(id);
            if (countryOpt.isPresent()) {
                Country country = countryOpt.get();

                // Clear continent associations first
                country.removeAllContinents();

                // Save to persist the cleared associations
                countryRepository.saveAndFlush(country);

                // Now delete the country (capital will be deleted automatically due to cascade)
                countryRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete country with id: " + id, e);
        }
    }

    public CountryFormDTO getEditFormById(int id) {
        Country country = countryRepository.findByIdWithJoins(id).get();

        Set<Integer> continentIds = country.getContinents().stream()
                .map(Continent::getId)
                .collect(Collectors.toSet());

        return CountryFormDTO.builder()
                .id(country.getId())
                .name(country.getName())
                .domain(country.getDomain())
                .areaKm2(country.getAreaKm2())
                .inhabitants(country.getInhabitants())
                .continentIds(continentIds)
                .capitalName(country.getCapital().getName())
                .capitalPopulation(country.getCapital().getPopulation())
                .build();
    }

    private CountryViewDTO toViewDTO(Country country) {
        Set<ContinentDTO> continentDTOs = country.getContinents().stream()
                .map(continent -> ContinentDTO.builder()
                        .id(continent.getId())
                        .name(continent.getName())
                        .shortName(continent.getShortName())
                        .build())
                .collect(Collectors.toSet());

        return CountryViewDTO.builder()
                .id(country.getId())
                .name(country.getName())
                .domain(country.getDomain())
                .areaKm2(country.getAreaKm2())
                .inhabitants(country.getInhabitants())
                .continents(continentDTOs)
                .capitalName(country.getCapital().getName())
                .capitalPopulation(country.getCapital().getPopulation())
                .build();
    }
}