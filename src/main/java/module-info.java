module com.relief {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires javafx.web;

    opens com.frontend to javafx.fxml;
    exports com.frontend;
}
