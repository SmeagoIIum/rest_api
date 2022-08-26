package com.example.rest_api.service;

import java.io.IOException;
import java.util.List;

import com.example.rest_api.entity.Airport;
import com.example.rest_api.util.CSVHelper;
import com.example.rest_api.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {
    @Autowired
    AirportRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Airport> tutorials = CSVHelper.csvToAirports(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<Airport> getAllAirports() {
        return repository.findAll();
    }
}