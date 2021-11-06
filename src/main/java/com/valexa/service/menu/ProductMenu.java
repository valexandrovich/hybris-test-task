package com.valexa.service.menu;

import com.valexa.dao.ProductDao;
import com.valexa.dao.ProductDaoImpl;
import com.valexa.model.Product;
import com.valexa.service.form.CreateProductForm;
import com.valexa.utils.OrderedProductPrinter;
import com.valexa.utils.ProductPrinter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ProductMenu extends AbstractMenu {

    ProductDao productDao = new ProductDaoImpl();
    ProductPrinter productPrinter = new ProductPrinter();
    OrderedProductPrinter orderedProductPrinter = new OrderedProductPrinter();
    CreateProductForm createProductForm = new CreateProductForm();

    @Override
    public void startMenu() {
        initialize();
        Integer userChoice = -1;
        while (userChoice != 5) {
            printMenu();
            userChoice = getUserChoice();
            switch (userChoice) {
                case 1:
                    createProductForm.createProduct();
                    setMenuBody(productPrinter.printProducts(productDao.findAll()));
                    break;
                case 2:
                    setMenuBody(orderedProductPrinter.printProducts(productDao.findOrderedProducts()));
                    break;
                case 3:
                    Integer productId = promptProductId();
                    Product product = productDao.findById(productId);
                    if (product == null) {
                        System.out.println("No product for this ID!");
                    } else {
                        productDao.delete(product);
                        setMenuBody(productPrinter.printProducts(productDao.findAll()));
                    }
                    userChoice = -1;
                    break;
                case 4:
                    if (isDeleteAllProducts()) {
                        System.out.println("Deleting all products ...");
                        productDao.deleteAll();
                    } else {
                        System.out.println("Wrong password!");
                    }
                    setMenuBody(productPrinter.printProducts(productDao.findAll()));
                    break;
                case 5:
                    return;
            }
        }
    }

    @Override
    void initialize() {
        List<Product> products = productDao.findAll();
        setMenuBody(productPrinter.printProducts(products));
        setMenuHeader(null);
        List<String> mainMenuItems = Arrays.asList(
                "Create product",
                "Show ordered products",
                "Delete product by ID",
                "Delete all product (need password)",
                "Back to Main Menu"
        );
        setMenuItems(mainMenuItems);
    }

    private Integer promptProductId() {
        Scanner in = new Scanner(System.in);
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

    private boolean isDeleteAllProducts() {
        boolean result = false;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        InputStream resourceStream = loader.getResourceAsStream("local.properties");
        try {
            props.load(resourceStream);
            Scanner in = new Scanner(System.in);
            System.out.print("Enter password: ");
            String userPassword = in.nextLine();
            if (!userPassword.isEmpty() && userPassword.equals(props.getProperty("products.password"))) {
                result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
