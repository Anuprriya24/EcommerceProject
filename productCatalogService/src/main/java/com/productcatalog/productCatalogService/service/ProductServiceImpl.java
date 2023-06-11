package com.productcatalog.productCatalogService.service;

import com.productcatalog.productCatalogService.entity.Product;
import com.productcatalog.productCatalogService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Product getProductById(Long id)
    {
        return productRepository.getOne(id);
    }

    @Override
    public List<Product> getAllProductsByName(String name) {

        return productRepository.findAllByProductName(name);
    }

    @Override
    public Product addProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {

        productRepository.deleteById(productId);
    }
}
