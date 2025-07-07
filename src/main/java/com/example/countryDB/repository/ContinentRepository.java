package com.example.countryDB.repository;

import com.example.countryDB.model.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentRepository extends JpaRepository<Continent, Integer> {
    Continent findByName(String name);
}
