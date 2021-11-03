package controller;

import model.domain.*;
import service.OrderItemService;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ProductsController extends HttpServlet {

    ProductService productService = new ProductService();
    OrderItemService orderItemService = new OrderItemService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        Product product = new Product();
        product.setName(req.getParameter("name"));
        product.setPrice(Integer.valueOf(req.getParameter("price")));
        product.setStatus(ProductStatus.valueOf(req.getParameter("status")));
        product.setCreatedAt(new Date());
        productService.save(product);

        List<Product> products =productService.getAllProducts();
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        req.setAttribute("products", products);
        req.setAttribute("orderItems", orderItems);
        req.setAttribute("orderStatuses", Arrays.asList(OrderStatus.values()));
        req.setAttribute("productStatuses", Arrays.asList(ProductStatus.values()));
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
