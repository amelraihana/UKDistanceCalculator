package com.example.ukdistancecalculator.util;

import com.example.ukdistancecalculator.model.Postcode;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for loading postcodes from a CSV file.
 * Each postcode includes its latitude and longitude coordinates.
 */
@Component
public class PostcodeLoader {

    /**
     * Loads postcodes from a given CSV file and returns a list of valid Postcode objects.
     *
     * @param filename The path to the CSV file.
     * @return A list of Postcode objects loaded from the file.
     * @throws IOException If there is an issue reading the file.
     */
    public List<Postcode> loadPostcodes(String filename) throws IOException {
        List<Postcode> postcodes = new ArrayList<>();

        // Try-with-resources to ensure the BufferedReader is closed after use
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Parse the CSV file, skipping the header row and ignoring surrounding spaces
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader()
                    .withIgnoreSurroundingSpaces()
                    .parse(reader);

            for (CSVRecord record : records) {
                String postcode = record.get(1); // Column 1: Postcode
                String latitudeStr = record.get(2); // Column 2: Latitude
                String longitudeStr = record.get(3); // Column 3: Longitude

                // Validate latitude and longitude before creating the Postcode object
                if (isValidLatitudeLongitude(latitudeStr, longitudeStr)) {
                    double latitude = Double.parseDouble(latitudeStr);
                    double longitude = Double.parseDouble(longitudeStr);
                    postcodes.add(new Postcode(postcode, latitude, longitude));
                }
                // Skips invalid rows silently without logging
            }
        }

        return postcodes;
    }

    /**
     * Validates that the latitude and longitude strings are non-null, non-empty, and parseable as doubles.
     *
     * @param latitudeStr  The latitude as a string.
     * @param longitudeStr The longitude as a string.
     * @return True if both latitude and longitude are valid; false otherwise.
     */
    private boolean isValidLatitudeLongitude(String latitudeStr, String longitudeStr) {
        if (latitudeStr == null || longitudeStr == null || latitudeStr.isEmpty() || longitudeStr.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(latitudeStr);
            Double.parseDouble(longitudeStr);
        } catch (NumberFormatException e) {
            return false; // Return false if parsing fails
        }
        return true; // Return true if both values are valid doubles
    }
}
