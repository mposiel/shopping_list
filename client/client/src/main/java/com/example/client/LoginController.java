package com.example.client;

import com.example.client.Services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessage;

    private final AuthService authService = new AuthService();

    @FXML
    protected void onLoginButtonClick() throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Call the REST API to login the user
        try {
            authService.loginCall(email, password);
            HelloApplication.setRoot("home-view");
        } catch (Exception e) {
            errorMessage.setText("Invalid email or password.");
        }

    }

    @FXML
    protected void onRegisterLinkClick() throws IOException {
        HelloApplication.setRoot("register-view");
    }
}
