package com.company.m03uf5pr01.controllers;

import com.company.m03uf5pr01.exceptions.*;
import com.company.m03uf5pr01.models.Animal;
import com.company.m03uf5pr01.models.Au;
import com.company.m03uf5pr01.models.Propietari;
import com.company.m03uf5pr01.utils.FXutils;
import com.company.m03uf5pr01.utils.Globals;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PreparacioBatallaController {
    @FXML
    private ComboBox<String> mascota_cb;
    @FXML
    private ComboBox<String> propietari_cb;
    @FXML
    private ComboBox<String> mascotaop_cb;
    @FXML
    private Button batalla_btn;
    @FXML
    private Button tornar_btn;
    @FXML
    private TextField diners_input;

    @FXML
    protected void initialize() {
        for (Animal mascota : Globals.getPropietariActual().getMascotes()) {
            mascota_cb.getItems().add(mascota.toString());
        }

        for (Propietari propietari : Globals.getPropietaris()) {
            if (!propietari.equals(Globals.getPropietariActual())) {
                propietari_cb.getItems().add(propietari.getNom());
            }
        }
    }

    @FXML
    private void carregarMascotes() {
        try {
            if (propietari_cb.getValue() == null) {
                throw new NoSelectionException("Has de seleccionar un propietari!");
            }
            mascotaop_cb.getItems().clear();
            Propietari seleccionat = Globals.getPropietaris().get(Globals.getPropietaris()
                    .indexOf(new Propietari(propietari_cb.getValue())));

            if (seleccionat.getMascotes().size() == 0) {
                throw new NoAnimalsException("El propietari seleccionat no té mascotes!");
            }

            mascotaop_cb.setDisable(false);
            for (Animal mascota: seleccionat.getMascotes()) {
                mascotaop_cb.getItems().add(mascota.toString());
            }
        } catch (NoSelectionException nse) {
            FXutils.crearAlerta(Alert.AlertType.ERROR, propietari_cb.getScene().getWindow(),
                    "No s'ha seleccionat un propietari", nse.getMessage());
        } catch (NoAnimalsException nae) {
            FXutils.crearAlerta(Alert.AlertType.ERROR, propietari_cb.getScene().getWindow(),
                    "Sense mascotes", nae.getMessage());
            mascotaop_cb.setDisable(true);
        }
    }

    @FXML
    private void redirToCombat() {
        try {
            if (mascota_cb.getValue() == null) {
                throw new NoSelectionException("Has de seleccionar una mascota teva per lluitar!");
            }
            if (propietari_cb.getValue() == null) {
                throw new NoSelectionException("Has de seleccionar un propietari oponent per lluitar!");
            }
            if (mascotaop_cb.getValue() == null) {
                throw new NoSelectionException("Has de seleccionar una mascota oponent per lluitar");
            }
            if (diners_input.getText().equals("")) {
                throw new EmptyInputException("Has d'introduir els diners per apostar");
            }
            String regexNum = "^[0-9]+$";
            if (!diners_input.getText().matches(regexNum)) {
                throw new InvalidTypeException("El text ha de ser enter!");
            }
            int dinersApostats = Integer.parseInt(diners_input.getText());
            if (dinersApostats > Globals.getPropietariActual().getDiners()) {
                throw new NotEnoughMoneyException("Tens menys diners que els que vols apostar!");
            }
            Propietari seleccionat = Globals.getPropietaris().get(Globals.getPropietaris()
                    .indexOf(new Propietari(propietari_cb.getValue())));
            if (dinersApostats > seleccionat.getDiners()) {
                throw new NotEnoughMoneyException("El propietari que vols desafiar té menys diners que els que vols apostar!");
            }

            int idDesafiant = Animal.toStringToId(mascota_cb.getValue());
            int idDesafiat = Animal.toStringToId(mascotaop_cb.getValue());
            Globals.setAnimalDesafiant(Globals.getPropietariActual().getMascotes().get(
                    Globals.getPropietariActual().getMascotes().indexOf(new Au(idDesafiant))
            ));
            Globals.setAnimalDesafiat(seleccionat.getMascotes().get(
                    seleccionat.getMascotes().indexOf(new Au(idDesafiat))
            ));

            Globals.getPropietariActual().setDiners(Globals.getPropietariActual().getDiners() - dinersApostats);
            seleccionat.setDiners(seleccionat.getDiners() - dinersApostats);
            Globals.setDinersApostats(dinersApostats * 2);
            FXutils.cambiarEscena("Combat", batalla_btn);
        } catch (NoSelectionException nse) {
            FXutils.crearAlerta(Alert.AlertType.ERROR, batalla_btn.getScene().getWindow(),
                    "Error a la selecció", nse.getMessage());
        } catch (NotEnoughMoneyException neme) {
            FXutils.crearAlerta(Alert.AlertType.ERROR, batalla_btn.getScene().getWindow(),
                    "Diners insuficients", neme.getMessage());
        } catch (InvalidTypeException ite) {
            FXutils.crearAlerta(Alert.AlertType.ERROR, batalla_btn.getScene().getWindow(),
                    "Text incorrecte", ite.getMessage());
        } catch (EmptyInputException eie) {
            FXutils.crearAlerta(Alert.AlertType.ERROR, batalla_btn.getScene().getWindow(),
                    "Text buit", eie.getMessage());
        }
    }

    @FXML
    private void tornar() {
        FXutils.cambiarEscena("PantallaInicial", tornar_btn);
    }
}
