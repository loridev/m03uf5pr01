package com.company.m03uf5pr01.controllers;

import com.company.m03uf5pr01.exceptions.NoAnimalsException;
import com.company.m03uf5pr01.exceptions.NotEnoughMoneyException;
import com.company.m03uf5pr01.exceptions.NothingToFixException;
import com.company.m03uf5pr01.models.Animal;
import com.company.m03uf5pr01.models.Au;
import com.company.m03uf5pr01.utils.FXutils;
import com.company.m03uf5pr01.utils.Globals;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class MenuMascotesController {
    @FXML
    private ComboBox<String> mascotes_cb;
    @FXML
    private Button tornar_btn;
    @FXML
    private Button curar_btn;
    @FXML
    private Button sacrificar_btn;
    @FXML
    private Button veri_btn;

    @FXML
    private void initialize() {
        for (Animal mascota: Globals.getPropietariActual().getMascotes()) {
            mascotes_cb.getItems().add(mascota.toString());
        }
    }

    @FXML
    private void curar() {
        try {
            if (mascotes_cb.getValue() == null) {
                throw new NoAnimalsException("Has de seleccionar un animal!");
            }
            if (Globals.getPropietariActual().getDiners() < 50) {
                throw new NotEnoughMoneyException("No tens suficients diners per curar l'animal!");
            }
            Globals.getPropietariActual().setDiners(Globals.getPropietariActual().getDiners() - 50);
            int idAnimal = Integer.parseInt(mascotes_cb.getValue().split("(\\ )(\\|)(\\ )")[0]);
            Animal animalSeleccionat = Globals.getPropietariActual().getMascotes().get(Globals.getPropietariActual().getMascotes()
                    .indexOf(new Au(idAnimal)));
            if (animalSeleccionat.getVida() == 100) {
                throw new NothingToFixException("L'animal ja té tota la vida!");
            }
            animalSeleccionat.setVida(100);

            FXutils.crearAlerta(Alert.AlertType.INFORMATION, curar_btn.getScene().getWindow(),
                    "ANIMAL CURAT", "L'animal ha sigut curat!");

            FXutils.cambiarEscena("PantallaInicial", curar_btn);
        } catch (NoAnimalsException nae) {
            FXutils.crearAlerta(Alert.AlertType.WARNING, curar_btn.getScene().getWindow(),
                    "Cap mascota  seleccionada", nae.getMessage());
        } catch (NotEnoughMoneyException neme) {
            FXutils.crearAlerta(Alert.AlertType.WARNING, curar_btn.getScene().getWindow(),
                    "Diners insuficients", neme.getMessage());
        } catch (NothingToFixException ntfe) {
            FXutils.crearAlerta(Alert.AlertType.WARNING, curar_btn.getScene().getWindow(),
                    "No s'ha de fer res", ntfe.getMessage());
        }
    }

    @FXML
    private void sacrificar() {
        try {
            if (mascotes_cb.getValue() == null) {
                throw new NoAnimalsException("Has de seleccionar un animal!");
            }
            Globals.getPropietariActual().setDiners(Globals.getPropietariActual().getDiners() - 50);
            int idAnimal = Animal.toStringToId(mascotes_cb.getValue());
            Globals.getPropietariActual().getMascotes().remove(new Au(idAnimal));

            FXutils.crearAlerta(Alert.AlertType.INFORMATION, sacrificar_btn.getScene().getWindow(),
                    "ANIMAL SACRIFICAT", "L'animal ha sigut sacrificat!");
            FXutils.cambiarEscena("PantallaInicial", sacrificar_btn);
        } catch (NoAnimalsException nae) {
            FXutils.crearAlerta(Alert.AlertType.WARNING, sacrificar_btn.getScene().getWindow(),
                    "Cap mascota  seleccionada", nae.getMessage());
        }
    }

    @FXML
    private void treureVeri() {
        try {
            if (mascotes_cb.getValue() == null) {
                throw new NoAnimalsException("Has de seleccionar un animal!");
            }
            if (Globals.getPropietariActual().getDiners() < 25) {
                throw new NotEnoughMoneyException("No tens suficients diners per curar l'animal!");
            }
            Globals.getPropietariActual().setDiners(Globals.getPropietariActual().getDiners() - 25);
            int idAnimal = Integer.parseInt(mascotes_cb.getValue().split("(\\ )(\\|)(\\ )")[0]);
            Animal animalSeleccionat = Globals.getPropietariActual().getMascotes().get(Globals.getPropietariActual().getMascotes()
                    .indexOf(new Au(idAnimal)));
            if (!animalSeleccionat.isEnverinat()) {
                throw new NothingToFixException("L'animal no està enverinat!");
            }
            animalSeleccionat.setEnverinat(false);

            FXutils.crearAlerta(Alert.AlertType.INFORMATION, veri_btn.getScene().getWindow(),
                    "ANIMAL DESENVERINAT", "L'animal ha sigut desenverinat!");

            FXutils.cambiarEscena("PantallaInicial", veri_btn);
        } catch (NoAnimalsException nae) {
            FXutils.crearAlerta(Alert.AlertType.WARNING, veri_btn.getScene().getWindow(),
                    "Cap mascota  seleccionada", nae.getMessage());
        } catch (NotEnoughMoneyException neme) {
            FXutils.crearAlerta(Alert.AlertType.WARNING, veri_btn.getScene().getWindow(),
                    "Diners insuficients", neme.getMessage());
        } catch (NothingToFixException ntfe) {
            FXutils.crearAlerta(Alert.AlertType.WARNING, veri_btn.getScene().getWindow(),
                    "No s'ha de fer res", ntfe.getMessage());
        }
    }

    @FXML
    private void tornar() {
        FXutils.cambiarEscena("PantallaInicial", tornar_btn);
    }
}
