package controller;

import model.domain.*;
import service.OrderItemService;
import service.OrderService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class OrderController extends HttpServlet {

    ProductService productService = new ProductService();
    OrderItemService orderItemService = new OrderItemService();
    OrderService orderService = new OrderService();
    Random rnd = new Random();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int orderItemId = Integer.parseInt(req.getParameter("orderItemId"));
        if (orderItemId > 0) {
            OrderItem orderItem = orderItemService.findById(orderItemId);
            orderItem.setQuantity(Integer.valueOf(req.getParameter("quantity")));
            orderItemService.updateOrderItem(orderItem);
        } else {

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
                    oi.setProduct(productService.getProductById(Integer.valueOf(productIds[i])));
                    orderItemService.save(oi);
                }
                orderService.save(order);
            }


        }
        List<Product> products = productService.getAllProducts();
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        req.setAttribute("products", products);
        req.setAttribute("orderItems", orderItems);
        req.setAttribute("orderStatuses", Arrays.asList(OrderStatus.values()));
        req.setAttribute("productStatuses", Arrays.asList(ProductStatus.values()));
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
}
