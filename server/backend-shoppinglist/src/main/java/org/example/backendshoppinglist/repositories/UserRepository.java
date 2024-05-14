package org.example.backendshoppinglist.repositories;

import org.example.backendshoppinglist.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
}
