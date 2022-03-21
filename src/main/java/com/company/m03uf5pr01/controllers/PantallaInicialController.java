package com.company.m03uf5pr01.controllers;

import com.company.m03uf5pr01.exceptions.NoAnimalsException;
import com.company.m03uf5pr01.utils.FXutils;
import com.company.m03uf5pr01.utils.Globals;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PantallaInicialController {
    @FXML
    private Button sortir_btn;
    @FXML
    private Label diners;
    @FXML
    private Label usuari;
    @FXML
    private Button protectora_btn;
    @FXML
    private Button afegir_btn;
    @FXML
    private Label dia;

    @FXML
    protected void initialize() {
        usuari.setText(Globals.propietariActual.getNom());
        diners.setText(Globals.propietariActual.getDiners() + "€");
        dia.setText("DIA " + Globals.dia);

    }

    @FXML
    protected void logout() {
        Globals.propietariActual = null;
        FXutils.cambiarEscena("LoginOrRegister", sortir_btn);
    }

    @FXML
    protected void redirToProtectora() {
        try {
            if (Globals.protectora == null || Globals.protectora.size() == 0) {
                throw new NoAnimalsException("No hi ha animals a la protectora, ves al dia següent per carregar" +
                        " més animals!");
            }
            FXutils.cambiarEscena("protectora", protectora_btn);
        } catch (NoAnimalsException nae) {
            FXutils.crearAlerta(Alert.AlertType.WARNING, protectora_btn.getScene().getWindow(), "Protectora Buida",
                    nae.getMessage());
        }
    }

    @FXML
    protected void redirToAfegirDiners() {
        FXutils.cambiarEscena("AfegirDiners", afegir_btn);
    }
}
