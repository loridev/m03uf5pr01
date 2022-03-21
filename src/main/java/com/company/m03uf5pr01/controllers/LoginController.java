package com.company.m03uf5pr01.controllers;

import com.company.m03uf5pr01.exceptions.UserNotFoundException;
import com.company.m03uf5pr01.models.Propietari;
import com.company.m03uf5pr01.utils.FXutils;
import com.company.m03uf5pr01.utils.Globals;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usr;
    @FXML
    private PasswordField pwd;
    @FXML
    private Button login_btn;
    @FXML
    private Button tornar_btn;

    @FXML
    protected void login() {
        try {
            for (Propietari propietari: Globals.propietaris) {
                if (propietari.getNom().equals(usr.getText()) && propietari.getPassword().equals(pwd.getText())) {
                    Globals.propietariActual = propietari;
                    FXutils.cambiarEscena("PantallaInicial", login_btn);
                    return;
                }
            }

            throw new UserNotFoundException("El nom o la contrasenya introduïts no són correctes!");
        } catch (UserNotFoundException unfe) {
            FXutils.crearAlerta(Alert.AlertType.ERROR, usr.getScene().getWindow(), "Usuari incorrecte",
                    unfe.getMessage());
        }
    }

    @FXML
    protected void tornar() {
        FXutils.cambiarEscena("LoginOrRegister", tornar_btn);
    }

}
