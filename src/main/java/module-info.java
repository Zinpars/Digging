module diggingapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens digging to javafx.fxml;
    exports digging;
}
