module com.relief {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;


    opens com.model to avafx.fxml;
    exports com.model;
}
