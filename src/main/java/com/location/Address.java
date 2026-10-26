package com.location;


/**
 * Represents a street address with its city, state, and ZIP code.
 */
public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;

    /**
     * Creates an address from its street, city, state, and ZIP code.
     *
     * @param street street address
     * @param city city name
     * @param state state or region
     * @param zip ZIP or postal code
     */
    public Address(String street, String city, String state, String zip){
        this.street = street;
        this. city = city;
        this.state = state;
        this.zip = zip;
    }

    /**
     * Returns the street portion of this address.
     *
     * @return street address
     */
    public String getStreet() {
        return street;
    }

    /**
     * Returns the city for this address.
     *
     * @return city name
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the state or region for this address.
     *
     * @return state or region
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the ZIP or postal code for this address.
     *
     * @return ZIP or postal code
     */
    public String getZip() {
        return zip;
    }

    /**
     * Looks up the geographic coordinates for this address.
     *
     * @return the coordinates, or {@code null} if the lookup fails
     */
    public Coordinates getCoordinates() {
        try {
            return MapTools.getInstance().getCoordinates(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns this address as a formatted string.
     *
     * @return the street, city, state, and ZIP code
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %s %s", street, city, state, zip);
    }
}
