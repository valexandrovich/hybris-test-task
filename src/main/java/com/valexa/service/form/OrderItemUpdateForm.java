package com.valexa.service.form;

import com.valexa.dao.OrderItemDao;
import com.valexa.dao.OrderItemDaoImpl;
import com.valexa.model.OrderItem;

import java.util.Scanner;

public class OrderItemUpdateForm {
    OrderItem orderItem = null;
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    Scanner in = new Scanner(System.in);

    public void updateOrderItem() {
        orderItem = orderItemDao.findById(promptOrderItemId());
        if (orderItem != null) {
            Integer quantity = promptQuantity();
            orderItem.setQuantity(quantity);
            orderItemDao.update(orderItem);
        } else {
            System.out.println("Order item ID not found!");
        }
    }

    public Integer promptOrderItemId() {
        String userInput = "";
        Integer orderItemId = -1;
        while (orderItemId < 1) {
            System.out.print("Enter order item ID: ");
            userInput = in.nextLine();
            try {
                orderItemId = Integer.parseInt(userInput);
            } catch (Exception ex) {
                orderItemId = -1;
            }
        }
        return orderItemId;
    }

    public Integer promptQuantity() {
        Integer quantity = -1;
        while (quantity < 0) {
            System.out.print("Enter new quantity: ");
            try {
                quantity = Integer.parseInt(in.nextLine());
            } catch (Exception ex) {
                quantity = -1;
            }
        }
        return quantity;
    }

}
