module com.relief {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.simple;

    opens com.relief to javafx.fxml;
    exports com.relief;
}
