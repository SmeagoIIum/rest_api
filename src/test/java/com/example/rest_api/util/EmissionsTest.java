package com.example.rest_api.util;

import com.example.rest_api.entity.Airport;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmissionsTest {

    @Test
    void oneEmptyAirportFormShouldLeadToErrorMessage(){
        Emissions emissions = new Emissions();
        Optional<Airport> airport1 = Optional.empty();
        Optional<Airport> airport2 = Optional.of(new Airport());
        String result = emissions.calculate_emissions(airport1, airport2, false);
        String expected = "Bitte wählen Sie Start- und- Ziel-Flughafen aus der Liste aus.";

        assertEquals(result, expected);
    }

    @Test
    void twoEmptyAirportFormsShouldLeadToErrorMessage(){
        Emissions emissions = new Emissions();
        Optional<Airport> airport1 = Optional.empty();
        Optional<Airport> airport2 = Optional.empty();
        String result = emissions.calculate_emissions(airport1, airport2, false);
        String expected = "Bitte wählen Sie Start- und- Ziel-Flughafen aus der Liste aus.";

        assertEquals(result, expected);
    }

    @Test
    void emissionsCausedByFlightFromCityAToCityBShouldBe_0_1537t(){
        Emissions emissions = new Emissions();

        Double  longitude1= 50.0359;
        Double latitude1 = -5.4253;
        Double  longitude2 = 58.3838;
        Double latitude2 = -3.0412;

        Optional<Airport> airport1 = Optional.of(new Airport("AAA", "CityA International Airport", latitude1, longitude1, "CityA"));
        Optional<Airport> airport2 = Optional.of(new Airport("BBB", "CityB International Airport", latitude2, longitude2, "CityB"));

        String result = emissions.calculate_emissions(airport1, airport2, false);
        String expected =  "Sie haben 0.153710787463525 Tonnen CO² mit dem Flug verursacht, pfui.";
        //distance between points: 968.9 km

        assertEquals(result, expected);

    }

    @Test
    void selectingRoundtripShouldLeadToDoublingOfEmissions(){
        Emissions emissions = new Emissions();


        Double  longitude1= 50.0359;
        Double latitude1 = -5.4253;
        Double  longitude2 = 58.3838;
        Double latitude2 = -3.0412;

        Optional<Airport> airport1 = Optional.of(new Airport("AAA", "CityA International Airport", latitude1, longitude1, "CityA"));
        Optional<Airport> airport2 = Optional.of(new Airport("BBB", "CityB International Airport", latitude2, longitude2, "CityB"));

        String result = emissions.calculate_emissions(airport1, airport2, true);
        String expected =  "Sie haben 0.30742157492705 Tonnen CO² mit dem Flug verursacht, pfui.";
        //distance between points: 968.9 km

        assertEquals(result, expected);

    }

    @Test
    void exceeding16000KmShouldLeadToErrorMessage(){
        Emissions emissions = new Emissions();


        Double  longitude1= 90.0;
        Double latitude1 = 0.0;
        Double  longitude2 = -90.0;
        Double latitude2 = 0.0;

        Optional<Airport> airport1 = Optional.of(new Airport("North pole", "North Pole Airport", latitude1, longitude1, "North pole"));
        Optional<Airport> airport2 = Optional.of(new Airport("South pole", "South Pole Airport", latitude2, longitude2, "South pole"));

        String result = emissions.calculate_emissions(airport1, airport2, true);
        String expected =  "So weit fliegt kein Flugzeug. Bitte wählen Sie eine andere Strecke aus.";

        assertEquals(result, expected);

    }


}