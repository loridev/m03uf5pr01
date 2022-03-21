package com.company.m03uf5pr01;

import com.company.m03uf5pr01.utils.Globals;
import com.company.m03uf5pr01.utils.JSONutils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("LoginOrRegister.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setOnHiding(event -> {
            JSONutils.writeOnJSON(Globals.propietaris, Paths.get("src/main/resources/com/company/m03uf5pr01/data/propietaris.json"));
            JSONutils.writeOnJSON(Globals.protectora, Paths.get("src/main/resources/com/company/m03uf5pr01/data/protectora.json"));
        });
        Globals.propietaris = JSONutils.readFromJSON(Paths.get("src/main/resources/com/company/m03uf5pr01/data/propietaris.json"), "Propietari");
        Globals.protectora = JSONutils.readFromJSON(Paths.get("src/main/resources/com/company/m03uf5pr01/data/protectora.json"), "Animal");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}