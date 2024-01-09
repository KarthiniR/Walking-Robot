module com.project.walkingrobot {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;

    opens com.project.walkingrobot to javafx.fxml;
    exports com.project.walkingrobot;
}