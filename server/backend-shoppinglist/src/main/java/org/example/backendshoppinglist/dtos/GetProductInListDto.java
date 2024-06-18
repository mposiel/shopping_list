package org.example.backendshoppinglist.dtos;

public class GetProductInListDto {
    private Integer id;
    private String productName;
    private String unit;
    private boolean isUnitInteger;
    private Integer quantity;

    public GetProductInListDto() {
    }

    public GetProductInListDto(Integer id, String productName, String unit, boolean isUnitInteger, Integer quantity) {
        this.id = id;
        this.productName = productName;
        this.unit = unit;
        this.isUnitInteger = isUnitInteger;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
