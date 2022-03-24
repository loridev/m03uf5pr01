package com.company.m03uf5pr01.controllers;

import com.company.m03uf5pr01.models.Au;
import com.company.m03uf5pr01.models.Mamifer;
import com.company.m03uf5pr01.models.Reptil;
import com.company.m03uf5pr01.utils.Globals;
import javafx.fxml.FXML;
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
    private void initialize() {
        nom1_lbl.setText(Globals.getAnimalDesafiant().getNom());
        nom2_lbl.setText(Globals.getAnimalDesafiat().getNom());
        tipus1_lbl.setText(Globals.getAnimalDesafiant().getTipus().toString());
        tipus2_lbl.setText(Globals.getAnimalDesafiat().getTipus().toString());
        vida1_lbl.setText("VIDA: " + Globals.getAnimalDesafiant().getVida());
        vida2_lbl.setText("VIDA: " + Globals.getAnimalDesafiat().getVida());

        if (Globals.getAnimalDesafiant() instanceof Au) {
            hab11_btn.setText("PICOTADA");
            hab12_btn.setText("ESGARRAPADA");
        } else if (Globals.getAnimalDesafiant() instanceof Mamifer) {
            hab11_btn.setText("COP DE PUNY");
            hab12_btn.setText("PATADA");
        } else if (Globals.getAnimalDesafiant() instanceof Reptil) {
            hab11_btn.setText("MOSSEGADA");
            hab12_btn.setVisible(false);
        }

        if (Globals.getAnimalDesafiat() instanceof Au) {
            hab21_btn.setText("PICOTADA");
            hab22_btn.setText("ESGARRAPADA");
        } else if (Globals.getAnimalDesafiat() instanceof Mamifer) {
            hab21_btn.setText("COP DE PUNY");
            hab22_btn.setText("PATADA");
        } else if (Globals.getAnimalDesafiat() instanceof Reptil) {
            hab21_btn.setText("MOSSEGADA");
            hab22_btn.setVisible(false);
        }
    }

}
