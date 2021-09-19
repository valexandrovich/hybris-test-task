package service.form;

import model.domain.Order;
import model.domain.OrderItem;
import model.domain.Product;
import service.ProductService;

import java.util.Date;
import java.util.Scanner;

public class AddOrderItemForm {
    ProductService productService = new ProductService();
    Scanner in = new Scanner(System.in);

    public OrderItem addOrderItem() {
        Product product = null;
        OrderItem orderItem = new OrderItem();
        while (product == null) {
            Integer productId = promptProductId();
            product = productService.getProductById(productId);
        }
        orderItem.setProduct(product);
        orderItem.setQuantity(promptQuantity());
        return orderItem;
    }

    private Integer promptProductId() {
        String userInput = "";
        Integer productId = -1;
        while (productId < 1) {
            System.out.print("Enter product ID: ");
            userInput = in.nextLine();
            try {
                productId = Integer.parseInt(userInput);
            } catch (Exception ex) {
                productId = -1;
            }
        }
        return productId;
    }

    private Integer promptQuantity(){
        String userInput = "";
        Integer quantity = -1;
        while (quantity < 1) {
            System.out.print("Enter quantity: ");
            userInput = in.nextLine();
            try {
                quantity = Integer.parseInt(userInput);
            } catch (Exception ex) {
                quantity = -1;
            }
        }
        return quantity;
    }
}
