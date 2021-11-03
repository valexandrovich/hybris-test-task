package controller;

import model.domain.OrderItem;
import service.OrderItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderItemsController extends HttpServlet {

    OrderItemService orderItemService = new OrderItemService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderItemId = Integer.parseInt(req.getParameter("orderItemId"));
        OrderItem orderItem = orderItemService.findById(orderItemId);
        orderItem.setQuantity(Integer.valueOf(req.getParameter("quantity")));
        orderItemService.updateOrderItem(orderItem);
        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/"));
    }
}
