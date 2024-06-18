package org.example.backendshoppinglist.repositories;

import org.example.backendshoppinglist.entities.ShoppingListProduct;
import org.example.backendshoppinglist.entities.ShoppingListProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListProductRepository extends JpaRepository<ShoppingListProduct, ShoppingListProductKey> {
}
