package com.example.countryDB.repository;

import com.example.countryDB.model.Capital;
import com.example.countryDB.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country findByName(String name);
}
