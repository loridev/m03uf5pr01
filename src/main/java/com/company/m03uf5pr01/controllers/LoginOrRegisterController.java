package com.company.m03uf5pr01.controllers;
import com.company.m03uf5pr01.utils.FXutils;
import com.company.m03uf5pr01.utils.Globals;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.nio.file.Paths;

public class LoginOrRegisterController {
    @FXML
    private Button login_btn;

    @FXML
    protected void redirToLogin() {
        FXutils.cambiarEscena("Login", login_btn);
    }

    @FXML
    protected void redirToRegister() {
        FXutils.cambiarEscena("Registre", login_btn);
    }
}