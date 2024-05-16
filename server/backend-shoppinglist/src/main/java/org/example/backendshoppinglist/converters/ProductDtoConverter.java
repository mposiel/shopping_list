package org.example.backendshoppinglist.converters;

import org.example.backendshoppinglist.dtos.ProductDto;
import org.example.backendshoppinglist.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoConverter {
    public ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getProductName());
        productDto.setId(product.getId());
        productDto.setUnitInteger(product.isUnitInteger());
        productDto.setUnit(product.getUnit());
        return productDto;
    }

    public Product convertToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setUnit(productDto.getUnit());
        product.setUnitInteger(productDto.isUnitInteger());
        return product;
    }
}
