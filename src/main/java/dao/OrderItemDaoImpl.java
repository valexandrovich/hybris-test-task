package dao;

import model.domain.OrderItem;
import model.domain.Product;
import org.hibernate.Session;
import utils.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class OrderItemDaoImpl implements OrderItemDao{
    @Override
    public Integer save(OrderItem orderItem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Integer savedId= (Integer) session.save(orderItem);
        session.getTransaction().commit();
        return savedId;
    }

    @Override
    public void save(List<OrderItem> orderItems) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        for (OrderItem orderItem : orderItems){
            Integer savedId= (Integer) session.save(orderItem);
        }
        session.getTransaction().commit();
    }

    @Override
    public List<OrderItem> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from OrderItem oi order by oi.order.id desc ");
        List<OrderItem> allOrderItems = query.getResultList();
        return allOrderItems;
    }

    @Override
    public OrderItem findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from OrderItem oi where oi.id  = " + id);
        OrderItem orderItem = (OrderItem) query.getSingleResult();
        return orderItem;
    }

    @Override
    public List<OrderItem> findByOrderId(Integer orderId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from OrderItem oi where order.id  = " + orderId);
        List<OrderItem> orderItems = query.getResultList();
        return orderItems;
    }

    @Override
    public void update(OrderItem orderItem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(orderItem);
        session.getTransaction().commit();
    }
}
