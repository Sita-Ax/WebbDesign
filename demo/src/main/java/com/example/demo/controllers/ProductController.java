package com.example.demo.controllers;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.request.ProductRequest;
import com.example.demo.model.response.ProductResponse;
import com.example.demo.services.ProductService;
import com.example.demo.shared.dto.ProductDto;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//presentation and top layer "this is the message or quest" also the controller
//it just talk to service layer
@RestController
//this is the start url
@RequestMapping(path = "/products")//localhost://localhost:8080/products/pathid{B@367386}
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getProducts() {
        List<ProductDto> productDtos = productService.getProducts();
        ArrayList<ProductResponse> responseList = new ArrayList<>();
        for (ProductDto productDto : productDtos) {
            ProductResponse response= new ProductResponse();
            BeanUtils.copyProperties(productDto, response);
            responseList.add(response);
        }
        return responseList;
    }

    @GetMapping(value = "/{productId}")
    public ProductResponse getProduct(@PathVariable String productId) {
        ProductResponse response = new ProductResponse();
        Optional<ProductDto> optionalProductDto = productService.getProductById(productId);
        if (optionalProductDto.isPresent()) {
            ProductDto productDto = optionalProductDto.get();
            //this will copy this 2 values the rest is null
            BeanUtils.copyProperties(productDto, response);
            return response;
        }
        throw new NotFoundException("no id" + productId);
    }

    //createUser method that take in request tex from postman
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productDetails) {
        //Copy json to dto in
        ProductDto productDtoIn = new ProductDto();
        //this will copy this 2 values the rest is null (detail is the json info)
        BeanUtils.copyProperties(productDetails, productDtoIn);
        //pass dto in to service layer
        ProductDto productDtoOut = productService.createProduct(productDtoIn);
        //copy dto out from service layer to response
        ProductResponse response = new ProductResponse();
        BeanUtils.copyProperties(productDtoOut, response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ProductResponse updateProduct(@PathVariable String productId, @RequestBody ProductRequest requestData) {
        ProductDto productDtoIn = new ProductDto();
        BeanUtils.copyProperties(requestData, productDtoIn);

        Optional<ProductDto> productDtoOut = productService.updateProduct(productId, productDtoIn);
        if (productDtoOut.isEmpty()) {
            throw new NotFoundException("Not found" + productId);
        }

        ProductDto productDto = productDtoOut.get();
        ProductResponse response = new ProductResponse();
        BeanUtils.copyProperties(productDto, response);
        return response;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable String productId) {
        boolean deleted = productService.deleteProduct(productId);
        if(deleted){
            return "Deleted products";
        }
      throw new NotFoundException(" Not this id" + productId);
    }
}



