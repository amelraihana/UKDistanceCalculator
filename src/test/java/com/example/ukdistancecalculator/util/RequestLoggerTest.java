package com.example.ukdistancecalculator.util;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RequestLoggerTest {

    /**
     * Test case to verify that the logRequest method is called correctly with the expected parameters
     */
    @Test
    void testLogRequest() {
        // Given: Create a mock instance of the RequestLogger class
        RequestLogger requestLogger = mock(RequestLogger.class);

        // When: Call the logRequest method with specific postcode values
        requestLogger.logRequest("AB10 1XG", "AB11 5BQ");

        // Then: Verify that the logRequest method was called exactly once with the specified postcodes
        verify(requestLogger, times(1)).logRequest("AB10 1XG", "AB11 5BQ");
    }
}
