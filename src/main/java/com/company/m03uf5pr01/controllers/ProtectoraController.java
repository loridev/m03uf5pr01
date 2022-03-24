package com.company.m03uf5pr01.controllers;

import com.company.m03uf5pr01.exceptions.NoAnimalsException;
import com.company.m03uf5pr01.models.Animal;
import com.company.m03uf5pr01.models.Au;
import com.company.m03uf5pr01.utils.FXutils;
import com.company.m03uf5pr01.utils.Globals;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.util.Collections;
import java.util.List;

public class ProtectoraController {
    @FXML
    private Button adoptar_btn;
    @FXML
    private ComboBox<String> animals_cb;
    @FXML
    private Button tornar_btn;

    @FXML
    protected void initialize() {
        Collections.sort(Globals.getProtectora());
        for (Animal animal: Globals.getProtectora()) {
            animals_cb.getItems().add(animal.toString());
        }
    }

    @FXML
    protected void adoptar() {
        try {
            if (animals_cb.getValue() == null) {
                throw new NoAnimalsException("Has de seleccionar un animal!");
            }

            int idAnimal = Animal.toStringToId(animals_cb.getValue());
            Globals.getPropietariActual().getMascotes().add(Globals.getProtectora()
                    .get(Globals.getProtectora().indexOf(new Au(idAnimal))));

            Globals.getProtectora().get(Globals.getProtectora()
                    .indexOf(new Au(idAnimal))).setPropietari(Globals.getPropietariActual());

            //Globals.protectora.get(Globals.protectora.indexOf(new Au(idAnimal))).setPropietari(Globals.propietariActual);
            Globals.getProtectora().remove(Globals.getProtectora().get(Globals.getProtectora().indexOf(new Au(idAnimal))));

            FXutils.cambiarEscena("PantallaInicial", adoptar_btn);
        } catch (NoAnimalsException nae) {
            FXutils.crearAlerta(Alert.AlertType.WARNING, adoptar_btn.getScene().getWindow(),
                    "Cap animal seleccionat", nae.getMessage());
        }
    }

    @FXML
    protected void tornar() {
        FXutils.cambiarEscena("PantallaInicial", tornar_btn);
    }
}
