package com.example.demo.services;

import com.example.demo.shared.dto.ProductDto;

import java.util.List;
import java.util.Optional;

//just a interface so this hard data will talk to and 'mock' things it is a dependencies
public interface ProductService {

    //Optional<ProductEntity> getProductByProductId(String product_id);
    ProductDto createProduct(ProductDto productDetails);
    List<ProductDto> getProducts();
    Optional<ProductDto> getProductById(String productId);
    Optional<ProductDto> updateProduct(String productId, ProductDto productDtoIn);
    boolean deleteProduct(String productId);
    void deleteProduct(long id);
}
