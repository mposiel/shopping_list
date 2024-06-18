package org.example.backendshoppinglist.services;

import org.example.backendshoppinglist.converters.ShoppingListDtoConverter;
import org.example.backendshoppinglist.dtos.AddProductToListDto;
import org.example.backendshoppinglist.dtos.GetProductInListDto;
import org.example.backendshoppinglist.dtos.ShoppingListDto;
import org.example.backendshoppinglist.entities.*;
import org.example.backendshoppinglist.repositories.ProductRepository;
import org.example.backendshoppinglist.repositories.ShoppingListProductRepository;
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
    private final ProductRepository productRepository;
    private final ShoppingListProductRepository shoppingListProductRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, ShoppingListDtoConverter shoppingListDtoConverter, UserShoppingListRepository userShoppingListRepository, UserService userService, ProductRepository productRepository, ShoppingListProductRepository shoppingListProductRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.shoppingListDtoConverter = shoppingListDtoConverter;
        this.userShoppingListRepository = userShoppingListRepository;
        this.userService = userService;
        this.productRepository = productRepository;
        this.shoppingListProductRepository = shoppingListProductRepository;
    }

    public ShoppingListDto addList(ShoppingListDto shoppingListDto) {

        Optional<User> user = userService.getLoggedInUser();
        if (user.isPresent()) {
            ShoppingList shoppingList = shoppingListDtoConverter.convertToEntity(shoppingListDto);
            shoppingList = shoppingListRepository.save(shoppingList);

            UserShoppingList userShoppingList = new UserShoppingList(
                    new UserShoppingListKey(
                            user.get().getId(),
                            shoppingList.getId()),
                    shoppingList,
                    user.get(),
                    ListRole.OWNER
            );

            userShoppingListRepository.save(userShoppingList);

            return shoppingListDtoConverter.convertToDto(shoppingList);
        } else {
            throw new IllegalArgumentException("User not found");
        }

    }

    public List<ShoppingListDto> getAllLists() {
        Optional<User> user = userService.getLoggedInUser();
        if (user.isPresent()) {
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
        if (user.isPresent()) {
            Optional<UserShoppingList> userShoppingList = userShoppingListRepository.findById(new UserShoppingListKey(id, user.get().getId()));
            if (userShoppingList.isPresent()) {
                return shoppingListDtoConverter.convertToDto(userShoppingList.get().getShoppingList());
            } else {
                throw new IllegalArgumentException("Shopping list not found");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public boolean existsById(Integer id) {
        Optional<User> user = userService.getLoggedInUser();
        if (user.isPresent()) {
            return userShoppingListRepository.existsById(new UserShoppingListKey(id, user.get().getId()));
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }


    public void deleteList(Integer id) {
        Optional<User> user = userService.getLoggedInUser();
        if (user.isPresent()) {
            userShoppingListRepository.deleteById(new UserShoppingListKey(id, user.get().getId()));
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public ShoppingListDto updateList(Integer id, ShoppingListDto shoppingListDto) {
        Optional<User> user = userService.getLoggedInUser();
        if (user.isPresent()) {
            Optional<UserShoppingList> userShoppingList = userShoppingListRepository.findById(new UserShoppingListKey(id, user.get().getId()));
            if (userShoppingList.isPresent()) {
                ShoppingList shoppingList = shoppingListDtoConverter.convertToEntity(shoppingListDto);
                shoppingList.setId(id);
                shoppingList = shoppingListRepository.save(shoppingList);
                return shoppingListDtoConverter.convertToDto(shoppingList);
            } else {
                throw new IllegalArgumentException("Shopping list not found");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public AddProductToListDto addProductToList(Integer listId, AddProductToListDto addProductToListDto) {
        Optional<User> user = userService.getLoggedInUser();
        if (user.isPresent()) {
            Optional<UserShoppingList> userShoppingList = userShoppingListRepository.findById(new UserShoppingListKey(listId, user.get().getId()));
            if (userShoppingList.isPresent()) {
                ShoppingList shoppingList = userShoppingList.get().getShoppingList();
                Optional<Product> product = productRepository.findById(addProductToListDto.getId());
                if (product.isPresent()) {
                    ShoppingListProduct shoppingListProduct = new ShoppingListProduct(
                            new ShoppingListProductKey(
                                    shoppingList.getId(),
                                    product.get().getId()
                            ),
                            shoppingList,
                            product.get(),
                            addProductToListDto.getQuantity()
                    );
                    shoppingListProductRepository.save(shoppingListProduct);
                    shoppingList.getShoppingListProducts().add(shoppingListProduct);
                    return addProductToListDto;
                } else {
                    throw new IllegalArgumentException("Product not found");
                }
            } else {
                throw new IllegalArgumentException("Shopping list not found");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public List<GetProductInListDto> getProductsFromList(Integer listId) {
        User user = userService.getLoggedInUser().orElseThrow(() -> new IllegalArgumentException("User not found"));
        Optional<UserShoppingList> userShoppingList = userShoppingListRepository.findById(new UserShoppingListKey(listId, user.getId()));
        if (userShoppingList.isPresent()) {
            ShoppingList shoppingList = userShoppingList.get().getShoppingList();

            List<GetProductInListDto> productDtos = new ArrayList<>();

            shoppingList.getShoppingListProducts().forEach(
                    shoppingListProduct -> productDtos.add(
                            new GetProductInListDto(
                                    shoppingListProduct.getProduct().getId(),
                                    shoppingListProduct.getProduct().getProductName(),
                                    shoppingListProduct.getProduct().getUnit(),
                                    shoppingListProduct.getProduct().isUnitInteger(),
                                    shoppingListProduct.getQuantity()
                            )
                    )
            );

            return productDtos;
        } else {
            throw new IllegalArgumentException("Shopping list not found");
        }
    }

    public List<ShoppingListDto> getUserSharedLists() {
        User user = userService.getLoggedInUser().orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<UserShoppingList> userShoppingLists = userShoppingListRepository.findAllByUser(user).orElseThrow(() -> new IllegalArgumentException("User shopping lists not found"));

        List<ShoppingListDto> shoppingListDtos = new ArrayList<>();
        userShoppingLists.forEach(
                userShoppingList -> shoppingListDtos.add(shoppingListDtoConverter.convertToDto(userShoppingList.getShoppingList()))
        );

        return shoppingListDtos;
    }
}
