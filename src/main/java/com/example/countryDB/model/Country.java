package com.example.countryDB.model;

import jakarta.persistence.*;
import lombok.*;

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
    private int inhabitants;

    @ManyToOne
    @JoinColumn(name = "continent_id", nullable = false)
    private Continent continent ;

    @OneToOne
    @JoinColumn(name = "capital_id")
    private Capital capital;

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
