package com.valexa.controller;

import com.valexa.dao.OrderItemDao;
import com.valexa.dao.OrderItemDaoImpl;
import com.valexa.model.OrderItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderItemsController extends HttpServlet {

    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderItemId = Integer.parseInt(req.getParameter("orderItemId"));
        OrderItem orderItem = orderItemDao.findById(orderItemId);
        orderItem.setQuantity(Integer.valueOf(req.getParameter("quantity")));
        orderItemDao.update(orderItem);
        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/"));
    }
}
