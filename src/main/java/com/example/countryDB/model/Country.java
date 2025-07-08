package com.example.countryDB.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Country")
public class Country {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String domain;
    private int areaKm2;
    private Long inhabitants;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "country_continents",
            joinColumns = @JoinColumn(name = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "continent_id")
    )
    @Builder.Default
    private Set<Continent> continents = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "capital_id", nullable = false)
    private Capital capital;

    public void addContinent(Continent continent) {
        continents.add(continent);
    }

    public void removeContinent(Continent continent) {
        continents.remove(continent);
    }

    public void removeAllContinents() {
        continents.clear();
    }

    @PreRemove
    private void preRemove() {
        this.continents.clear();
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", domain='" + domain + '\'' +
                ", areaKm2=" + areaKm2 +
                ", inhabitants=" + inhabitants +
                '}';
    }
}
