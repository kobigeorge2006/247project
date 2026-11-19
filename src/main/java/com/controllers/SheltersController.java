package com.controllers;

import com.frontend.App;
import com.model.HurricaneReliefSystem;
import com.model.other.Shelter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SheltersController {

    private final HurricaneReliefSystem system = new HurricaneReliefSystem();

    @FXML
    private TextField zipField;

    @FXML
    private Label resultsLabel;

    @FXML
    private VBox shelterResults;

    @FXML
    private void goHome() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void searchShelters() {
        String zipCode = zipField.getText().trim();
        ArrayList<Shelter> shelters = zipCode.isEmpty()
                ? system.getAllShelters()
                : system.findShelters(zipCode);

        shelterResults.getChildren().clear();
        resultsLabel.setText(shelters.size() + " shelter(s) found.");

        for (Shelter shelter : shelters) {
            Label shelterDetails = new Label(
                    shelter.getName() + "\n"
                            + shelter.getAddress() + "\n"
                            + "Capacity: " + shelter.getCapacity() + "\n"
                            + "Status: " + shelter.getStatus());
            shelterDetails.setWrapText(true);
            shelterDetails.setMaxWidth(Double.MAX_VALUE);
            shelterDetails.setStyle(
                    "-fx-background-color: white; -fx-background-radius: 8; "
                            + "-fx-border-color: #DDE3EB; "
                            + "-fx-border-radius: 8; -fx-padding: 16;");
            shelterResults.getChildren().add(shelterDetails);
        }
    }
    @FXML
private void goStormUpdates() throws java.io.IOException {
    com.frontend.App.setRoot("stormupdates");
}
}
