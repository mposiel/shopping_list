package org.example.backendshoppinglist.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ShoppingListProductKey implements Serializable {
    @Column(name = "shopping_list_id")
    private Integer listId;
    @Column(name = "product_id")
    private Integer productId;

    public ShoppingListProductKey() {
    }

    public ShoppingListProductKey(Integer shoppingListId, Integer productId) {
        this.listId = shoppingListId;
        this.productId = productId;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer shoppingListId) {
        this.listId = shoppingListId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingListProductKey that = (ShoppingListProductKey) o;

        if (listId != null ? !listId.equals(that.listId) : that.listId != null) return false;
        return productId != null ? productId.equals(that.productId) : that.productId == null;
    }

    @Override
    public int hashCode() {
        int result = listId != null ? listId.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        return result;
    }
}
