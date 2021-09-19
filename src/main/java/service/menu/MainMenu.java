package service.menu;

import service.form.CreateProductForm;

import java.util.Arrays;
import java.util.List;

public class MainMenu extends AbstractMenu {

//
    OrderMenu orderMenu = new OrderMenu();
    ProductMenu productMenu = new ProductMenu();

    @Override
    public void startMenu() {
        initialize();
        Integer userChoice = -1;
        while (userChoice != 8) {
            printMenu();
            userChoice = getUserChoice();
            switch (userChoice) {
//                case 1:
//                    createOrderMenu.startMenu();
//                    break;
                case 1:
                    productMenu.startMenu();
                    break;
                case 2:
                    orderMenu.startMenu();
                    break;
                case 3:
                    return;
            }
        }

    }

    @Override
    public void initialize() {
        setMenuHeader("HYBRIS APP");
        List<String> mainMenuItems = Arrays.asList(
//                "Create order",// 1
//                "Update order",// 2
                "Products menu",// 1
                "Order menu",// 2
                "Exit application"// 3
        );
        setMenuItems(mainMenuItems);
    }
}
