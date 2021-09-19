package dao;

import model.domain.Order;
import model.domain.OrderItem;

import java.util.List;

public interface OrderItemDao {
    Integer save(OrderItem orderItem);
    void save(List<OrderItem> orderItems);
    List<OrderItem> findAll();
    OrderItem findById(Integer id);
    List<OrderItem> findByOrderId(Integer orderId);
    void update(OrderItem orderItem);

}
