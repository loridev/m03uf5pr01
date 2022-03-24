package com.company.m03uf5pr01.controllers;

import com.company.m03uf5pr01.models.Au;
import com.company.m03uf5pr01.models.Mamifer;
import com.company.m03uf5pr01.models.Reptil;
import com.company.m03uf5pr01.utils.FXutils;
import com.company.m03uf5pr01.utils.Globals;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CombatController {
    @FXML
    private Label nom1_lbl;
    @FXML
    private Label tipus1_lbl;
    @FXML
    private Label vida1_lbl;
    @FXML
    private Button hab11_btn;
    @FXML
    private Button hab12_btn;
    @FXML
    private Label nom2_lbl;
    @FXML
    private Label tipus2_lbl;
    @FXML
    private Label vida2_lbl;
    @FXML
    private Button hab21_btn;
    @FXML
    private Button hab22_btn;

    private String classeDesafiant;
    private String classeDesafiat;

    @FXML
    private void initialize() {
        nom1_lbl.setText(Globals.getAnimalDesafiant().getNom());
        nom2_lbl.setText(Globals.getAnimalDesafiat().getNom());
        tipus1_lbl.setText(Globals.getAnimalDesafiant().getTipus().toString());
        tipus2_lbl.setText(Globals.getAnimalDesafiat().getTipus().toString());
        vida1_lbl.setText("VIDA: " + Globals.getAnimalDesafiant().getVida());
        vida2_lbl.setText("VIDA: " + Globals.getAnimalDesafiat().getVida());

        Globals.getAnimalDesafiant().rugir(Globals.getAnimalDesafiat());
        Globals.getAnimalDesafiat().rugir(Globals.getAnimalDesafiant());

        if (Globals.getAnimalDesafiant() instanceof Au) {
            classeDesafiant = "Au";
            hab11_btn.setText("PICOTADA");
            hab12_btn.setText("ESGARRAPADA");
        } else if (Globals.getAnimalDesafiant() instanceof Mamifer) {
            classeDesafiant = "Mamifer";
            hab11_btn.setText("COP DE PUNY");
            hab12_btn.setText("PATADA");
        } else if (Globals.getAnimalDesafiant() instanceof Reptil) {
            classeDesafiant = "Reptil";
            hab11_btn.setText("MOSSEGADA");
            hab12_btn.setVisible(false);
        }

        if (Globals.getAnimalDesafiat() instanceof Au) {
            classeDesafiat = "Au";
            hab21_btn.setText("PICOTADA");
            hab22_btn.setText("ESGARRAPADA");
        } else if (Globals.getAnimalDesafiat() instanceof Mamifer) {
            classeDesafiat = "Mamifer";
            hab21_btn.setText("COP DE PUNY");
            hab22_btn.setText("PATADA");
        } else if (Globals.getAnimalDesafiat() instanceof Reptil) {
            classeDesafiat = "Reptil";
            hab21_btn.setText("MOSSEGADA");
            hab22_btn.setVisible(false);
        }
    }

    @FXML
    private void desafiantAtac1() {
        boolean exitos = false;
        switch (classeDesafiant) {
            case "Au" -> exitos = ((Au) Globals.getAnimalDesafiant()).picotada(Globals.getAnimalDesafiat());
            case "Mamifer" -> exitos = ((Mamifer) Globals.getAnimalDesafiant()).copDePuny(Globals.getAnimalDesafiat());
            case "Reptil" -> exitos = ((Reptil) Globals.getAnimalDesafiant()).mossegar(Globals.getAnimalDesafiat());
        }

        if (exitos) {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom1_lbl.getScene().getWindow(),
                    "Atac exitós", Globals.getAnimalDesafiant().getNom() + " ha atacat" +
                            "satisfactòriament a " + Globals.getAnimalDesafiat().getNom());
        } else {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom2_lbl.getScene().getWindow(),
                    "Atac fallat", "L'atac ha fallat");
        }

        vida1_lbl.setText("VIDA: " + Globals.getAnimalDesafiant().getVida());
        vida2_lbl.setText("VIDA: " + Globals.getAnimalDesafiat().getVida());

        if (Globals.getAnimalDesafiat().isEnverinat()) {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom1_lbl.getScene().getWindow(),
                    "ENVERINAT", Globals.getAnimalDesafiat().getNom() + " ha sigut enveinat");
        }
    }

    @FXML
    private void desafiantAtac2() {
        boolean exitos = false;
        if (classeDesafiant.equals("Au")) {
            exitos = ((Au) Globals.getAnimalDesafiant()).esgarrapada(Globals.getAnimalDesafiat());
        } else if (classeDesafiant.equals("Mamifer")) {
            exitos = ((Mamifer) Globals.getAnimalDesafiant()).patada(Globals.getAnimalDesafiat());
        }

        if (exitos) {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom1_lbl.getScene().getWindow(),
                    "Atac exitós", Globals.getAnimalDesafiant().getNom() + " ha atacat" +
                            "satisfactòriament a " + Globals.getAnimalDesafiat().getNom());
        } else {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom2_lbl.getScene().getWindow(),
                    "Atac fallat", "L'atac ha fallat");
        }

        vida1_lbl.setText("VIDA: " + Globals.getAnimalDesafiant().getVida());
        vida2_lbl.setText("VIDA: " + Globals.getAnimalDesafiat().getVida());
    }

    @FXML
    private void desafiatAtac1() {
        boolean exitos = false;
        switch (classeDesafiat) {
            case "Au" -> exitos = ((Au) Globals.getAnimalDesafiat()).picotada(Globals.getAnimalDesafiant());
            case "Mamifer" -> exitos = ((Mamifer) Globals.getAnimalDesafiat()).copDePuny(Globals.getAnimalDesafiant());
            case "Reptil" -> exitos = ((Reptil) Globals.getAnimalDesafiat()).mossegar(Globals.getAnimalDesafiant());
        }

        if (exitos) {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom2_lbl.getScene().getWindow(),
                    "Atac exitós", Globals.getAnimalDesafiat().getNom() + " ha atacat" +
                            "satisfactòriament a " + Globals.getAnimalDesafiant().getNom());
        } else {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom2_lbl.getScene().getWindow(),
                    "Atac fallat", "L'atac ha fallat");
        }

        vida1_lbl.setText("VIDA: " + Globals.getAnimalDesafiant().getVida());
        vida2_lbl.setText("VIDA: " + Globals.getAnimalDesafiat().getVida());

        if (Globals.getAnimalDesafiant().isEnverinat()) {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom1_lbl.getScene().getWindow(),
                    "ENVERINAT", Globals.getAnimalDesafiant().getNom() + " ha sigut enveinat");
        }
    }

    @FXML
    private void desafiatAtac2() {
        boolean exitos = false;
        if (classeDesafiat.equals("Au")) {
            exitos = ((Au) Globals.getAnimalDesafiat()).esgarrapada(Globals.getAnimalDesafiant());
        } else if (classeDesafiat.equals("Mamifer")) {
            exitos = ((Mamifer) Globals.getAnimalDesafiat()).patada(Globals.getAnimalDesafiant());
        }

        if (exitos) {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom2_lbl.getScene().getWindow(),
                    "Atac exitós", Globals.getAnimalDesafiat().getNom() + " ha atacat" +
                            "satisfactòriament a " + Globals.getAnimalDesafiant().getNom());
        } else {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom2_lbl.getScene().getWindow(),
                    "Atac fallat", "L'atac ha fallat");
        }

        vida1_lbl.setText("VIDA: " + Globals.getAnimalDesafiant().getVida());
        vida2_lbl.setText("VIDA: " + Globals.getAnimalDesafiat().getVida());
    }

}
