package org.example.backendshoppinglist.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user_list")

public class UserShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private ShoppingList shoppingList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private ListRole role;

    public UserShoppingList() {
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
