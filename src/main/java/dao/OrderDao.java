package dao;

import model.domain.Order;

import java.util.List;

public interface OrderDao {
    Integer save(Order order);

    List<Order> findAll();

    Order findById(Integer id);
}
