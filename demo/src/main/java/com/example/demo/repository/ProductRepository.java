package com.example.demo.repository;

import com.example.demo.repository.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//database
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByProductId(String productId);
   // void deleteByProductId(String id);
    Long deleteByProductId(String productId);
}
