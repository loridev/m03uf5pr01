package com.company.m03uf5pr01.controllers;
import com.company.m03uf5pr01.models.Propietari;
import com.company.m03uf5pr01.utils.FXutils;
import com.company.m03uf5pr01.utils.Globals;
import com.company.m03uf5pr01.utils.JSONutils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.nio.file.Paths;

public class LoginOrRegisterController {
    @FXML
    private Button login_btn;

    @FXML
    protected void initialize() {
        /*JsonArray jsonUsers = JSONutils.readFromJSON(Paths.get("usuaris.json"));

        for (JsonValue row: jsonUsers) {
            JsonObject userObject = row.asJsonObject();
            Globals.propietaris.put(userObject.getString("nom"),
                    new Propietari(userObject.getString("nom"), userObject.getString("password")));
        }*/
    }

    @FXML
    protected void redirToLogin() {
        FXutils.cambiarEscena("Login", login_btn);
    }

    @FXML
    protected void redirToRegister() {
        FXutils.cambiarEscena("Registre", login_btn);
    }
}