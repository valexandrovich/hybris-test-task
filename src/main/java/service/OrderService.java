package service;

import dao.OrderDaoImpl;
import model.domain.Order;
import model.domain.Product;

import java.util.List;

public class OrderService {
    private final OrderDaoImpl orderDao = new OrderDaoImpl();

    public void save(Order order) {
        orderDao.save(order);
    }

    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    public Order getOrderById(Integer id) {
        return orderDao.findById(id);
    }


}
