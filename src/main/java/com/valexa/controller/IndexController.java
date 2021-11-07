package com.valexa.controller;

import com.valexa.dao.OrderItemDao;
import com.valexa.dao.ProductDao;
import com.valexa.model.OrderItem;
import com.valexa.model.OrderStatus;
import com.valexa.model.Product;
import com.valexa.model.ProductStatus;
import lombok.Setter;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class IndexController implements HttpRequestHandler {

    @Setter
    ProductDao productDao;
    @Setter
    OrderItemDao orderItemDao;

    private void refreshIndexPage(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> products = productDao.findAll();
        List<OrderItem> orderItems = orderItemDao.findAll();
        req.setAttribute("products", products);
        req.setAttribute("orderItems", orderItems);
        req.setAttribute("orderStatuses", Arrays.asList(OrderStatus.values()));
        req.setAttribute("productStatuses", Arrays.asList(ProductStatus.values()));
        try {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        refreshIndexPage(httpServletRequest, httpServletResponse);
    }
}
