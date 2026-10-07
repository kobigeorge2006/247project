package com.model.other;

//Stores location information.
public class Location {

    private double latitude;
    private double longitude;
    private String streetAddress;
    private String city;
    private String zipCode;

    public Location(double latitude, double longitude, String streetAddress,
                    String city, String zipCode) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipCode = zipCode;
    }

    public double calculateDistance(Location targetLocation) {
        return 0.0;
    }

    public String formateFullAddress() {
        return "";
    }
}