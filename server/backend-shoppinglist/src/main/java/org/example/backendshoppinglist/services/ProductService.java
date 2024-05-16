package org.example.backendshoppinglist.services;

import org.example.backendshoppinglist.converters.ProductDtoConverter;
import org.example.backendshoppinglist.dtos.ProductDto;
import org.example.backendshoppinglist.entities.Product;
import org.example.backendshoppinglist.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoConverter productDtoConverter;

    public ProductService(ProductRepository productRepository, ProductDtoConverter productDtoConverter) {
        this.productRepository = productRepository;
        this.productDtoConverter = productDtoConverter;
    }

    public List<ProductDto> getAllProducts() {
        List<ProductDto> products = new ArrayList<>();
        productRepository.findAll().forEach(
                product -> products.add(productDtoConverter.convertToDto(product))
        );
        return products;
    }

    public ProductDto addProduct(ProductDto productDto) {
        productRepository.save(productDtoConverter.convertToEntity(productDto));
        return productDto;
    }
    public boolean existsById(Integer id) {
        return productRepository.existsById(id);
    }
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

}
