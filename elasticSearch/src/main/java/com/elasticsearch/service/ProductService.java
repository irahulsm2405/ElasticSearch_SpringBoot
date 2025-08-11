package com.elasticsearch.service;

import com.elasticsearch.entity.Product;

import java.util.List;

import org.springframework.stereotype.Service;

public interface ProductService {
    Product addNewProduct(Product product);

    Product updateProduct(int id, Product product);

    public Iterable<Product> getAllProducts();

    void deleteProduct(int id);
    
    public List<Product> searchByNameOrDesc(String text);
    
    public List<Product> searchByKeywordOrPrice(String keyword, double min, double max);
    
    public List<Product> searchByKeywordAndPrice(String keyword, double min, double max);
    
    public List<Product> fuzzySearch(String keyword);
}
