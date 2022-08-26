package com.example.rest_api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.example.rest_api.entity.Airport;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


//source: https://www.pixeltrice.com/import-the-csv-file-into-mysql-database-using-spring-boot-application/
public class CSVHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERs = { "id", "iata_code", "name", "latitude_deg", "longitude_deg", "municipality" };

    public static List<Airport> csvToAirports(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Airport> airportList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Airport airport = new Airport(
                        csvRecord.get("iata_code"),
                        csvRecord.get("name"),
                        Double.parseDouble(csvRecord.get("latitude_deg")),
                        Double.parseDouble(csvRecord.get("longitude_deg")),
                        csvRecord.get("municipality")
                );

                airportList.add(airport);
            }
            return airportList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }


}
