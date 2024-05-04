package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class HomeController {
    @FXML
    private VBox yourLists;
    @FXML
    private VBox sharedLists;

    @FXML
    protected void initialize() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setColor(Color.GRAY);

        yourLists.setEffect(dropShadow);
        sharedLists.setEffect(dropShadow);

        HBox.setHgrow(yourLists, Priority.ALWAYS);
        HBox.setHgrow(sharedLists, Priority.ALWAYS);
    }
}