package com.example.ukdistancecalculator.model;

/**
 * Represents a postcode with its corresponding latitude and longitude coordinates.
 */
public class Postcode {
    private String postcode;
    private double latitude;
    private double longitude;


    /**
     * Constructs a Postcode object with the specified values.
     * @param postcode - The postcode string.
     * @param latitude - The latitude coordinate of the postcode.
     * @param longitude - The longitude coordinate of the postcode.
     */
    public Postcode(String postcode, double latitude, double longitude) {
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getter for the the postcode value
    public String getPostcode() {
        return postcode;
    }

    // Setter for the postcode value
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    // Getter for the latitude coordinate
    public double getLatitude() {
        return latitude;
    }

    // Setter for the latitude coordinate
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    // Getter for the longitude coordinate
    public double getLongitude() {
        return longitude;
    }

    // Setter for the longitude coordinate
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
