package service;

import dao.ProductDaoImpl;
import model.domain.Product;

import java.util.List;
import java.util.Map;

public class ProductService {

    private final ProductDaoImpl productDao = new ProductDaoImpl();

    public void save(Product product) {
        productDao.save(product);
    }

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public Product getProductById(Integer id) {
        return productDao.findById(id);
    }

    public Map<Integer, Product> getOrderedProducts() {
        return productDao.findOrderedProducts();
    }

    public void delete(Product product) {
        productDao.delete(product);
    }

    public void deleteAll() {
        productDao.deleteAll();
    }
}
