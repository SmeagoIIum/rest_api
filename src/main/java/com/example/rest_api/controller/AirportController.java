package com.example.rest_api.controller;

import com.example.rest_api.entity.Airport;
import com.example.rest_api.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
public class AirportController {

    @Autowired
    public AirportRepository airportRepository;

    @GetMapping("/airports")
    public String findAllAirports(Model model) {

        try {
            List<Airport> airports = airportRepository.findAll();
        // save Airports list on model
        model.addAttribute("airports", airports);
        model.addAttribute("status", true);
        } catch (Exception e) {
            model.addAttribute("message",
                    "An error occurred while retrieving the airport records from the DB.");

        }

        return "airports";
    }



}
