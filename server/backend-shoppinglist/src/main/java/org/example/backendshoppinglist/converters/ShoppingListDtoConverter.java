package org.example.backendshoppinglist.converters;

import org.example.backendshoppinglist.dtos.ShoppingListDto;
import org.example.backendshoppinglist.entities.ShoppingList;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListDtoConverter {

    public ShoppingListDto convertToDto(ShoppingList shoppingList) {
        ShoppingListDto shoppingListDto = new ShoppingListDto();
        shoppingListDto.setId(shoppingList.getId());
        shoppingListDto.setShoppingListName(shoppingList.getShoppingListName());
        return shoppingListDto;
    }

    public ShoppingList convertToEntity(ShoppingListDto shoppingListDto) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(shoppingListDto.getId());
        shoppingList.setShoppingListName(shoppingListDto.getShoppingListName());
        return shoppingList;
    }


}
