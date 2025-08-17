package com.elasticsearch.repository;

import com.elasticsearch.entity.Product;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

//similar to normal repo class

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, Integer> {

    // Search by name OR description
    @Query("""
        {
          "multi_match": {
            "query": "?0",
            "fields": ["name", "description"]
          }
        }
    """)
    List<Product> searchByNameOrDescription(String text);

    List<Product> findByNameContainingOrDescriptionContainingAndPriceBetween(
            String name, String description, double minPrice, double maxPrice
        );
    
    @Query("""
    {
      "bool": {
        "must": [
          {
            "multi_match": {
              "query": "?0",
              "fields": ["name", "description"]
            }
          },
          {
            "range": {
              "price": {
                "gte": ?1,
                "lte": ?2
              }
            }
          }
        ]
      }
    }
    """)
    List<Product> searchByKeywordAndPrice(String keyword, double min, double max);
    
    @Query("""
            {
              "match": {
                "name": {
                  "query": "?0",
                  "fuzziness": "AUTO"
                }
              }
            }
        """)
        List<Product> fuzzySearchByName(String keyword);
}
