package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onRegisterButtonClick() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // TODO: Call the REST API to register the user
        HelloApplication.setRoot("login-view");

    }
}