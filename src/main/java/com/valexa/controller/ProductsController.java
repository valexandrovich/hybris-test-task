package com.valexa.controller;

import com.valexa.dao.ProductDao;
import com.valexa.model.Product;
import com.valexa.model.ProductStatus;
import lombok.Setter;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class ProductsController implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if (httpServletRequest.getMethod().equalsIgnoreCase("GET")){
                doGet(httpServletRequest, httpServletResponse);
        } else {
            doPost(httpServletRequest, httpServletResponse);
        }
    }

    @Setter
    ProductDao productDao;// = new ProductDaoImpl() ;
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    Properties props = new Properties();
    InputStream resourceStream = loader.getResourceAsStream("local.properties");

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("del") != null) {
            int delId = Integer.parseInt(req.getParameter("del"));
            productDao.delete(productDao.findById(delId));
        } else if (req.getParameter("password") != null && req.getParameter("deleteAll") != null) {
            String password = req.getParameter("password");
            props.load(resourceStream);
            if (props.getProperty("products.password").equals(password)) {
                productDao.deleteAll();
            }
        }
        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/"));
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        product.setName(req.getParameter("name"));
        product.setPrice(Integer.valueOf(req.getParameter("price")));
        product.setStatus(ProductStatus.valueOf(req.getParameter("status")));
        product.setCreatedAt(new Date());
        productDao.save(product);
        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/"));
    }
}
