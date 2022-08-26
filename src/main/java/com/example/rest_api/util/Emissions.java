package com.example.rest_api.util;

import com.example.rest_api.entity.Airport;
import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import java.math.BigDecimal;
import java.util.Optional;


public class Emissions {

    public String calculate_emissions(Optional<Airport> airport1, Optional<Airport> airport2, boolean isRoundtripFLight){
        Apfloat emissions = new Apfloat(0);

        Apfloat factor = new Apfloat(0.158);
        Apfloat distance = new Apfloat(0);
        Apfloat detour = new Apfloat(10);

        if(airport1.isPresent() &  airport2.isPresent()) {
            distance = calculate_big_circle_distance_in_km(airport1.get().getLatitudeDeg(), airport1.get().getLongitudeDeg(),
                    airport2.get().getLatitudeDeg(), airport2.get().getLongitudeDeg());
            //  Maximaldistanz überprüfen (Aufgabe 4)
            if (distance.compareTo(new Apfloat(16000))>0 ){
                return "So weit fliegt kein Flugzeug. Bitte wählen Sie eine andere Strecke aus.";
            }
            // Roundtrip flight überprüfen (Aufgabe 3)
            if(isRoundtripFLight){
                emissions = factor.multiply(distance.add(detour)).divide(new Apfloat(1000))
                        .multiply(new Apfloat(2));
            }else{
                emissions = factor.multiply(distance.add(detour)).divide(new Apfloat(1000));
            }
            return String.format("Sie haben %s Tonnen CO² mit dem Flug verursacht, pfui.",
                    new BigDecimal(String.valueOf(emissions)).toPlainString());
        }
        else{
            return "Bitte wählen Sie Start- und- Ziel-Flughafen aus der Liste aus.";
        }
    }



    //Quelle https://www.movable-type.co.uk/scripts/latlong.html
    Apfloat calculate_big_circle_distance_in_km(double lat1, double lon1, double lat2, double lon2) {
        Apfloat radius_of_the_earth = new Apfloat(6371); // Radius of the earth in km
        Apfloat diff_latitude = deg2rad(new Apfloat(lat2-lat1));// deg2rad below
        Apfloat diff_longitude = deg2rad(new Apfloat(lon2-lon1));
        Apfloat a = ApfloatMath.sin(diff_latitude.divide(new Apfloat(2)))
                .multiply(ApfloatMath.sin(diff_latitude.divide(new Apfloat(2)))).add(
                ApfloatMath.cos(deg2rad(new Apfloat(lat1))).multiply(ApfloatMath.cos(deg2rad(new Apfloat(lat2))))
                        .multiply(ApfloatMath.sin(diff_longitude.divide(new Apfloat(2)))
                        .multiply(ApfloatMath.sin(diff_longitude.divide(new Apfloat(2))))));
        Apfloat c = new Apfloat(2)
                .multiply(ApfloatMath.atan2(ApfloatMath.sqrt(a), ApfloatMath.sqrt(new Apfloat(1).subtract(a))));
        return radius_of_the_earth.multiply(c);
    }

    Apfloat deg2rad(Apfloat deg) {
        return deg.multiply(ApfloatMath.pi(10000).divide(new Apfloat(180)));
    }


}
