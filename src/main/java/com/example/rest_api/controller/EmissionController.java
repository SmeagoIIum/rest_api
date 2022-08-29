package com.example.rest_api.controller;

import com.example.rest_api.entity.Airport;
import com.example.rest_api.repository.AirportRepository;
import com.example.rest_api.util.Emissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EmissionController {

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    Emissions emissions;


    @GetMapping(value = "/")
    public String populateList(Model model) {
        List<Airport> airports =  airportRepository.findAll();
        model.addAttribute("airports", airports);
        model.addAttribute("status", true);
        return "emissions";
    }


    @PostMapping(value = "/")
    @ResponseBody //indicates that the return type should be written straight to the HTTP response body
                // (and not placed in a Model, or interpreted as a view name)
    public String calculate_emissions(@RequestParam(value = "airport1_id", required = true) String airport1_id,
                                      @RequestParam(value = "airport2_id", required = true) String airport2_id,
                                      @RequestParam(value = "isRoundtrip", defaultValue = "false") Boolean isRoundtrip,  Model model){

        Optional<Airport> airport1 = airportRepository.findById(Long.valueOf(airport1_id));
        Optional<Airport> airport2 = airportRepository.findById(Long.valueOf(airport2_id));
        String emission_message = emissions.calculate_emissions(airport1, airport2, isRoundtrip);
        model.addAttribute("emission_message", emission_message);
        model.addAttribute("status", true);
        return emission_message;
    }

//    @PostMapping(value = "/")
//    @ResponseBody //indicates that the return type should be written straight to the HTTP response body
//    // (and not placed in a Model, or interpreted as a view name)
//    public String calculate_emissions(@ModelAttribute(value = "airport1") Airport airport1,
//                                      @ModelAttribute(value = "airport2") Airport airport2,
//                                      @RequestParam(value = "isRoundtrip", defaultValue = "false") String isRoundtrip,  Model model){
//
//        Boolean isRountripBool = Boolean.valueOf(isRoundtrip);
//        String emission_message = emissions.calculate_emissions(airport1, airport2, isRountripBool);
//        model.addAttribute("emission_message", emission_message);
//        model.addAttribute("status", true);
//        return emission_message;
//    }


}
