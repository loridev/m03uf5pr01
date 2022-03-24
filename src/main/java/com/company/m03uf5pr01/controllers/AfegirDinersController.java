package com.company.m03uf5pr01.controllers;

import com.company.m03uf5pr01.exceptions.InvalidTypeException;
import com.company.m03uf5pr01.exceptions.OutOfRangeException;
import com.company.m03uf5pr01.utils.FXutils;
import com.company.m03uf5pr01.utils.Globals;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AfegirDinersController {
    @FXML
    private Button tornar_btn;
    @FXML
    private TextField diners_input;

    @FXML
    protected void afegirDiners() {
        String regexNum = "^[0-9]+$";

        try {
            if (!diners_input.getText().matches(regexNum)) {
                throw new InvalidTypeException("El text ha de ser enter!");
            }
            int dinersAfegir = Integer.parseInt(diners_input.getText());

            if (dinersAfegir > 5000) {
                throw new OutOfRangeException("El màxim de diners admitit per afegir és de 5000€");
            }
            Globals.getPropietariActual().setDiners(Globals.getPropietariActual().getDiners() + Integer.parseInt(diners_input.getText()));
            tornar();
        } catch (InvalidTypeException ite) {
            FXutils.crearAlerta(Alert.AlertType.ERROR, diners_input.getScene().getWindow(), "Input incorrecte",
                    ite.getMessage());
        } catch (OutOfRangeException oore) {
            FXutils.crearAlerta(Alert.AlertType.ERROR, diners_input.getScene().getWindow(), "Límit excedit!",
                    oore.getMessage());
        }
    }

    @FXML
    protected void tornar() {
        FXutils.cambiarEscena("PantallaInicial", tornar_btn);
    }
}
