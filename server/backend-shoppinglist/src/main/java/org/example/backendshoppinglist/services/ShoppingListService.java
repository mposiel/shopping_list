package org.example.backendshoppinglist.services;

import org.example.backendshoppinglist.converters.ShoppingListDtoConverter;
import org.example.backendshoppinglist.dtos.ShoppingListDto;
import org.example.backendshoppinglist.entities.*;
import org.example.backendshoppinglist.repositories.ShoppingListRepository;
import org.example.backendshoppinglist.repositories.UserShoppingListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListDtoConverter shoppingListDtoConverter;
    private final UserShoppingListRepository userShoppingListRepository;
    private final UserService userService;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, ShoppingListDtoConverter shoppingListDtoConverter, UserShoppingListRepository userShoppingListRepository, UserService userService) {
        this.shoppingListRepository = shoppingListRepository;
        this.shoppingListDtoConverter = shoppingListDtoConverter;
        this.userShoppingListRepository = userShoppingListRepository;
        this.userService = userService;
    }

    public ShoppingListDto addList(ShoppingListDto shoppingListDto) {

        Optional<User> user = userService.getLoggedInUser();
        if(user.isPresent()) {
            ShoppingList shoppingList = shoppingListDtoConverter.convertToEntity(shoppingListDto);
            shoppingList = shoppingListRepository.save(shoppingList);

            UserShoppingList userShoppingList = new UserShoppingList();
            userShoppingList.setUser(user.get());
            userShoppingList.setShoppingList(shoppingList);
            userShoppingList.setRole(ListRole.OWNER);
            userShoppingList.setId(new UserShoppingListKey(user.get().getId(), shoppingList.getId()));

            userShoppingListRepository.save(userShoppingList);

            return shoppingListDtoConverter.convertToDto(shoppingList);
        } else {
            throw new IllegalArgumentException("User not found");
        }

    }

    public List<ShoppingListDto> getAllLists() {
        Optional<User> user = userService.getLoggedInUser();
        if(user.isPresent()) {
            Optional<List<UserShoppingList>> userShoppingLists = userShoppingListRepository.findAllByUser(user.get());

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

    public ShoppingListDto getList(Integer id) {
        Optional<User> user = userService.getLoggedInUser();
        if(user.isPresent()) {
            Optional<UserShoppingList> userShoppingList = userShoppingListRepository.findById(new UserShoppingListKey(id, user.get().getId()));
            if(userShoppingList.isPresent()) {
                return shoppingListDtoConverter.convertToDto(userShoppingList.get().getShoppingList());
            } else {
                throw new IllegalArgumentException("Shopping list not found");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }




}
