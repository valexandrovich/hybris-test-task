package service.menu;

import model.domain.Order;
import model.domain.OrderItem;
import model.domain.OrderStatus;
import model.domain.ProductStatus;
import service.OrderItemService;
import service.OrderService;
import service.form.AddOrderItemForm;
import utils.OrderItemsPrinter;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateOrderMenu extends AbstractMenu {

    AddOrderItemForm addOrderItemForm = new AddOrderItemForm();
    OrderItemsPrinter orderItemsPrinter = new OrderItemsPrinter();
    List<OrderItem> orderItems = new ArrayList<>();
    OrderService orderService = new OrderService();
    OrderItemService orderItemService = new OrderItemService();
    AtomicInteger userIdGenerator = new AtomicInteger();

    @Override
    public void startMenu() {
        initialize();
        Order order = new Order();
        Integer userChoice = -1;
        while (userChoice != 8){
            printMenu();
            userChoice = getUserChoice();
            switch (userChoice){
                case 1:
                    orderItems.add(addOrderItemForm.addOrderItem());
                    setMenuBody(orderItemsPrinter.printOrderItems(orderItems));
                    setMenuHeader(null);
                    break;
                case 2:
                    if (orderItems.size()>0){

                        order.setCreateAt(new Date());
                        order.setStatus(promptOrderStatus());
                        System.out.println("Saving ... ");
                        order.setUserId(userIdGenerator.incrementAndGet());
                        orderItems.stream().forEach(orderItem -> orderItem.setOrder(order));
                        orderItemService.save(orderItems);
                        return;
                    } else {
                        System.out.println("Empty order!");
                    }
                    break;
                case 3:
                    return;
            }
        }
    }

    @Override
    public void initialize() {
        setMenuHeader("NEW ORDER");
        List<String> mainMenuItems = Arrays.asList(
                "Add product",
                "Save",
                "Close"
        );
        setMenuItems(mainMenuItems);
    }

    private OrderStatus promptOrderStatus(){
        Scanner in = new Scanner(System.in);
        Integer orderStatusId = -1;
        while (orderStatusId < 1 || orderStatusId > OrderStatus.values().length) {
            System.out.println("-----------------------------------");
            for (OrderStatus status : OrderStatus.values()) {
                System.out.println((status.ordinal() + 1) + " - " + status.toString());
            }
            System.out.print("Select order status: ");
            try {
                orderStatusId = Integer.parseInt(in.nextLine());
            } catch (Exception ex) {
                orderStatusId = -1;
            }
        }
        return OrderStatus.values()[orderStatusId - 1];
    }
}
