package com.example.ukdistancecalculator.controller;

import com.example.ukdistancecalculator.model.DistanceResult;
import com.example.ukdistancecalculator.model.Postcode;
import com.example.ukdistancecalculator.service.DistanceCalculator;
import com.example.ukdistancecalculator.util.PostcodeLoader;
import com.example.ukdistancecalculator.util.RequestLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.ukdistancecalculator.util.UpdatePostcode;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class DistanceController {

    // Injects the PostcodeLoader utility for loading postcode data
    @Autowired
    private PostcodeLoader postcodeLoader;

    // Injects the DistanceCalculator service for distance calculation
    @Autowired
    private DistanceCalculator distanceCalculator;

    // Injects the RequestLogger utility for logging API requests
    @Autowired
    private RequestLogger requestLogger;

    // Injects the UpdatePostcode utility for updating postcode data
    @Autowired
    private UpdatePostcode updatePostcode;

    /**
     * Endpoint to calculate the distance between two postcodes.
     * @param postcode1 - The first postcode.
     * @param postcode2 - The second postcode.
     * @return DistanceResult - Contains postcodes and calculated distance.
     * @throws Exception - If postcodes are invalid or an error occurs.
     */
    @GetMapping("/distance")
    public DistanceResult getDistance(@RequestParam String postcode1, @RequestParam String postcode2) throws Exception {
        // Log the request for aggregation/reporting
        requestLogger.logRequest(postcode1, postcode2);

        // Load postcode data from the CSV file
        String filename = "src/main/resources/data/ukpostcodes.csv";
        List<Postcode> postcodes = postcodeLoader.loadPostcodes(filename);

        // Find the first postcode
        Postcode p1 = postcodes.stream()
                .filter(p -> p.getPostcode().equals(postcode1))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid postcode1"));

        // Find the second postcode
        Postcode p2 = postcodes.stream()
                .filter(p -> p.getPostcode().equals(postcode2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid postcode2"));

        // Calculate the distance between the two postcodes
        double distance = distanceCalculator.calculateDistance(p1, p2);

        // Return the result containing postcodes and distance
        return new DistanceResult(p1, p2, distance);
    }

    /**
     * Endpoint to update the latitude and longitude of a postcode.
     * @param postcode - The postcode to update.
     * @param latitude - New latitude value.
     * @param longitude - New longitude value.
     * @return String - Update status message.
     * @throws Exception - If an error occurs during update.
     */
    @PutMapping("/update-postcode")
    public String updatePostcodeCoordinates(@RequestParam String postcode, @RequestParam double latitude, @RequestParam double longitude) throws Exception {
        // Update the postcode's coordinates using the utility class
        boolean updated = updatePostcode.updatePostcodeCoordinates(postcode, latitude, longitude);
        if (updated) {
            return "Postcode coordinates updated successfully.";
        } else {
            return "Postcode not found.";
        }
    }

    /**
     * Endpoint to retrieve the latitude and longitude of a specific postcode.
     * @param postcode - The postcode to fetch coordinates for.
     * @return Postcode - Contains the postcode and its coordinates.
     * @throws Exception - If the postcode is not found or an error occurs.
     */
    @GetMapping("/get-coordinates")
    public Postcode getCoordinates(@RequestParam String postcode) throws Exception {
        // Log the request for aggregation/reporting
        requestLogger.logRequest(postcode, null);  // Only one postcode is involved

        // Load postcode data from the CSV file
        List<Postcode> postcodes = postcodeLoader.loadPostcodes("src/main/resources/data/ukpostcodes.csv");

        // Find and return the matching postcode with its coordinates
        return postcodes.stream()
                .filter(p -> p.getPostcode().equals(postcode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Postcode not found"));
    }


}
