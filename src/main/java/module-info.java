module com.company.m03uf5pr01 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.json;

    opens com.company.m03uf5pr01 to javafx.fxml;
    exports com.company.m03uf5pr01;
    exports com.company.m03uf5pr01.controllers;
    opens com.company.m03uf5pr01.controllers to javafx.fxml;
}