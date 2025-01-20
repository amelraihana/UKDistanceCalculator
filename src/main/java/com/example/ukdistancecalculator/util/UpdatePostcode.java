package com.example.ukdistancecalculator.util;

import com.example.ukdistancecalculator.model.Postcode;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.List;

@Component
public class UpdatePostcode {

    // Path to the CSV file containing the postcodes data
    private final String filename = "src/main/resources/data/ukpostcodes.csv";

    /**
     * Updates the coordinates(latitude and longitude) for a given postcode.
     *
     * @param postcode The postcode to update
     * @param latitude The new latitude to assign
     * @param longitude The new longitude to assign
     * @return True if updated successfully, false if postcode not found
     * @throws IOException If there's an error reading or writing to the file
     */
    public boolean updatePostcodeCoordinates(String postcode, double latitude, double longitude) throws IOException {
        // Load the list of postcodes from the CSV file
        List<Postcode> postcodes = new PostcodeLoader().loadPostcodes(filename);

        // Find the postcode in the list and update its coordinates
        for (Postcode p : postcodes) {
            if (p.getPostcode().equals(postcode)) {
                p.setLatitude(latitude); // Update latitude
                p.setLongitude(longitude); // Update longitude

                // After updating, write the updated list of postcodes back to the CSV file
                writePostcodesToFile(postcodes);
                return true; // Successfully updated
            }
        }
        return false; // Postcode not found
    }

    /**
     * Writes the updated list of postcodes back to the CSV file.
     *
     * @param postcodes The updated list of postcodes with new coordinates
     * @throws IOException If there's an error writing to the file
     */
    private void writePostcodesToFile(List<Postcode> postcodes) throws IOException {
        // Open the file for writing, using a BufferedWriter and CSVPrinter to write in CSV format
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Index", "Postcode", "Latitude", "Longitude"))) {

            // Write each postcode's data back into the CSV file
            for (int i = 0; i < postcodes.size(); i++) {
                Postcode p = postcodes.get(i);
                // Write the row for each postcode: index, postcode, latitude, and longitude
                csvPrinter.printRecord(i + 1, p.getPostcode(), p.getLatitude(), p.getLongitude());
            }
        }
    }
}
