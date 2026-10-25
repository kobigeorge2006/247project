package com.model;

import com.location.Address;
import com.model.other.Shelter;
import com.model.requests.AssistanceRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Creates printable trip information for the Carter family scenario.
 */
public final class TripExporter {

    private TripExporter() {
    }

    /**
     * Writes the Carter family's pickup and shelter directions to a text file.
     *
     * @param outputFile destination for the text file
     * @param request the family's assistance request
     * @param shelter the selected shelter
     * @param volunteerLocation the volunteer's starting address
     * @return the absolute path of the exported file
     * @throws IOException if the trip file cannot be written
     */
    public static Path exportCarterFamilyTrip(Path outputFile,
            AssistanceRequest request, Shelter shelter,
            Address volunteerLocation) throws IOException {
        if (outputFile == null || request == null || shelter == null
                || volunteerLocation == null || request.getAddress() == null) {
            throw new IllegalArgumentException(
                    "Trip export requires a request, shelter, and addresses.");
        }

        String shelterAddress = shelter.getAddress()
                + ", Columbia, SC "
                + shelter.getLocation().getZipCode();
        StringBuilder trip = new StringBuilder();
        trip.append("HURRICANE RELIEF - CARTER FAMILY TRIP\n")
                .append("====================================\n\n")
                .append("Family members\n")
                .append("- John Carter, age 42\n")
                .append("- Mary Carter, age 40\n")
                .append("- Sophie Carter, age 12\n")
                .append("- Buddy (dog)\n\n")
                .append("Contact information\n")
                .append(request.getContactInfo()).append("\n\n")
                .append("Pickup address\n")
                .append(request.getAddress()).append("\n\n")
                .append("Medical note\n")
                .append("Sophie needs medical assistance.\n\n")
                .append("Directions: Rosa to the Carter home\n")
                .append("Start: ").append(volunteerLocation).append("\n")
                .append("Destination: ").append(request.getAddress())
                .append("\n");
        appendDirections(trip, List.of(
                "Head west on Vendue Range toward East Bay Street.",
                "Continue south on East Bay Street, then follow Broad Street "
                        + "west toward the Carter pickup address."));
        trip.append("\nDirections: Carter home to the selected shelter\n")
                .append("Start: ").append(request.getAddress()).append("\n")
                .append("Destination: ").append(shelterAddress).append("\n");
        appendDirections(trip, List.of(
                "Follow the signed evacuation route from Palmetto Harbor Lane "
                        + "to I-26 West.",
                "Continue on I-26 West toward Columbia and follow local signs "
                        + "to the shelter."));
        trip.append("\nSelected shelter\n")
                .append(shelter.getName()).append("\n")
                .append(shelterAddress).append("\n\n")
                .append("Fictional scenario: assume the listed route is safe "
                        + "and passable.\n");

        Path absoluteOutput = outputFile.toAbsolutePath();
        Files.createDirectories(absoluteOutput.getParent());
        Files.writeString(absoluteOutput, trip.toString(),
                StandardCharsets.UTF_8);
        return absoluteOutput;
    }

    private static void appendDirections(StringBuilder trip,
            List<String> directions) {
        for (int i = 0; i < directions.size(); i++) {
            trip.append(i + 1).append(". ")
                    .append(directions.get(i)).append("\n");
        }
    }
}
