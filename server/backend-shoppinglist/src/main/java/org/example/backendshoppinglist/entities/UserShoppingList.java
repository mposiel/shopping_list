package org.example.backendshoppinglist.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user_list")

public class UserShoppingList {

    @EmbeddedId

    private UserShoppingListKey id;

    @ManyToOne
    @MapsId("listId")
    @JoinColumn(name = "list_id")
    private ShoppingList shoppingList;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private ListRole role;

    public UserShoppingList() {
    }

    public UserShoppingListKey getId() {
        return id;
    }

    public void setId(UserShoppingListKey id) {
        this.id = id;
    }
    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ListRole getRole() {
        return role;
    }

    public void setRole(ListRole role) {
        this.role = role;
    }


}
