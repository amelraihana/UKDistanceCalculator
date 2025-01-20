package com.example.ukdistancecalculator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Utility class for logging requests involving postcodes.
 * Logs the postcodes in a structured JSON format and validates them.
 */
@Component
public class RequestLogger {

    // Logger instance for logging requests
    private static final Logger logger = LoggerFactory.getLogger(RequestLogger.class);

    /**
     * Logs the postcodes from the request in a structured format.
     *
     * @param postcode1 First postcode in the request
     * @param postcode2 Second postcode in the request
     */
    public void logRequest(String postcode1, String postcode2) {
        // Validate postcodes
        boolean isPostcode1Valid = isValidPostcode(postcode1);
        boolean isPostcode2Valid = isValidPostcode(postcode2);

        // Log the request in JSON format
        logger.info("{ \"postcode1\": \"{}\", \"postcode2\": \"{}\", \"postcode1Valid\": {}, \"postcode2Valid\": {} }",
                postcode1, postcode2, isPostcode1Valid, isPostcode2Valid);

        // Additional logging for invalid postcodes
        if (!isPostcode1Valid) {
            logger.warn("Invalid postcode detected: postcode1 = {}", postcode1);
        }
        if (!isPostcode2Valid) {
            logger.warn("Invalid postcode detected: postcode2 = {}", postcode2);
        }
    }

    /**
     * Validates a postcode string. Replace with actual validation logic as needed.
     *
     * @param postcode The postcode to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidPostcode(String postcode) {
        // Simple validation: non-null, non-empty, and matches UK postcode pattern
        return postcode != null && !postcode.isEmpty() && postcode.matches("^[A-Z0-9 ]{5,8}$");
    }
}
