package com.example.ukdistancecalculator.service;

import com.example.ukdistancecalculator.model.Postcode;
import org.springframework.stereotype.Service;

/**
 * Service class for calculating the distance between two postcodes using the Haversine formula.
 */
@Service
public class DistanceCalculator {

    private static final double EARTH_RADIUS = 6371; // Earth's radius in kilometers

    /**
     * Calculates the distance between two postcodes using the Haversine formula.
     *
     * @param postcode1 - The first postcode object containing latitude and longitude.
     * @param postcode2 - The second postcode object containing latitude and longitude.
     * @return double - The distance between the two postcodes in kilometers, rounded to two decimal places.
     */
    public double calculateDistance(Postcode postcode1, Postcode postcode2) {
        // Convert longitude and latitude from degrees to radians
        double lon1Radians = Math.toRadians(postcode1.getLongitude());
        double lon2Radians = Math.toRadians(postcode2.getLongitude());
        double lat1Radians = Math.toRadians(postcode1.getLatitude());
        double lat2Radians = Math.toRadians(postcode2.getLatitude());

        // Apply the Haversine formula
        double a = haversine(lat1Radians, lat2Radians) +
                Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate and return the distance, rounded to two decimal places
        return Math.round((EARTH_RADIUS * c) * 100.0) / 100.0;
    }

    /**
     * Helper method to compute the Haversine value for a given angle difference.
     *
     * @param deg1 - The first angle in radians.
     * @param deg2 - The second angle in radians.
     * @return double - The Haversine value for the given angle difference.
     */
    private double haversine(double deg1, double deg2) {
        return square(Math.sin((deg1 - deg2) / 2.0));
    }

    /**
     * Helper method to compute the square of a number.
     *
     * @param x - The input number.
     * @return double - The square of the input number.
     */
    private double square(double x) {
        return x * x;
    }
}
