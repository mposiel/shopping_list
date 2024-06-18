package com.example.client;

import com.example.client.Services.HomeService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ListItemController {
    public Integer id;

    @FXML
    private Label itemLabel;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    private HomeService homeService;
    private HomeController homeController; // Add this line

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
        this.homeController.listNeedsRefreshProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                refreshList();
                this.homeController.listNeedsRefreshProperty().set(false);
            }
        });
    }

    public void refreshList() {
        try {
            homeController.refreshView();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    @FXML
    public void initialize() {
        homeService = new HomeService();

        edit.setOnAction(event -> {
            // Handle button 1 click
        });

        delete.setOnAction(event -> {

            try {
                boolean isDeleted = homeService.deleteList(id);
                if (isDeleted) {
                    homeController = HomeController.getInstance();
                    Platform.runLater(homeController::refreshView);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setItemLabel(String text) {
        itemLabel.setText(text);
    }
}