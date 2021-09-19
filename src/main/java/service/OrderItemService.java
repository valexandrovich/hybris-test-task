package service;

import dao.OrderItemDaoImpl;
import model.domain.OrderItem;

import java.util.List;

public class OrderItemService {
    private final OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();

    public void save(OrderItem orderItem) {
        orderItemDao.save(orderItem);
    }

    public void save(List<OrderItem> orderItems) {
        orderItemDao.save(orderItems);
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemDao.findAll();
    }

    public OrderItem findById(Integer id) {
        return orderItemDao.findById(id);
    }

    public List<OrderItem> findByOrderId(Integer id){
        return orderItemDao.findByOrderId(id);
    }

    public void updateOrderItem(OrderItem orderItem){
        orderItemDao.update(orderItem);
    }
}
