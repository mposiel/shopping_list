package org.example.backendshoppinglist.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String productName;
    @Column(nullable = false)
    private String unit;
    @Column(nullable = false)
    private boolean isUnitInteger;
    @OneToMany(mappedBy = "product")
    private Set<ShoppingListProduct> shoppingListProducts;


    public Product() {
    }

    public Product(String productName, String unit, boolean isUnitInteger) {
        this.productName = productName;
        this.unit = unit;
        this.isUnitInteger = isUnitInteger;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isUnitInteger() {
        return isUnitInteger;
    }

    public void setUnitInteger(boolean unitInteger) {
        isUnitInteger = unitInteger;
    }

    public Set<ShoppingListProduct> getShoppingListProducts() {
        return shoppingListProducts;
    }

    public void setShoppingListProducts(Set<ShoppingListProduct> shoppingListProducts) {
        this.shoppingListProducts = shoppingListProducts;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
