package dao;

import model.domain.Product;
import model.domain.ProductStatus;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProductDaoImpl implements ProductDao{
    @Override
    public Integer save(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Integer savedId= (Integer) session.save(product);
        session.getTransaction().commit();
        return savedId;
    }

    @Override
    public List<Product> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Product");
        List<Product> allProducts = query.getResultList();
        return allProducts;
    }

    @Override
    public Product findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Product p where p.id  = " + id);
        try {
            Product product = (Product) query.getSingleResult();
            return product;
        } catch (NoResultException ex){
            return null;
        }


    }

    @Override
    public Map<Integer, Product> findOrderedProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select   p.name,  p.price, p.status, sum(oi.quantity) from Product p left join OrderItem oi on oi.product.id = p.id where oi.quantity > 0 group by p.name, p.price, p.status order by sum(oi.quantity) desc ");
         List<Object[]> result =  query.getResultList();

         Map<Integer, Product> parsedResult = new TreeMap<>(Collections.reverseOrder());

         for (Object[] o : result){
             Product product = new Product();
             product.setName(String.valueOf(o[0]));
             product.setPrice((Integer) o[1]);
             product.setStatus(ProductStatus.valueOf(String.valueOf(o[2])));
             parsedResult.put(Integer.valueOf(o[3].toString()), product);
         }
         return parsedResult;
    }

    @Override
    public void delete(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();
    }

    @Override
    public void deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Product ");
        query.executeUpdate();
        session.getTransaction().commit();
    }
}
