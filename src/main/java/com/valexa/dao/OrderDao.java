package com.valexa.dao;

import com.valexa.model.Order;

import java.util.List;

public interface OrderDao {
    Integer save(Order order);

    List<Order> findAll();

    Order findById(Integer id);
}
