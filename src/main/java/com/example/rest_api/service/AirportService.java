package com.example.rest_api.service;

import com.example.rest_api.entity.Airport;
import com.example.rest_api.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class AirportService {

    @Autowired
    public AirportRepository airportRepository;

    public Collection<String> getAirportNameList(){

        List<Airport> airportList =  airportRepository.findAll();
        HashMap<Integer, String> airportNameMap = new HashMap<>();

        for (int i=0; i<airportList.size(); i++){
            Airport currentAirport = airportList.get(i);
            airportNameMap.put(i, currentAirport.getName() + "(" + currentAirport.getIataCode() + ")");
        }

        return airportNameMap.values();
    }


}
