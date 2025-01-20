package com.example.ukdistancecalculator.service;

import com.example.ukdistancecalculator.model.Postcode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistanceCalculatorTest {

    // Create an instance of the DistanceCalculator service for testing
    private final DistanceCalculator distanceCalculator = new DistanceCalculator();

    /**
     * Test case to validate the calculation of the correct distance between two different postcodes
     */
    @Test
    void calculateDistance_shouldReturnCorrectDistance() {
        // Arrange: Initialize two Postcode objects with known coordinates
        Postcode postcode1 = new Postcode("AB11 6UL", 57.137547, -2.112233);
        Postcode postcode2 = new Postcode("AB11 8RQ", 57.135978, -2.072115);

        // Act: Calculate the distance between the two postcodes using the service method
        double distance = distanceCalculator.calculateDistance(postcode1, postcode2);

        // Assert: Check that the calculated distance matches the expected value within a tolerance range
        double expectedDistance = 2.43; // Correct expected distance
        double tolerance = 0.01; // Allowable difference
        assertEquals(expectedDistance, distance, tolerance, "Distance should be calculated correctly within tolerance");
    }

    /**
     * Test case to verify that the distance between the same postcode returns zero
     */
    @Test
    void calculateDistance_samePostcode_shouldReturnZero() {
        // Arrange: Initialize a Postcode object with a specific location
        Postcode postcode = new Postcode("AB10 1XG", 57.144156, -2.114864);

        // Act: Calculate the distance from the postcode to itself
        double distance = distanceCalculator.calculateDistance(postcode, postcode);

        // Assert: The distance should be zero when both postcodes are the same
        assertEquals(0.0, distance, "Distance between the same postcode should be zero");
    }
}
