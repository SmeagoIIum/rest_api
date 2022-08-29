package com.example.rest_api.util;

import com.example.rest_api.entity.Airport;
import com.example.rest_api.repository.AirportRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.io.*;
import java.util.List;

@Component
public class MyRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);
    @Autowired
    private AirportRepository airportRepository;

    @Override
    public void run(String... args) throws Exception {

        File file = new File("./src/main/resources/static/airports.csv");

        try (Reader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(FileUtils.readFileToByteArray(file))))) {

            // create csv bean reader
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Airport.class)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // convert `CsvToBean` object to list of Airports
            List<Airport> airports = csvToBean.parse();

            try {
                airportRepository.saveAll(airports);
            } catch (Exception e) {
                logger.info("An error occurred while saving the airport records.");
            }
        } catch (Exception ex) {
            logger.info("An error occurred while processing the CSV file.");
        }
    }


}




