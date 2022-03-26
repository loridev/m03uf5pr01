package com.company.m03uf5pr01;

import com.company.m03uf5pr01.models.Animal;
import com.company.m03uf5pr01.models.Propietari;
import com.company.m03uf5pr01.utils.Globals;
import com.company.m03uf5pr01.utils.JSONutils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.json.Json;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("LoginOrRegister.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Fightnimals");
        stage.setScene(scene);
        stage.setOnHiding(event -> {
            JSONutils.writeOnJSON(Globals.getPropietaris(), Paths.get(Globals.RESOURCES_PATH + "data/propietaris.json"));
            JSONutils.writeOnJSON(Globals.getProtectora(), Paths.get(Globals.RESOURCES_PATH + "data/protectora.json"));
            JSONutils.saveConf(Paths.get(Globals.RESOURCES_PATH + "conf/config.json"));
        });
        Globals.setPropietaris((ArrayList<Propietari>) JSONutils.readFromJSON(Paths.get(Globals.RESOURCES_PATH + "data/propietaris.json"), "Propietari"));
        Globals.setProtectora((LinkedList<Animal>) JSONutils.readFromJSON(Paths.get(Globals.RESOURCES_PATH + "data/protectora.json"), "Animal"));
        JSONutils.loadConf(Paths.get(Globals.RESOURCES_PATH + "conf/config.json"));

        if (Globals.getPropietaris() == null) {
            Globals.setPropietaris(new ArrayList<>());
        }
        if (Globals.getProtectora() == null) {
            Globals.setProtectora(new LinkedList<>());
        }
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}