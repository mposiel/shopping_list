package org.example.backendshoppinglist.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "list_product")
public class ShoppingListProduct implements Serializable {

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
    private Integer quantity;

    public ShoppingListProduct() {
    }

    public ShoppingListProduct(ShoppingListProductKey id, ShoppingList shoppingList, Product product, int quantity) {
        this.id = id;
        this.shoppingList = shoppingList;
        this.product = product;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}