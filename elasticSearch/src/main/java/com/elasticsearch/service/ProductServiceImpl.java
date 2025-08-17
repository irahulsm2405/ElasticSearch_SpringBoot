package com.elasticsearch.service;

import com.elasticsearch.entity.Product;
import com.elasticsearch.repository.ProductRepository;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;

import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Iterable<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product addNewProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(int id, Product product) {
		product.setId(id);
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(int id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<Product> searchByNameOrDesc(String text) {
		return productRepository.searchByNameOrDescription(text);
	}

    public List<Product> searchByKeywordOrPrice(String keyword, double min, double max) {
        return productRepository.findByNameContainingOrDescriptionContainingAndPriceBetween(
            keyword, keyword, min, max
        );
    }
    
    public List<Product> searchByKeywordAndPrice(String keyword, double min, double max) {
        return productRepository.searchByKeywordAndPrice(keyword, min, max);
    }   
    
    @Override
    public List<Product> fuzzySearch(String keyword) {
        return productRepository.fuzzySearchByName(keyword);
    }

}
