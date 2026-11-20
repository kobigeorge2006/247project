package com.controllers;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.Locale;

public class StormUpdatesController {

    @FXML
    private TextField locationField;

    @FXML
    private TableView<StormEvent> eventsTable;

    @FXML
    private TableColumn<StormEvent, String> statusColumn;

    @FXML
    private TableColumn<StormEvent, String> locationColumn;

    @FXML
    private TableColumn<StormEvent, String> windColumn;

    @FXML
    private TableColumn<StormEvent, String> pathColumn;

    @FXML
    private Label resultsLabel;

    // Sample records from your design.
    private final ObservableList<StormEvent> allEvents =
            FXCollections.observableArrayList(
                    new StormEvent(
                            "active", "Florida, USA", "150", "North"
                    ),
                    new StormEvent(
                            "not-active", "Texas, USA", "120", "West"
                    )
            );

    @FXML
    private void initialize() {
        statusColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().status));

        locationColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().location));

        windColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().windSpeed));

        pathColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().forecastPath));

        eventsTable.setPlaceholder(
                new Label("No events match that location.")
        );

        // Display all events when the screen opens.
        handleViewEvents();
    }

    @FXML
    private void handleViewEvents() {
        String search = locationField.getText()
                .trim()
                .toLowerCase(Locale.ROOT);

        ObservableList<StormEvent> matches =
                FXCollections.observableArrayList();

        for (StormEvent event : allEvents) {
            if (event.location.toLowerCase(Locale.ROOT).contains(search)) {
                matches.add(event);
            }
        }

        eventsTable.setItems(matches);
        resultsLabel.setText(matches.size() + " event(s) found");
    }

    public static class StormEvent {
        private final String status;
        private final String location;
        private final String windSpeed;
        private final String forecastPath;

        public StormEvent(String status, String location,
                          String windSpeed, String forecastPath) {
            this.status = status;
            this.location = location;
            this.windSpeed = windSpeed;
            this.forecastPath = forecastPath;
        }
    }
}
