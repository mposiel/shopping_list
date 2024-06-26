package org.example.backendshoppinglist.dtos;

public class ProductDto {
    private Integer id;
    private String productName;
    private String unit;
    private boolean isUnitInteger;

    public ProductDto(Integer id, String productName, String unit, boolean isUnitInteger) {
        this.id = id;
        this.productName = productName;
        this.unit = unit;
        this.isUnitInteger = isUnitInteger;
    }

    public ProductDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
