package com.company.m03uf5pr01.controllers;

import com.company.m03uf5pr01.exceptions.ExistingUserException;
import com.company.m03uf5pr01.exceptions.InvalidUsernameException;
import com.company.m03uf5pr01.models.Propietari;
import com.company.m03uf5pr01.utils.FXutils;
import com.company.m03uf5pr01.utils.Globals;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegisterController {
    @FXML
    private Button register_btn;
    @FXML
    private TextField usr;
    @FXML
    private PasswordField pwd;
    @FXML
    private Button tornar_btn;

    @FXML
    protected void register() {
        try {
            if (usr.getText().contains(" ")) {
                throw new InvalidUsernameException("El nom d'usuari no pot contenir espais!");
            }
            Propietari propietariPerCrear = new Propietari(usr.getText(), pwd.getText());

            if (Globals.getPropietaris().contains(propietariPerCrear)) {
                throw new ExistingUserException("L'usuari amb el nom " + propietariPerCrear.getNom() + " ja existeix!");
            }

            Globals.getPropietaris().add(propietariPerCrear);

            Globals.setPropietariActual(propietariPerCrear);

            FXutils.cambiarEscena("PantallaInicial", register_btn);
        } catch (InvalidUsernameException iue) {
            FXutils.crearAlerta(Alert.AlertType.ERROR, usr.getScene().getWindow(), "Usuari invàlid",
                    iue.getMessage());
        } catch (ExistingUserException eue) {
            FXutils.crearAlerta(Alert.AlertType.ERROR, usr.getScene().getWindow(), "Usuari ja existent",
                    eue.getMessage());
        }
    }

    @FXML
    protected void tornar() {
        FXutils.cambiarEscena("LoginOrRegister", tornar_btn);
    }
}
