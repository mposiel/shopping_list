package org.example.backendshoppinglist.controllers;

import org.example.backendshoppinglist.dtos.ProductDto;
import org.example.backendshoppinglist.dtos.ShoppingListDto;
import org.example.backendshoppinglist.entities.ShoppingList;
import org.example.backendshoppinglist.services.ShoppingListService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/list")
public class ListController {
    private final ShoppingListService shoppingListService;

    public ListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping("/")
    public ResponseEntity<ShoppingListDto> addList(@RequestBody ShoppingListDto shoppingListDto) {
        return ResponseEntity.ok(shoppingListService.addList(shoppingListDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<ShoppingListDto>> getAllLists() {
        return ResponseEntity.ok(shoppingListService.getAllLists());
    }


}
