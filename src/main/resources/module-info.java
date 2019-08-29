
module bartest {
    requires com.google.common;
    requires javafx.fxml;
    requires javafx.controls;
    requires flogger;
    requires java.logging;

    opens app;
}