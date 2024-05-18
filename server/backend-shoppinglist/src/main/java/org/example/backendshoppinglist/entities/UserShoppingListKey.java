package org.example.backendshoppinglist.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.Serializable;

@Embeddable
public class UserShoppingListKey implements Serializable {
    @Column(name = "list_id")
    private Integer listId;

    @Column(name = "user_id")
    private Integer userId;
    public UserShoppingListKey() {
    }

    public UserShoppingListKey(Integer listId, Integer userId) {
        this.listId = listId;
        this.userId = userId;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserShoppingListKey that = (UserShoppingListKey) o;

        if (listId != null ? !listId.equals(that.listId) : that.listId != null) return false;
        return userId != null ? userId.equals(that.userId) : that.userId == null;
    }

    @Override
    public int hashCode() {
        int result = listId != null ? listId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
