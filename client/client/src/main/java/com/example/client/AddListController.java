package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import java.io.IOException;

public class AddListController {
    @FXML
    private VBox productsToBuy;
    @FXML
    private VBox addedProducts;

    @FXML
    protected void initialize() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setColor(Color.GRAY);

        productsToBuy.setEffect(dropShadow);
        addedProducts.setEffect(dropShadow);

        HBox.setHgrow(productsToBuy, Priority.ALWAYS);
        HBox.setHgrow(addedProducts, Priority.ALWAYS);
    }
    @FXML
    protected void onBackButtonClick() throws IOException {
        HelloApplication.setRoot("home-view");
    }

    @FXML
    protected void onConfirmButtonClick() throws IOException {
        // Add your confirmation logic here...

        // For example, navigate back to the home page
        HelloApplication.setRoot("home-view");
    }}