package org.example.backendshoppinglist.controllers;

import org.example.backendshoppinglist.dtos.AddProductToListDto;
import org.example.backendshoppinglist.dtos.GetProductInListDto;
import org.example.backendshoppinglist.dtos.ShoppingListDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingListDto> getList(@PathVariable Integer id) {
        return ResponseEntity.ok(shoppingListService.getList(id));
    }

    @GetMapping("/userList")
    public ResponseEntity<List<ShoppingListDto>> getUserSharedLists() {
        return ResponseEntity.ok(shoppingListService.getUserSharedLists());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteList(@PathVariable Integer id) {
        if (!shoppingListService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        shoppingListService.deleteList(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingListDto> updateList(@PathVariable Integer id, @RequestBody ShoppingListDto shoppingListDto) {
        return ResponseEntity.ok(shoppingListService.updateList(id, shoppingListDto));
    }

    @PostMapping("/{listId}/products")
    public ResponseEntity<AddProductToListDto> addProductToList(@PathVariable Integer listId, @RequestBody AddProductToListDto addProductToListDto) {
        return ResponseEntity.ok(shoppingListService.addProductToList(listId, addProductToListDto));
    }

    @GetMapping("/{listId}/products")
    public ResponseEntity<List<GetProductInListDto>> getProductsFromList(@PathVariable Integer listId) {
        return ResponseEntity.ok(shoppingListService.getProductsFromList(listId));
    }

}