package org.example.backendshoppinglist.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "list_product")
public class ShoppingListProduct implements Serializable{

    @EmbeddedId
    private ShoppingListProductKey id;

    @ManyToOne
    @MapsId("listId")
    @JoinColumn(name = "list_id")
    private ShoppingList shoppingList;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int quantity;

    public ShoppingListProduct() {
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}