package com.example.countryDB.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "Continent")
public class Continent {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String shortName;

    @ManyToMany(mappedBy = "continents", fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    private Set<Country> countries = new HashSet<>();

}
