package service.form;

import model.domain.Product;
import model.domain.ProductStatus;
import service.ProductService;

import java.util.Date;
import java.util.Scanner;

public class CreateProductForm {
    Product product = new Product();
    ProductService productService = new ProductService();
    Scanner in = new Scanner(System.in);

    public void createProduct() {
        product.setName(promptName());
        product.setPrice(promptPrice());
        product.setStatus(promptStatus());
        product.setCreatedAt(new Date());
        System.out.println("Saving ...");
        productService.save(product);
    }

    public String promptName() {
        String userInput = "";
        while (userInput.isEmpty()) {
            System.out.print("Enter product name: ");
            userInput = in.nextLine();
        }
        return userInput;
    }

    public Integer promptPrice() {
        Integer price = -1;
        while (price < 0) {
            System.out.print("Enter product price: ");
            try {
                price = Integer.parseInt(in.nextLine());
            } catch (Exception ex) {
                price = -1;
            }
        }
        return price;
    }

    public ProductStatus promptStatus() {
        Integer productStatusId = -1;
        while (productStatusId < 1 || productStatusId > ProductStatus.values().length) {
            for (ProductStatus status : ProductStatus.values()) {
                System.out.println((status.ordinal() + 1) + " - " + status.toString());
            }
            System.out.print("Select product status: ");
            try {
                productStatusId = Integer.parseInt(in.nextLine());
            } catch (Exception ex) {
                productStatusId = -1;
            }
        }
        return ProductStatus.values()[productStatusId - 1];
    }
}
