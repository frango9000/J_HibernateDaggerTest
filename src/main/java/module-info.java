
module bartest {
    requires com.google.common;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires flogger;
    requires java.logging;
    requires net.bytebuddy;
    requires java.sql;
    requires java.xml.bind;
    requires java.persistence;
    requires jfxtras.controls;

    opens app.control to javafx.fxml;
    opens app.model to javafx.base, org.hibernate.orm.core;

    opens app;

}