package com.example.ukdistancecalculator.model;

import java.util.Objects;

/**
 * Represents the result of a distance calculation between two postcodes.
 */
public class DistanceResult {
    private Postcode postcode1;
    private Postcode postcode2;
    private double distance;
    private final String unit = "km"; //fixed as kilometers

    /**
     * Constructs a DistanceResult with the given postcodes and distance.
     * @param postcode1 - The first postcode.
     * @param postcode2 - The second postcode.
     * @param distance - The calculated distance between the postcodes.
     */
    public DistanceResult(Postcode postcode1, Postcode postcode2, double distance) {
        this.postcode1 = postcode1;
        this.postcode2 = postcode2;
        this.distance = distance;
    }

    // Getter for the first postcode
    public Postcode getPostcode1() {
        return postcode1;
    }

    // Setter for the first postcode
    public void setPostcode1(Postcode postcode1) {
        this.postcode1 = postcode1;
    }

    // Getter for the second postcode
    public Postcode getPostcode2() {
        return postcode2;
    }

    // Setter for the second postcode
    public void setPostcode2(Postcode postcode2) {
        this.postcode2 = postcode2;
    }

    // Getter for the distance value
    public double getDistance() {
        return distance;
    }

    // Setter for the distance value
    public void setDistance(double distance) {
        this.distance = distance;
    }

    // Getter for the unit of measurement (always "km")
    public String getUnit() {
        return unit;
    }

    /**
     * Compares this DistanceResult with another object for equality.
     * Two DistanceResult objects are equal if their postcodes, distance, and unit are the same.
     * @param o - The object to compare with.
     * @return boolean - True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistanceResult that = (DistanceResult) o;
        return Double.compare(that.distance, distance) == 0 && Objects.equals(postcode1, that.postcode1) && Objects.equals(postcode2, that.postcode2);
    }

    /**
     * Computes a hash code for this DistanceResult.
     * @return int - The hash code of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(postcode1, postcode2, distance, unit);
    }

    /**
     * Returns a string representation of the DistanceResult.
     * Includes postcodes, distance, and unit.
     * @return String - The string representation of the object.
     */
    @Override
    public String toString() {
        return "DistanceResult{" +
                "postcode1=" + postcode1 +
                ", postcode2=" + postcode2 +
                ", distance=" + distance +
                ", unit='" + unit + '\'' +
                '}';
    }
}
