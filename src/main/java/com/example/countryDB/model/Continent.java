package com.example.countryDB.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Continent")
public class Continent {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String shortName;

    @OneToMany(mappedBy = "continent")
    private List<Country> countries;

}
