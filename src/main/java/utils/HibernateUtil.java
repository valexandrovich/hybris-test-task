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

    static {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
    }

    public static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            mapEntityClasses(configuration);
            configuration.setProperties(getLocalProperties());
            return configuration.buildSessionFactory();
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Configuration error");
        }
    }

    private static Properties getLocalProperties() throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        InputStream resourceStream = loader.getResourceAsStream("local.properties");
        props.load(resourceStream);
        return props;
    }

    private static void mapEntityClasses(Configuration configuration){
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(OrderItem.class);
        configuration.addAnnotatedClass(Order.class);
    }
}
