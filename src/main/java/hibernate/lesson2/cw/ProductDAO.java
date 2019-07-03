package hibernate.lesson2.cw;

import hibernate.lesson1.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDAO {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        Product product = new Product();
        product.setName("gogogo");
        product.setDescription("i will go to heaven");
        product.setPrice(33);

        save(product);
    }

    public static void save(Product product) {

        Session session = null;
        Transaction tr = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            session.save(product);

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Save is filed");
            System.err.println(e.getMessage());
            if (tr != null)
                tr.rollback();

        } finally {
            if (session != null)
                session.close();
        }
        System.out.println("Save is done");
    }

    public static SessionFactory createSessionFactory() {

        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
