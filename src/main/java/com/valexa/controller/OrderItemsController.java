package com.valexa.controller;

import com.valexa.dao.OrderItemDao;
import com.valexa.model.OrderItem;
import lombok.Setter;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderItemsController implements HttpRequestHandler {

    @Setter
    OrderItemDao orderItemDao;

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if (httpServletRequest.getMethod().equalsIgnoreCase("POST")){
            doPost(httpServletRequest, httpServletResponse);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderItemId = Integer.parseInt(req.getParameter("orderItemId"));
        OrderItem orderItem = orderItemDao.findById(orderItemId);
        orderItem.setQuantity(Integer.valueOf(req.getParameter("quantity")));
        orderItemDao.update(orderItem);
        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/"));
    }
}
