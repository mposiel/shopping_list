package org.example.backendshoppinglist.repositories;

import org.example.backendshoppinglist.entities.User;
import org.example.backendshoppinglist.entities.UserShoppingList;
import org.example.backendshoppinglist.entities.UserShoppingListKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserShoppingListRepository extends JpaRepository<UserShoppingList, UserShoppingListKey> {
    public Optional<List<UserShoppingList>> findAllByUser(User user);
}
