package com.example.client.entities;

public class ShoppingList {
    private Integer id;
    private String shoppingListName;


    public ShoppingList(String shoppingListName, Integer id) {
        this.shoppingListName = shoppingListName;
        this.id = id;
    }

    public String getShoppingListName() {
        return shoppingListName;
    }

    public void setShoppingListName(String shoppingListName) {
        this.shoppingListName = shoppingListName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return shoppingListName;
    }
}