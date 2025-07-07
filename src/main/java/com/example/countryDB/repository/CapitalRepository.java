package com.example.countryDB.repository;

import com.example.countryDB.model.Capital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapitalRepository extends JpaRepository<Capital, Integer> {
    Capital findByName(String name);
}
