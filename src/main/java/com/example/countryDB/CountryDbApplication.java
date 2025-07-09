package com.example.countryDB;

import com.example.countryDB.model.Capital;
import com.example.countryDB.model.Continent;
import com.example.countryDB.model.Country;
import com.example.countryDB.repository.CapitalRepository;
import com.example.countryDB.repository.ContinentRepository;
import com.example.countryDB.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class CountryDbApplication {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ContinentRepository continentRepository;

    @Autowired
    private CapitalRepository capitalRepository;

    public static void main(String[] args) {
        SpringApplication.run(CountryDbApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    private void initDatabaseData() {
        List<Continent> continents = continentRepository.saveAll(List.of(
                Continent.builder()
                        .name("Africa")
                        .shortName("AF")
                        .countries(new HashSet<>())
                        .build(),
                Continent.builder()
                        .name("Antarctica")
                        .shortName("AN")
                        .countries(new HashSet<>())
                        .build(),
                Continent.builder()
                        .name("Europe")
                        .shortName("EU")
                        .countries(new HashSet<>())
                        .build(),
                Continent.builder()
                        .name("North America")
                        .shortName("NA")
                        .countries(new HashSet<>())
                        .build(),
                Continent.builder()
                        .name("Asia")
                        .shortName("AS")
                        .countries(new HashSet<>())
                        .build(),
                Continent.builder()
                        .name("South America")
                        .shortName("SA")
                        .countries(new HashSet<>())
                        .build(),
                Continent.builder()
                        .name("Oceania")
                        .shortName("OC")
                        .countries(new HashSet<>())
                        .build()
        ));

        Continent europe = continentRepository.findByName("Europe");
        Continent northAmerica = continentRepository.findByName("North America");
        Continent asia = continentRepository.findByName("Asia");
        Continent africa = continentRepository.findByName("Africa");

        Country austria = Country.builder()
                .name("Austria")
                .domain("AT")
                .areaKm2(83879)
                .inhabitants(9027999L)
                .continents(new HashSet<>())
                .capital(Capital.builder()
                        .name("Vienna")
                        .population(2028499)
                        .build())
                .build();
        austria.addContinent(europe);

        Country germany = Country.builder()
                .name("Germany")
                .domain("DE")
                .areaKm2(357596)
                .inhabitants(83555478L)
                .continents(new HashSet<>())
                .capital(Capital.builder()
                        .name("Berlin")
                        .population(3596999)
                        .build())
                .build();
        germany.addContinent(europe);

        Country sweden = Country.builder()
                .name("Sweden")
                .domain("SE")
                .areaKm2(450295)
                .inhabitants(10588230L)
                .continents(new HashSet<>())
                .capital(Capital.builder()
                        .name("Stockholm")
                        .population(984748)
                        .build())
                .build();
        sweden.addContinent(europe);

        Country estonia = Country.builder()
                .name("Estonia")
                .domain("EE")
                .areaKm2(45335)
                .inhabitants(1373101L)
                .continents(new HashSet<>())
                .capital(Capital.builder()
                        .name("Tallinn")
                        .population(461602)
                        .build())
                .build();
        estonia.addContinent(europe);

        Country canada = Country.builder()
                .name("Canada")
                .domain("CA")
                .areaKm2(9984670)
                .inhabitants(41528680L)
                .continents(new HashSet<>())
                .capital(Capital.builder()
                        .name("Ottawa")
                        .population(1017449)
                        .build())
                .build();
        canada.addContinent(northAmerica);

        Country russia = Country.builder()
                .name("Russia")
                .domain("RU")
                .areaKm2(17098242)
                .inhabitants(146171015L)
                .continents(new HashSet<>())
                .capital(Capital.builder()
                        .name("Moscow")
                        .population(13010112)
                        .build())
                .build();
        russia.addContinent(europe);
        russia.addContinent(asia);

        Country egypt = Country.builder()
                .name("Egypt")
                .domain("EG")
                .areaKm2(1002450)
                .inhabitants(104258327L)
                .continents(new HashSet<>())
                .capital(Capital.builder()
                        .name("Cairo")
                        .population(10100166)
                        .build())
                .build();
        egypt.addContinent(africa);
        egypt.addContinent(asia);

        countryRepository.saveAll(List.of(
                austria, germany, sweden, estonia, canada, russia, egypt
        ));
    }
}