package com.productcatalog.productCatalogService.repository;

import com.productcatalog.productCatalogService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(String category);

    List<Product> findAllByProductName(String name);
}
