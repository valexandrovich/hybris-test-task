package controller;

import model.domain.OrderItem;
import model.domain.OrderStatus;
import model.domain.Product;
import model.domain.ProductStatus;
import service.OrderItemService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class IndexController extends HttpServlet {

    ProductService productService = new ProductService();
    OrderItemService orderItemService = new OrderItemService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products =productService.getAllProducts();
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        req.setAttribute("products", products);
        req.setAttribute("orderItems", orderItems);
        req.setAttribute("orderStatuses", Arrays.asList(OrderStatus.values()));
        req.setAttribute("productStatuses", Arrays.asList(ProductStatus.values()));
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
}
