package com.location;

import java.util.ArrayList;

/**
 * Represents a driving route between two map locations and its route points.
 */
public class DrivingRoute {

    private final MapLocation startingLocation;
    private final MapLocation endingLocation;
    private final ArrayList<Coordinates> routePoints;

    /**
     * Creates a driving route with its endpoints and geographic path points.
     *
     * @param startingLocation route origin
     * @param endingLocation route destination
     * @param routePoints ordered coordinates along the route
     */
    public DrivingRoute(
            MapLocation startingLocation,
            MapLocation endingLocation,
            ArrayList<Coordinates> routePoints
    ) {
        this.startingLocation = startingLocation;
        this.endingLocation = endingLocation;
        this.routePoints = routePoints;
    }

    /**
     * Returns the starting location.
     *
     * @return route origin
     */
    public MapLocation getStartingLocation() {
        return startingLocation;
    }

    /**
     * Returns the ending location.
     *
     * @return route destination
     */
    public MapLocation getEndingLocation() {
        return endingLocation;
    }

    /**
     * Returns the geographic points that describe the route.
     *
     * @return ordered route coordinates
     */
    public ArrayList<Coordinates> getRoutePoints() {
        return routePoints;
    }
}