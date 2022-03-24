package com.company.m03uf5pr01.controllers;

import com.company.m03uf5pr01.exceptions.NoAnimalsException;
import com.company.m03uf5pr01.models.*;
import com.company.m03uf5pr01.utils.FXutils;
import com.company.m03uf5pr01.utils.Globals;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PantallaInicialController {
    /** Pool de noms */
    private static final String[] POOL_NOMS = {"Drako", "Lucy", "Lua", "Firulais", "Calcetines", "Zarpas", "Rocky",
            "Bruc", "Milhouse", "Espetec", "Fuet", "Macarrones", "Llonganissa", "Elisa", "Sara", "Raimon", "Piruleta",
            "Gala", "Lennon", "Kinder", "Odin", "Israel", "Luke", "Leia", "Dali", "Niebla", "Casper", "Jagger",
            "Maya", "Tequila", "Joy", "Tor", "Tanka", "Rex", "Timon", "Pumba", "Potaje", "Cocido", "Croquetas",
            "Bimbo", "Colacao", "Nesquik", "Margarida", "Petúnia", "Coral", "Camila", "Eustàquia", "Coixí"};
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
    private Button dia_seguent_btn;
    @FXML
    private Button mascotes_btn;
    @FXML
    private Button batalla_btn;

    @FXML
    protected void initialize() {
        /*if (Globals.dia == 0) {
            afegirAnimals();
        }*/
        usuari.setText(Globals.getPropietariActual().getNom());
        diners.setText(Globals.getPropietariActual().getDiners() + "€");
        dia.setText("DIA " + Globals.getDia());

    }

    @FXML
    protected void logout() {
        Globals.setPropietariActual(null);
        FXutils.cambiarEscena("LoginOrRegister", sortir_btn);
    }

    @FXML
    protected void redirToProtectora() {
        try {
            if (Globals.getProtectora() == null || Globals.getProtectora().size() == 0) {
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

    @FXML
    protected void redirToMenuMascotes() {
        try {
            if (Globals.getPropietariActual().getMascotes().size() == 0) {
                throw new NoAnimalsException("No tens cap mascota!");
            }
            FXutils.cambiarEscena("MenuMascotes", mascotes_btn);
        } catch (NoAnimalsException nae) {
            FXutils.crearAlerta(Alert.AlertType.WARNING, mascotes_btn.getScene().getWindow(),
                    "No tens mascotes", nae.getMessage());
        }
    }

    @FXML
    protected void afegirAnimals() {
        Globals.setDia(Globals.getDia() + 1);
        int numAnimalsAfegits = (int) ((Math.random() * 5) + 1);

        for (int i = 0; i < numAnimalsAfegits; i++) {
            Animal animalAfegir;
            int tipusAnimalRandom = (int) (Math.random() * TipusAnimal.values().length);
            int nomRandom = (int) (Math.random() * POOL_NOMS.length);
            float atac = (float) (Math.random() * 60 + 1);
            float defensa = (float) (Math.random() * 25 + 1);
            float precissio = (float) (Math.random() * 75 + 1);

            if (tipusAnimalRandom < 4) {
                float multiplicadorPuny = (float) (Math.random() * 2 + 1);
                animalAfegir = new Mamifer(POOL_NOMS[nomRandom], atac, defensa, precissio,
                        TipusAnimal.values()[tipusAnimalRandom], multiplicadorPuny);
            } else if (tipusAnimalRandom > 7) {
                float precissioVeri = (float) (Math.random() * 60 + 1);
                animalAfegir = new Reptil(POOL_NOMS[nomRandom], atac, defensa, precissio,
                        TipusAnimal.values()[tipusAnimalRandom], precissioVeri);
            } else {
                float ratioRepeticioAtac = (float) (Math.random() * 35 + 1);
                animalAfegir = new Au(POOL_NOMS[nomRandom], atac, defensa, precissio,
                        TipusAnimal.values()[tipusAnimalRandom], ratioRepeticioAtac);
            }
            Globals.getProtectora().addLast(animalAfegir);
        }

        FXutils.crearAlerta(Alert.AlertType.INFORMATION, dia_seguent_btn.getScene().getWindow(),
                "DIA " + Globals.getDia(), "S'ha(n) afegit " + numAnimalsAfegits + " animal(s) més per adoptar," +
                        " hi ha un total de " + (Globals.getProtectora().size()) + " a la protectora!");
        FXutils.cambiarEscena("PantallaInicial", dia_seguent_btn);
    }

    @FXML
    private void redirToPrepa() {
        try {
            if (Globals.getPropietariActual().getMascotes().size() == 0) {
                throw new NoAnimalsException("No tens cap mascota!");
            }
            FXutils.cambiarEscena("PreparacioBatalla", batalla_btn);
        } catch (NoAnimalsException nae) {
            FXutils.crearAlerta(Alert.AlertType.WARNING, mascotes_btn.getScene().getWindow(),
                    "No tens mascotes", nae.getMessage());
        }
    }
}
