package com.elasticsearch.controller;

import com.elasticsearch.entity.Product;
import com.elasticsearch.repository.ProductRepository;
import com.elasticsearch.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    
    
    @GetMapping("/search/{text}")
    public List<Product> searchByNameOrDesc(@PathVariable String text) {
        return productService.searchByNameOrDesc(text);
    }

    //Will search by name or price range
    @GetMapping("/search/orFilter")
    public List<Product> searchOrFilter(
        @RequestParam String keyword,
        @RequestParam double min,
        @RequestParam double max) {
        return productService.searchByKeywordOrPrice(keyword, min, max);
    }
    
    //Will search by name and price range
    @GetMapping("/search/andFilter")
    public List<Product> searchAndFilter(
        @RequestParam String keyword,
        @RequestParam double min,
        @RequestParam double max) {
        return productService.searchByKeywordAndPrice(keyword, min, max);
    }
    
    @GetMapping("/search/fuzzy")
    public List<Product> fuzzySearch(@RequestParam String keyword) {
        return productService.fuzzySearch(keyword);
    }
    
    @GetMapping("/all")
    public Iterable<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/add")
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product);
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable int id, Product product){
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
    }
}
