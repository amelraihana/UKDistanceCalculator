package com.example.ukdistancecalculator.controller;

import com.example.ukdistancecalculator.model.DistanceResult;
import com.example.ukdistancecalculator.model.Postcode;
import com.example.ukdistancecalculator.service.DistanceCalculator;
import com.example.ukdistancecalculator.util.PostcodeLoader;
import com.example.ukdistancecalculator.util.RequestLogger;
import com.example.ukdistancecalculator.util.UpdatePostcode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DistanceControllerTest {

    // Inject mocks into the DistanceController instance for testing
    @InjectMocks
    private DistanceController distanceController;

    // Mock dependencies of DistanceController
    @Mock
    private PostcodeLoader postcodeLoader;

    @Mock
    private DistanceCalculator distanceCalculator;

    @Mock
    private RequestLogger requestLogger;

    @Mock
    private UpdatePostcode updatePostcode;

    // Postcodes for testing
    private Postcode postcode1;
    private Postcode postcode2;

    /**
     * Setup method to initialize mock objects and test data before each test
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        // Initialize sample postcode data
        postcode1 = new Postcode("AB11 6UL", 57.137547, -2.112233);
        postcode2 = new Postcode("AB11 8RQ", 57.135978, -2.072115);
    }

    /**
     * Test case to validate the distance calculation between two postcodes
     */
    @Test
    void testGetDistance() throws Exception {
        // Prepare the mock list of postcodes
        List<Postcode> postcodes = Arrays.asList(postcode1, postcode2);
        when(postcodeLoader.loadPostcodes(anyString())).thenReturn(postcodes); // Mock postcode loader
        when(distanceCalculator.calculateDistance(postcode1, postcode2)).thenReturn(2.64); // Mock distance calculation

        // Expected result
        DistanceResult expectedResult = new DistanceResult(postcode1, postcode2, 2.64);

        // Perform the actual call to the controller method
        DistanceResult result = distanceController.getDistance("AB11 6UL", "AB11 8RQ");

        // Assert that the expected result matches the actual result
        assertEquals(expectedResult, result);

        // Verify if the request logging is called once
        verify(requestLogger, times(1)).logRequest("AB11 6UL", "AB11 8RQ");
    }

    /**
     * Test case to verify successful postcode coordinate update
     */
    @Test
    void testUpdatePostcodeCoordinates_Success() throws Exception {
        // Mock the updatePostcode service to return true (successful update)
        when(updatePostcode.updatePostcodeCoordinates(anyString(), anyDouble(), anyDouble())).thenReturn(true);

        // Call the controller method
        String response = distanceController.updatePostcodeCoordinates("AB11 6UL", 57.137547, -2.112233);

        // Assert the response message
        assertEquals("Postcode coordinates updated successfully.", response);
    }

    /**
     * Test case to verify postcode coordinate update failure (postcode not found)
     */
    @Test
    void testUpdatePostcodeCoordinates_Failure() throws Exception {
        // Mock the updatePostcode service to return false (postcode not found)
        when(updatePostcode.updatePostcodeCoordinates(anyString(), anyDouble(), anyDouble())).thenReturn(false);

        // Call the controller method
        String response = distanceController.updatePostcodeCoordinates("AB11 6UL", 57.137547, -2.112233);

        // Assert the response message
        assertEquals("Postcode not found.", response);
    }

    /**
     * Test case to verify retrieving coordinates of a valid postcode
     */
    @Test
    void testGetCoordinates() throws Exception {
        // Prepare the mock list of postcodes
        List<Postcode> postcodes = Arrays.asList(postcode1, postcode2);
        when(postcodeLoader.loadPostcodes(anyString())).thenReturn(postcodes); // Mock postcode loader

        // Call the controller method
        Postcode result = distanceController.getCoordinates("AB11 6UL");

        // Assert that the result matches the expected postcode
        assertEquals(postcode1, result);
    }

    /**
     * Test case to verify postcode not found error
     */
    @Test
    void testGetCoordinates_PostcodeNotFound() throws Exception {
        // Prepare the mock list of postcodes
        List<Postcode> postcodes = Arrays.asList(postcode1, postcode2);
        when(postcodeLoader.loadPostcodes(anyString())).thenReturn(postcodes);

        // Assert that an IllegalArgumentException is thrown when a postcode is not found
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            distanceController.getCoordinates("AB11 9ZZ");
        });

        // Assert that the exception message is correct
        assertEquals("Postcode not found", exception.getMessage());
    }
}
