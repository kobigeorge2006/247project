package com.controllers;

import java.io.IOException;

import com.frontend.App;

import javafx.fxml.FXML;


/**
 * Handles navigation actions from the primary view.
 */
public class HomeController {

    @FXML
    private void goToShelters() throws IOException {
        App.setRoot("NearByShelters");
    }
}
