package com.location;

/**
 * Represents a geographic point using latitude and longitude.
 */
public class Coordinates {
    private double latitude;
    private double longitude;

    /**
     * Creates coordinates from latitude and longitude values.
     *
     * @param latitude latitude in decimal degrees
     * @param longitude longitude in decimal degrees
     */
    public Coordinates(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns the latitude.
     *
     * @return latitude in decimal degrees
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns the longitude.
     *
     * @return longitude in decimal degrees
     */
    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return latitude + ", " + longitude;
    }
}
