package com.model.other;

/**
 * Stores geographic coordinates and address details.
 */
public class Location {

    private double latitude;
    private double longitude;
    private String streetAddress;
    private String city;
    private String zipCode;

    /**
     * Creates a location with coordinates and address details.
     *
     * @param latitude latitude in decimal degrees
     * @param longitude longitude in decimal degrees
     * @param streetAddress street address
     * @param city city
     * @param zipCode ZIP code
     */
    public Location(double latitude, double longitude, String streetAddress,
                    String city, String zipCode) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipCode = zipCode;
    }

    /**
     * Creates a location with only a street address.
     *
     * @param streetAddress street address
     */
    public Location(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    /**
     * Computes a simple coordinate difference from this location to another.
     *
     * @param targetLocation location to compare with
     * @return the sum of the latitude and longitude differences
     */
    public double calculateDistance(Location targetLocation) {
        return latitude - targetLocation.getLatitude() + longitude - targetLocation.getLongitude();
    }
    
    /**
     * Formats the street, city, and ZIP code as a single address.
     *
     * @return the formatted address
     */
    public String formateFullAddress() {
        return streetAddress + ", " + city + ", " + zipCode;
    }

    /**
     * Returns the latitude coordinate.
     *
     * @return latitude in decimal degrees
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Updates the latitude coordinate.
     *
     * @param latitude latitude in decimal degrees
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns the longitude coordinate.
     *
     * @return longitude in decimal degrees
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Updates the longitude coordinate.
     *
     * @param longitude longitude in decimal degrees
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Returns the street address.
     *
     * @return the street address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Updates the street address.
     *
     * @param streetAddress the street address
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Returns the city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Updates the city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the ZIP code.
     *
     * @return the ZIP code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Updates the ZIP code.
     *
     * @param zipCode the ZIP code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
}