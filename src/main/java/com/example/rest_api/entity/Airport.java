package com.example.rest_api.entity;

import com.opencsv.bean.CsvBindByPosition;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @CsvBindByPosition(position = 0)
    private String iataCode;

    @CsvBindByPosition(position = 1)
    private String name;

    @CsvBindByPosition(position = 2)
    private double latitude;

    @CsvBindByPosition(position = 3)
    private double longitude;

    @CsvBindByPosition(position = 4)
    private String municipality;

    public Airport() {
    }

    public Airport(Long id, String iataCode, String name, double latitude, double longitude, String municipality) {
        this.id = id;
        this.iataCode = iataCode;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.municipality = municipality;
    }

    public Airport(String iataCode, String name, double latitude, double longitude, String municipality) {
        this.iataCode = iataCode;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Double.compare(airport.latitude, latitude) == 0 && Objects.equals(iataCode, airport.iataCode) && Objects.equals(name, airport.name) && Objects.equals(longitude, airport.longitude) && Objects.equals(municipality, airport.municipality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iataCode, name, latitude, longitude, municipality);
    }

//    @Override
//    public String toString() {
//        return "Airport{" +
//                "id=" + id +
//                ", iataCode='" + iataCode + '\'' +
//                ", name='" + name + '\'' +
//                ", latitude=" + latitude +
//                ", longitude=" + longitude +
//                ", municipality='" + municipality + '\'' +
//                '}';
//    }
}
