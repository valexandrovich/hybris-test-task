package controller;

import model.domain.Product;
import model.domain.ProductStatus;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class ProductsController extends HttpServlet {

    ProductService productService = new ProductService();
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    Properties props = new Properties();
    InputStream resourceStream = loader.getResourceAsStream("local.properties");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("del") != null) {
            int delId = Integer.parseInt(req.getParameter("del"));
            productService.delete(productService.getProductById(delId));
        } else if (req.getParameter("password") != null && req.getParameter("deleteAll") != null) {
            String password = req.getParameter("password");
            props.load(resourceStream);
            if (props.getProperty("products.password").equals(password)){
                productService.deleteAll();
            }
        }
        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        product.setName(req.getParameter("name"));
        product.setPrice(Integer.valueOf(req.getParameter("price")));
        product.setStatus(ProductStatus.valueOf(req.getParameter("status")));
        product.setCreatedAt(new Date());
        productService.save(product);
        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/"));
    }
}
