package com.location;

/**
 * Represents an address together with its geographic coordinates.
 */
public class MapLocation {

    private final Address address;
    private final Coordinates coordinates;

    /**
     * Creates a map location from an address and its coordinates.
     *
     * @param address postal address
     * @param coordinates geographic coordinates for the address
     */
    public MapLocation(Address address, Coordinates coordinates) {
        this.address = address;
        this.coordinates = coordinates;
    }

    /**
     * Returns the postal address.
     *
     * @return this location's address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns the geographic coordinates.
     *
     * @return this location's coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    /**
     * Returns the postal address as a string.
     *
     * @return the formatted address
     */
    @Override
    public String toString() {
        return address.toString();
    }
}