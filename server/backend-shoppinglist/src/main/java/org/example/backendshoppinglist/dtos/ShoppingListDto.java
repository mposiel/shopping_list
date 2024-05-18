package org.example.backendshoppinglist.dtos;

public class ShoppingListDto {
    private Integer id;
    private String shoppingListName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShoppingListName() {
        return shoppingListName;
    }

    public void setShoppingListName(String shoppingListName) {
        this.shoppingListName = shoppingListName;
    }
}
