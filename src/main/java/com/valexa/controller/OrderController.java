package com.valexa.controller;

import com.valexa.dao.*;
import com.valexa.model.Order;
import com.valexa.model.OrderItem;
import com.valexa.model.OrderStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

public class OrderController extends HttpServlet {

    ProductDao productDao = new ProductDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    OrderDao orderDao = new OrderDaoImpl();
    Random rnd = new Random();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Order order = new Order();
            order.setStatus(OrderStatus.valueOf(req.getParameter("status")));
            order.setCreateAt(new Date());
            order.setUserId(rnd.nextInt(999));
            String[] productIds = req.getParameterValues("productId");
            String[] productQuantities = req.getParameterValues("quantity");
            if (productIds.length == productQuantities.length) {
                for (int i = 0; i < productIds.length; i++) {
                    OrderItem oi = new OrderItem();
                    oi.setOrder(order);
                    oi.setQuantity(Integer.valueOf(productQuantities[i]));
                    oi.setProduct(productDao.findById(Integer.valueOf(productIds[i])));
                    orderItemDao.save(oi);
                }
                orderDao.save(order);
            }
        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/"));
    }
}
