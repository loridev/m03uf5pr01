module com.company.m03uf5pr01 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.company.m03uf5pr01 to javafx.fxml;
    exports com.company.m03uf5pr01;
}