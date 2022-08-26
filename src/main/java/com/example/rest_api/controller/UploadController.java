package com.example.rest_api.controller;

import com.example.rest_api.entity.Airport;
import com.example.rest_api.service.CSVService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// source: https://attacomsian.com/blog/spring-boot-upload-parse-csv-file
@Controller
public class UploadController {

    @Autowired
    CSVService csvService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `Airport` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Airport.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();


                // convert `CsvToBean` object to list of Airports
                List<Airport> airports = csvToBean.parse();

                try {
                    csvService.save(file);

                } catch (Exception e) {
                    model.addAttribute("message", "An error occurred while processing the CSV file.");

                }

                //copied from other website



                // save Airports list on model
                model.addAttribute("airports", airports);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }

        return "file-upload-status";
    }
}