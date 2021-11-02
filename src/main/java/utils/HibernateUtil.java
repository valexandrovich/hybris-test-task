package utils;

import model.domain.Order;
import model.domain.OrderItem;
import model.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
    }


    public static boolean checkDbConnection() {
        try {
            HibernateUtil.getSessionFactory();
            return true;
        } catch (ExceptionInInitializerError ex) {
            return false;
        }
    }

    public static SessionFactory getSessionFactory() {
        if (HibernateUtil.sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Configuration configuration = new Configuration();
            mapEntityClasses(configuration);
            configuration.setProperties(getLocalProperties());
            return configuration.buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Database initialization error!");
        }
    }

    private static Properties getLocalProperties() throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        InputStream resourceStream = loader.getResourceAsStream("local.properties");
        props.load(resourceStream);
        return props;
    }

    private static void mapEntityClasses(Configuration configuration) {
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(OrderItem.class);
        configuration.addAnnotatedClass(Order.class);
    }
}
