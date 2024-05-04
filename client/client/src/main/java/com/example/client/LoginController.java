package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessage;

    @FXML
    protected void onLoginButtonClick() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("admin".equals(username) && "password".equals(password)) {
            // Navigate to the home page
            HelloApplication.setRoot("home-view");
        } else {
            // Display an error message
            errorMessage.setText("Invalid username or password.");
        }
    }

    @FXML
    protected void onRegisterLinkClick() throws IOException {
        HelloApplication.setRoot("register-view");
    }
}

//package com.example.client;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.nio.charset.StandardCharsets;
//
//public class LoginController {
//    @FXML
//    private TextField usernameField;
//    @FXML
//    private PasswordField passwordField;
//    @FXML
//    private Label errorMessage;
//
//    private HttpClient client = HttpClient.newHttpClient();
//
//    @FXML
//    protected void onLoginButtonClick() throws IOException, InterruptedException {
//        String username = usernameField.getText();
//        String password = passwordField.getText();
//
//        JSONObject json = new JSONObject();
//        json.put("username", username);
//        json.put("password", password);
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://localhost:8080/login"))
//                .POST(HttpRequest.BodyPublishers.ofString(json.toString(), StandardCharsets.UTF_8))
//                .header("Content-Type", "application/json")
//                .build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        if (response.statusCode() == 200) {
//            // Navigate to the home page
//            HelloApplication.setRoot("home-view");
//        } else {
//            // Display an error message
//            errorMessage.setText("Invalid username or password.");
//        }
//    }
//
//    @FXML
//    protected void onRegisterLinkClick() throws IOException, InterruptedException {
//        String username = usernameField.getText();
//        String password = passwordField.getText();
//
//        JSONObject json = new JSONObject();
//        json.put("username", username);
//        json.put("password", password);
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://localhost:8080/register"))
//                .POST(HttpRequest.BodyPublishers.ofString(json.toString(), StandardCharsets.UTF_8))
//                .header("Content-Type", "application/json")
//                .build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        if (response.statusCode() == 200) {
//            // Navigate to the login page
//            HelloApplication.setRoot("login-view");
//        } else {
//            // Display an error message
//            errorMessage.setText("Registration failed.");
//        }
//    }
//}