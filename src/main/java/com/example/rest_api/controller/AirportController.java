package com.example.rest_api.controller;

import com.example.rest_api.entity.Airport;
import com.example.rest_api.util.Emissions;
import com.example.rest_api.service.AirportService;
import com.example.rest_api.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class AirportController {

    @Autowired
    public AirportRepository airportRepository;

    @Autowired
    public AirportService airportService;


    @GetMapping("/airport")
    public Collection<String> getAirports(){
        return airportService.getAirportNameList();
    }

    @GetMapping("/calculate/{id1}/{id2}/{roundtrip}")
    public String calculate_emissions(@PathVariable Long id1, @PathVariable Long id2, @PathVariable Boolean roundtrip){
        Emissions emissions = new Emissions();
        Optional<Airport> airport1 = airportRepository.findById(id1);
        Optional<Airport> airport2 = airportRepository.findById(id2);
        return emissions.calculate_emissions(airport1, airport2, roundtrip);

    }

    public AirportController(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public AirportRepository getAirportRepository() {
        return airportRepository;
    }

    public void setAirportRepository(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }
}
