package service;

import dao.OrderDaoImpl;
import model.domain.Order;

public class OrderService {
    private final OrderDaoImpl orderDao = new OrderDaoImpl();

    public void save(Order order) {
        orderDao.save(order);
    }


}
