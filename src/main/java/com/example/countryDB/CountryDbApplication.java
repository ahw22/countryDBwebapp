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
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

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

    @Profile("develpoment")
    @EventListener(ApplicationReadyEvent.class)
    private void initDatabaseData() {
        List<Continent> continents = continentRepository.saveAll(List.of(
                Continent.builder()
                        .name("Africa")
                        .shortName("AF")
                        .build(),
                Continent.builder()
                        .name("Antarctica")
                        .shortName("AN")
                        .build(),
                Continent.builder()
                        .name("Europe")
                        .shortName("EU")
                        .build(),
                Continent.builder()
                        .name("North America")
                        .shortName("NA")
                        .build(),
                Continent.builder()
                        .name("Asia")
                        .shortName("AS")
                        .build(),
                Continent.builder()
                        .name("South America")
                        .shortName("SA")
                        .build(),
                Continent.builder()
                        .name("Oceania")
                        .shortName("OC")
                        .build()
        ));

        List<Capital> capitals = capitalRepository.saveAll(List.of(
                Capital.builder()
                        .name("Vienna")
                        .population(2028499)
                        .build(),
                Capital.builder()
                        .name("Berlin")
                        .population(3596999)
                        .build(),
                Capital.builder()
                        .name("Stockholm")
                        .population(984748)
                        .build(),
                Capital.builder()
                        .name("Tallinn")
                        .population(461602)
                        .build(),
                Capital.builder()
                        .name("Ottawa")
                        .population(1017449)
                        .build()
        ));

        countryRepository.saveAll(List.of(
                Country.builder()
                        .name("Austria")
                        .domain("AT")
                        .areaKm2(83879)
                        .inhabitants(9027999)
                        .continent(continentRepository.findByName("Europe"))
                        .capital(capitalRepository.findByName("Vienna"))
                        .build(),
                Country.builder()
                        .name("Germany")
                        .domain("DE")
                        .areaKm2(357596)
                        .inhabitants(83555478)
                        .continent(continentRepository.findByName("Europe"))
                        .capital(capitalRepository.findByName("Berlin"))
                        .build(),
                Country.builder()
                        .name("Sweden")
                        .domain("SE")
                        .areaKm2(450295)
                        .inhabitants(10588230)
                        .continent(continentRepository.findByName("Europe"))
                        .capital(capitalRepository.findByName("Stockholm"))
                        .build(),
                Country.builder()
                        .name("Estonia")
                        .domain("EE")
                        .areaKm2(45335)
                        .inhabitants(1373101)
                        .continent(continentRepository.findByName("Europe"))
                        .capital(capitalRepository.findByName("Tallinn"))
                        .build(),
                Country.builder()
                        .name("Canada")
                        .domain("CA")
                        .areaKm2(9984670)
                        .inhabitants(41528680)
                        .continent(continentRepository.findByName("North America"))
                        .capital(capitalRepository.findByName("Ottawa"))
                        .build()
        ));
    }
}
