package org.example.backendshoppinglist.repositories;

import org.example.backendshoppinglist.entities.ShoppingList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingList, Integer> {
}
