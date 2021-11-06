package com.valexa.dao;

import com.valexa.model.Order;
import org.hibernate.Session;
import com.valexa.utils.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Integer save(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Integer savedId = (Integer) session.save(order);
        session.getTransaction().commit();
        return savedId;
    }

    @Override
    public List<Order> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Order ");
        List<Order> allOrders = query.getResultList();
        return allOrders;
    }

    @Override
    public Order findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Order o where o.id  = " + id);
        Order order = (Order) query.getSingleResult();
        return order;
    }
}
