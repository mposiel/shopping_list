package com.example.client;

import com.example.client.Services.HomeService;
import com.example.client.entities.ShoppingList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;

public class HomeController {
    @FXML
    private VBox yourLists;
    @FXML
    private VBox sharedLists;
    @FXML
    private ListView<HBox> list1;

    private HomeService homeService;
    private final BooleanProperty listNeedsRefresh = new SimpleBooleanProperty(false);
    private static HomeController instance;

    public HomeController() {
        instance = this;
    }

    public static HomeController getInstance() {
        return instance;
    }

    public BooleanProperty listNeedsRefreshProperty() {
        return listNeedsRefresh;
    }

    @FXML
    protected void initialize() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setColor(Color.GRAY);

        yourLists.setEffect(dropShadow);
        sharedLists.setEffect(dropShadow);

        HBox.setHgrow(yourLists, Priority.ALWAYS);
        HBox.setHgrow(sharedLists, Priority.ALWAYS);

        homeService = new HomeService();

        try {
            List<ShoppingList> yourListsData = homeService.fetchYourLists();
            // Populate list1 ListView with the shoppingListName property of each ShoppingList object
            for (ShoppingList shoppingList : yourListsData) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ListItem.fxml"));
                    HBox listItem = loader.load();

                    ListItemController controller = loader.getController();
                    controller.setItemLabel(shoppingList.getShoppingListName());
                    controller.setId(shoppingList.getId());
                    controller.setHomeController(this); // Add this line


                    list1.getItems().add(listItem);
                } catch (IOException e) {
                    e.printStackTrace();
                }            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onAddListButtonClick() throws IOException {
        HelloApplication.setRoot("addlist-view");
    }
    public void deleteListItem(Integer id) {
        // Code to delete the list item
        listNeedsRefresh.set(true);
    }

    public void refreshView() {
        list1.getItems().clear();

        try {
            List<ShoppingList> yourListsData = homeService.fetchYourLists();
            for (ShoppingList shoppingList : yourListsData) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ListItem.fxml"));
                    HBox listItem = loader.load();

                    ListItemController controller = loader.getController();
                    controller.setItemLabel(shoppingList.getShoppingListName());
                    controller.setId(shoppingList.getId());

                    list1.getItems().add(listItem);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}