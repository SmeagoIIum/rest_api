package com.example.rest_api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String iataCode;

    private String name;

    private Double latitudeDeg;

    private Double longitudeDeg;

    private String municipality;

    public Airport() {
    }

    public Airport(String iataCode, String name, Double latitudeDeg, Double longitudeDeg, String municipality) {
        this.iataCode = iataCode;
        this.name = name;
        this.latitudeDeg = latitudeDeg;
        this.longitudeDeg = longitudeDeg;
        this.municipality = municipality;
    }


    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitudeDeg() {
        return latitudeDeg;
    }

    public void setLatitudeDeg(Double latitudeDeg) {
        this.latitudeDeg = latitudeDeg;
    }

    public Double getLongitudeDeg() {
        return longitudeDeg;
    }

    public void setLongitudeDeg(Double longitudeDeg) {
        this.longitudeDeg = longitudeDeg;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Double.compare(airport.latitudeDeg, latitudeDeg) == 0 && Double.compare(airport.longitudeDeg, longitudeDeg) == 0 && Objects.equals(iataCode, airport.iataCode) && Objects.equals(name, airport.name) && Objects.equals(municipality, airport.municipality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iataCode, name, latitudeDeg, longitudeDeg, municipality);
    }
}
