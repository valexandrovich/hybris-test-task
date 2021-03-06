package com.valexa.dao;

import com.valexa.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductDao {

    Integer save(Product product);

    List<Product> findAll();

    Product findById(Integer id);

    Map<Integer, Product> findOrderedProducts();

    void delete(Product product);

    void deleteAll();
}
