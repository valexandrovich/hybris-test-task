package service.menu;

import model.domain.OrderItem;
import service.OrderItemService;
import service.form.OrderItemUpdateForm;
import utils.OrderPrinter;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OrderMenu extends AbstractMenu {

    OrderItemService orderItemService = new OrderItemService();
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
                    setMenuBody(orderPrinter.printOrders(orderItemService.getAllOrderItems()));
                    break;
                case 2:
                    orderItemUpdateForm.updateOrderItem();
                    setMenuBody(orderPrinter.printOrders(orderItemService.getAllOrderItems()));
                    break;
                case 3:
                    Integer orderId = promptOrderId();
                    List<OrderItem> orderItems = orderItemService.findByOrderId(orderId);
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
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
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
