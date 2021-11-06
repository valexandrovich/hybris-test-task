package com.valexa.service.menu;

import com.valexa.dao.OrderItemDao;
import com.valexa.dao.OrderItemDaoImpl;
import com.valexa.model.OrderItem;
import com.valexa.service.form.OrderItemUpdateForm;
import com.valexa.utils.OrderPrinter;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OrderMenu extends AbstractMenu {

    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    OrderPrinter orderPrinter = new OrderPrinter();
    CreateOrderMenu createOrderMenu = new CreateOrderMenu();
    OrderItemUpdateForm orderItemUpdateForm = new OrderItemUpdateForm();

    @Override
    public void startMenu() {
        initialize();
        Integer userChoice = -1;
        while (userChoice != 4) {
            printMenu();
            userChoice = getUserChoice();
            switch (userChoice) {
                case 1:
                    createOrderMenu.startMenu();
                    setMenuBody(orderPrinter.printOrders(orderItemDao.findAll()));
                    break;
                case 2:
                    orderItemUpdateForm.updateOrderItem();
                    setMenuBody(orderPrinter.printOrders(orderItemDao.findAll()));
                    break;
                case 3:
                    Integer orderId = promptOrderId();
                    List<OrderItem> orderItems = orderItemDao.findByOrderId(orderId);
                    if (orderItems.size() < 1) {
                        System.out.println("No order on this ID! Try again");
                        userChoice = -1;
                    } else {
                        setMenuBody(orderPrinter.printOrders(orderItems));
                    }
                    break;
                case 4:
                    return;
            }
        }
    }

    private Integer promptOrderId() {
        Scanner in = new Scanner(System.in);
        String userInput = "";
        Integer orderId = -1;
        while (orderId < 1) {
            System.out.print("Enter order ID: ");
            userInput = in.nextLine();
            try {
                orderId = Integer.parseInt(userInput);
            } catch (Exception ex) {
                orderId = -1;
            }
        }
        return orderId;
    }

    @Override
    void initialize() {
        List<OrderItem> orderItems = orderItemDao.findAll();
        setMenuBody(orderPrinter.printOrders(orderItems));
        setMenuHeader(null);
        List<String> mainMenuItems = Arrays.asList(
                "Create order",
                "Update order",
                "Find order by ID",
                "Back to Main menu"
        );
        setMenuItems(mainMenuItems);
    }
}
