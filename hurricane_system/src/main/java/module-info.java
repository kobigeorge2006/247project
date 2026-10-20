module com.relief {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens com.model.frontend to javafx.fxml;
    exports com.model.frontend;
    exports com.model;
}