package com.example.demo.services.impl;

import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.entity.ProductEntity;
import com.example.demo.services.ProductService;
import com.example.demo.shared.Util;
import com.example.demo.shared.dto.ProductDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//business logic layer "what should happen in the project"
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Util util;

    public ProductServiceImpl(ProductRepository productRepository, Util util) {
        this.productRepository = productRepository;
        this.util = util;
    }


    public Optional<ProductDto> getProductById(String productId){
        Optional<ProductEntity> productIdEntity = productRepository.findByProductId(productId);
        return productIdEntity.map(productEntity -> {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(productEntity, productDto);
            return productDto;
        });
    }

    public ProductDto createProduct(ProductDto productDetailsIn){

        ProductEntity productEntity = new ProductEntity();//src
        BeanUtils.copyProperties(productDetailsIn, productEntity);//this is just the four parameters

        //get the id
        String productId = util.generateHash(productDetailsIn.getName());
        productEntity.setProductId(productId.substring(3));

        //this saved the entity copy and return
        ProductEntity productEntityOut = productRepository.save(productEntity);
        ProductDto productDtoOut = new ProductDto();//just small details
        BeanUtils.copyProperties(productEntityOut, productDtoOut);//popylera
        return productDtoOut;
    }

    //this give us the update we have done
    public Optional<ProductDto> updateProduct(String id, ProductDto productDto){
        Optional<ProductEntity> productIdEntity = productRepository.findByProductId(id);
        if(productIdEntity.isEmpty())
            return Optional.empty();
        return productIdEntity.map(productEntity -> {
            ProductDto response = new ProductDto();
            productEntity.setProductId(productDto.getProductId() != null ? util.generateHash(productDto.getName()).substring(3) : productEntity.getProductId());
            productEntity.setName(productDto.getName() != null ? productDto.getName() : productEntity.getName());
            productEntity.setCost(productDto.getCost() != 0 ? productDto.getCost() : productEntity.getCost());
            productEntity.setCategory(productDto.getCategory() != null ? productDto.getCategory() : productEntity.getCategory());

            //this get the new data and save it
            ProductEntity updateProductEntity = productRepository.save(productEntity);
            BeanUtils.copyProperties(updateProductEntity, response);
            return response;
        });
    }

    //this give us count that i have deleted
    @Transactional
    public boolean deleteProduct(String id) {
        long removedCount = productRepository.deleteByProductId(id);
        return removedCount > 0;
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    //this give us the hole List
    public List<ProductDto> getProducts() {
        Iterable<ProductEntity> productEntities = productRepository.findAll();
        ArrayList<ProductDto> productDtos = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(productEntity, productDto);
            productDtos.add(productDto);
        }
        return productDtos;
    }
}
