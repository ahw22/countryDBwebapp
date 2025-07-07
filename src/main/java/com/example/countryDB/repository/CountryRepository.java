package com.example.countryDB.repository;

import com.example.countryDB.model.Capital;
import com.example.countryDB.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country findByName(String name);

    @Query("SELECT c FROM Country c " +
            "JOIN FETCH c.continent " +
            "JOIN FETCH c.capital")
    List<Country> findAllWithJoins();

    @Query("SELECT c FROM Country c " +
            "JOIN FETCH c.continent " +
            "JOIN FETCH c.capital " +
            "WHERE c.id = :id")
    Optional<Country> findByIdWithJoins(@Param("id") int id);
}
