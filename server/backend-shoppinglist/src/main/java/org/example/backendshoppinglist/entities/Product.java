package org.example.backendshoppinglist.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
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
}
