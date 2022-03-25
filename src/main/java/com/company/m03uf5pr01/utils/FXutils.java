package com.company.m03uf5pr01.utils;

import com.company.m03uf5pr01.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;

public final class FXutils {
    public static void cambiarEscena(String nom, Node root) {
        try {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setTitle(nom);
            FXMLLoader loader = new FXMLLoader(App.class.getResource(nom + ".fxml"));
            Scene scene = new Scene(loader.load(), 640, 480);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void crearAlerta(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static Image getPngImage(String name) {
        return new Image(new File(name + ".png").toURI().toString());
    }
}
