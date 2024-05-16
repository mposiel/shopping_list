package org.example.backendshoppinglist.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "lists")
public class ShoppingList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String shoppingListName;

    @OneToMany(mappedBy = "shoppingList")
    private Set<ShoppingListProduct> shoppingListProducts;

    @OneToMany(mappedBy = "shoppingList")
    private Set<UserShoppingList> users;

    public ShoppingList() {
    }

    public String getShoppingListName() {
        return shoppingListName;
    }

    public void setShoppingListName(String shoppingListName) {
        this.shoppingListName = shoppingListName;
    }

    public Set<ShoppingListProduct> getShoppingListProducts() {
        return shoppingListProducts;
    }

    public void setShoppingListProducts(Set<ShoppingListProduct> shoppingListProducts) {
        this.shoppingListProducts = shoppingListProducts;
    }

    public Set<UserShoppingList> getUsers() {
        return users;
    }

    public void setUsers(Set<UserShoppingList> users) {
        this.users = users;
    }
}