package org.example.backendshoppinglist.services;

import org.example.backendshoppinglist.converters.ShoppingListDtoConverter;
import org.example.backendshoppinglist.dtos.ShoppingListDto;
import org.example.backendshoppinglist.entities.*;
import org.example.backendshoppinglist.repositories.ShoppingListRepository;
import org.example.backendshoppinglist.repositories.UserShoppingListRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListDtoConverter shoppingListDtoConverter;
    private final UserShoppingListRepository userShoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, ShoppingListDtoConverter shoppingListDtoConverter, UserShoppingListRepository userShoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.shoppingListDtoConverter = shoppingListDtoConverter;
        this.userShoppingListRepository = userShoppingListRepository;
    }

    public ShoppingListDto addList(ShoppingListDto shoppingListDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User user) {
            // Create a new ShoppingList entity from the provided ShoppingListDto
            ShoppingList shoppingList = shoppingListDtoConverter.convertToEntity(shoppingListDto);
            shoppingList = shoppingListRepository.save(shoppingList);

            // Create a new UserShoppingList entity
            UserShoppingList userShoppingList = new UserShoppingList();
            userShoppingList.setUser(user);
            userShoppingList.setShoppingList(shoppingList);
            userShoppingList.setRole(ListRole.OWNER);
            userShoppingList.setId(new UserShoppingListKey(user.getId(), shoppingList.getId()));

            // Save the new UserShoppingList entity
            userShoppingListRepository.save(userShoppingList);

            // Return the ShoppingListDto of the newly created shopping list
            return shoppingListDtoConverter.convertToDto(shoppingList);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public List<ShoppingListDto> getAllLists() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;

            Optional<List<UserShoppingList>> userShoppingLists = userShoppingListRepository.findAllByUser(user);

            if (userShoppingLists.isPresent()) {
                List<ShoppingListDto> shoppingListDtos = new ArrayList<>();
                userShoppingLists.get().forEach(
                        userShoppingList -> shoppingListDtos.add(shoppingListDtoConverter.convertToDto(userShoppingList.getShoppingList()))
                );

                return shoppingListDtos;
            } else {
                return new ArrayList<>();
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
