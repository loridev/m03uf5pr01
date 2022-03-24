package com.company.m03uf5pr01.controllers;

import com.company.m03uf5pr01.models.Animal;
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
    @FXML
    private Label torn_lbl;

    private String classeDesafiant;
    private String classeDesafiat;
    private Animal tornActual;

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

        tornActual = Globals.getAnimalDesafiant();

        passarTorn();

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
            case "Mamifer" -> {
                exitos = ((Mamifer) Globals.getAnimalDesafiant()).copDePuny(Globals.getAnimalDesafiat());
                if (!Globals.getAnimalDesafiant().getTipus().toString().equals("CANGUR")) {
                    FXutils.crearAlerta(Alert.AlertType.WARNING, nom1_lbl.getScene().getWindow(),
                            "L'animal s'ha ferit", "Al fer un cop de puny, l'animal s'ha ferit");
                }
            }
            case "Reptil" -> exitos = ((Reptil) Globals.getAnimalDesafiant()).mossegar(Globals.getAnimalDesafiat());
        }

        if (exitos) {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom1_lbl.getScene().getWindow(),
                    "Atac exitós", Globals.getAnimalDesafiant().getNom() + " ha atacat " +
                            "satisfactòriament a " + Globals.getAnimalDesafiat().getNom());
        } else {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom2_lbl.getScene().getWindow(),
                    "Atac fallat", "L'atac ha fallat");
        }

        checkVeri();

        vida1_lbl.setText("VIDA: " + Globals.getAnimalDesafiant().getVida());
        vida2_lbl.setText("VIDA: " + Globals.getAnimalDesafiat().getVida());

        if (detectarFinal()) {
            Animal guanyador = guanyador()[0];
            Animal perdedor = guanyador()[1];
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom1_lbl.getScene().getWindow(),
                    "TENIM GUANYADOR", guanyador.getNom() + " ha derrotat a " + perdedor.getNom() +
                    " i, per tant, pujarà de nivell i el propietari " + guanyador.getPropietari().getNom() + " ha guanyat "
                            + Globals.getDinersApostats() + "€");

            perdedor.getPropietari().getMascotes().remove(perdedor);
            guanyador.getPropietari().setDiners(guanyador.getPropietari().getDiners() + Globals.getDinersApostats());
            guanyador.pujarNivell();

            FXutils.cambiarEscena("PantallaInicial", nom1_lbl);
        } else {
            passarTorn();
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
                    "Atac exitós", Globals.getAnimalDesafiant().getNom() + " ha atacat " +
                            "satisfactòriament a " + Globals.getAnimalDesafiat().getNom());
        } else {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom2_lbl.getScene().getWindow(),
                    "Atac fallat", "L'atac ha fallat");
        }

        checkVeri();

        vida1_lbl.setText("VIDA: " + Globals.getAnimalDesafiant().getVida());
        vida2_lbl.setText("VIDA: " + Globals.getAnimalDesafiat().getVida());

        if (detectarFinal()) {
            Animal guanyador = guanyador()[0];
            Animal perdedor = guanyador()[1];
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom1_lbl.getScene().getWindow(),
                    "TENIM GUANYADOR", guanyador.getNom() + " ha derrotat a " + perdedor.getNom() +
                            " i, per tant, pujarà de nivell i el propietari " + guanyador.getPropietari().getNom() + " ha guanyat "
                            + Globals.getDinersApostats() + "€");

            perdedor.getPropietari().getMascotes().remove(perdedor);
            guanyador.getPropietari().setDiners(guanyador.getPropietari().getDiners() + Globals.getDinersApostats());
            guanyador.pujarNivell();

            FXutils.cambiarEscena("PantallaInicial", nom1_lbl);
        } else {
            passarTorn();
        }
    }

    @FXML
    private void desafiatAtac1() {
        boolean exitos = false;
        switch (classeDesafiat) {
            case "Au" -> exitos = ((Au) Globals.getAnimalDesafiat()).picotada(Globals.getAnimalDesafiant());
            case "Mamifer" -> {
                if (!Globals.getAnimalDesafiat().getTipus().toString().equals("CANGUR")) {
                    FXutils.crearAlerta(Alert.AlertType.WARNING, nom1_lbl.getScene().getWindow(),
                            "L'animal s'ha ferit", "Al fer un cop de puny, l'animal s'ha ferit," +
                                    " perdrà 5 de vida");
                }
                exitos = ((Mamifer) Globals.getAnimalDesafiat()).copDePuny(Globals.getAnimalDesafiant());
            }
            case "Reptil" -> exitos = ((Reptil) Globals.getAnimalDesafiat()).mossegar(Globals.getAnimalDesafiant());
        }

        if (exitos) {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom2_lbl.getScene().getWindow(),
                    "Atac exitós", Globals.getAnimalDesafiat().getNom() + " ha atacat " +
                            "satisfactòriament a " + Globals.getAnimalDesafiant().getNom());
        } else {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom2_lbl.getScene().getWindow(),
                    "Atac fallat", "L'atac ha fallat");
        }

        checkVeri();

        vida1_lbl.setText("VIDA: " + Globals.getAnimalDesafiant().getVida());
        vida2_lbl.setText("VIDA: " + Globals.getAnimalDesafiat().getVida());

        if (detectarFinal()) {
            Animal guanyador = guanyador()[0];
            Animal perdedor = guanyador()[1];
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom1_lbl.getScene().getWindow(),
                    "TENIM GUANYADOR", guanyador.getNom() + " ha derrotat a " + perdedor.getNom() +
                            " i, per tant, pujarà de nivell i el propietari " + guanyador.getPropietari().getNom() + " ha guanyat "
                            + Globals.getDinersApostats() + "€");

            perdedor.getPropietari().getMascotes().remove(perdedor);
            guanyador.getPropietari().setDiners(guanyador.getPropietari().getDiners() + Globals.getDinersApostats());
            guanyador.pujarNivell();

            FXutils.cambiarEscena("PantallaInicial", nom1_lbl);
        } else {
            passarTorn();
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
                    "Atac exitós", Globals.getAnimalDesafiat().getNom() + " ha atacat " +
                            "satisfactòriament a " + Globals.getAnimalDesafiant().getNom());
        } else {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom2_lbl.getScene().getWindow(),
                    "Atac fallat", "L'atac ha fallat");
        }

        checkVeri();

        vida1_lbl.setText("VIDA: " + Globals.getAnimalDesafiant().getVida());
        vida2_lbl.setText("VIDA: " + Globals.getAnimalDesafiat().getVida());

        if (detectarFinal()) {
            Animal guanyador = guanyador()[0];
            Animal perdedor = guanyador()[1];
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom1_lbl.getScene().getWindow(),
                    "TENIM GUANYADOR", guanyador.getNom() + " ha derrotat a " + perdedor.getNom() +
                            " i, per tant, pujarà de nivell i el propietari " + guanyador.getPropietari().getNom() + " ha guanyat "
                            + Globals.getDinersApostats() + "€");

            perdedor.getPropietari().getMascotes().remove(perdedor);
            guanyador.getPropietari().setDiners(guanyador.getPropietari().getDiners() + Globals.getDinersApostats());
            guanyador.pujarNivell();

            if (perdedor instanceof Au) {
                guanyador.setDefensa(guanyador.getDefensa() + perdedor.getAtac() / 6);
            } else if (perdedor instanceof Mamifer) {
                if (perdedor.getTipus().toString().equals("PORC")) {
                    guanyador.setDefensa(guanyador.getDefensa() + perdedor.getAtac() / 4);
                } else {
                    guanyador.setDefensa(guanyador.getDefensa() + perdedor.getAtac() / 8);
                }
            } else if (perdedor instanceof Reptil) {
                guanyador.setDefensa(guanyador.getDefensa() + perdedor.getAtac() / 10);
            }

            FXutils.cambiarEscena("PantallaInicial", nom1_lbl);
        } else {
            passarTorn();
        }
    }

    private boolean detectarFinal() {
        return Globals.getAnimalDesafiant().getVida() <= 0 || Globals.getAnimalDesafiat().getVida() <= 0;
    }

    private Animal[] guanyador() {
        if (Globals.getAnimalDesafiant().getVida() <= 0) {
            return new Animal[]{Globals.getAnimalDesafiat(), Globals.getAnimalDesafiant()};
        }
        return new Animal[]{Globals.getAnimalDesafiant(), Globals.getAnimalDesafiat()};
    }

    private void passarTorn() {
        torn_lbl.setText("Li toca atacar a " + tornActual.getNom());
        if (tornActual == Globals.getAnimalDesafiant()) {
            tornActual = Globals.getAnimalDesafiat();
            hab21_btn.setDisable(true);
            hab22_btn.setDisable(true);
            hab11_btn.setDisable(false);
            hab12_btn.setDisable(false);
        } else {
            tornActual = Globals.getAnimalDesafiant();
            hab21_btn.setDisable(false);
            hab22_btn.setDisable(false);
            hab11_btn.setDisable(true);
            hab12_btn.setDisable(true);
        }
    }

    private void checkVeri() {
        if (Globals.getAnimalDesafiat().isEnverinat()) {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom1_lbl.getScene().getWindow(),
                    "ENVERINAT", Globals.getAnimalDesafiat().getNom() + " ha sigut enverinat, per tant " +
                            "perdrà 5 de vida");
            Globals.getAnimalDesafiat().setVida(Globals.getAnimalDesafiat().getVida() - 5);
        }
        if (Globals.getAnimalDesafiant().isEnverinat()) {
            FXutils.crearAlerta(Alert.AlertType.INFORMATION, nom1_lbl.getScene().getWindow(),
                    "ENVERINAT", Globals.getAnimalDesafiant().getNom() + " ha sigut enverinat, per tant " +
                            "perdrà 5 de vida");
            Globals.getAnimalDesafiant().setVida(Globals.getAnimalDesafiant().getVida() - 5);
        }
    }

}
