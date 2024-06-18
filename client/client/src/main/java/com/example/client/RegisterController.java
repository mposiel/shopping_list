package com.example.client;

import com.example.client.Services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField fullNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessage;

    private final AuthService authService = new AuthService();

    @FXML
    protected void onRegisterButtonClick() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String fullName = fullNameField.getText();

        try {
            String response = authService.registerCall(username, password, fullName);
            if (!response.isEmpty()) {
                HelloApplication.setRoot("login-view");
            } else {
                errorMessage.setText("Registration failed.");
            }
        } catch (Exception e) {
            errorMessage.setText("Invalid email or password.");
        }
    }
}