package hibernate.lesson2.hw1;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDao {

    private SessionFactory sessionFactory;

    public Product save(Product product) throws Exception {

        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            session.save(product);

            tr.commit();
            return product;
        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();
            throw new Exception("Save method was filed", e);
        }
    }

    public Product update(Product product) throws Exception {

        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            session.update(product);

            tr.commit();
            return product;
        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();
            throw new Exception("Update method was filed", e);
        }
    }

    public Product delete(Product product) throws Exception {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            session.delete(product);

            tr.commit();
            return product;
        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();
            throw new Exception("Delete method was filed", e);
        }
    }

    public void saveAll(List<Product> products) throws Exception {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            for (Product product : products) {
                session.save(product);
            }

            tr.commit();
        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();
            throw new Exception("Save All method was filed", e);
        }
    }

    public void updateAll(List<Product> products) throws Exception {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            for (Product product : products) {
                session.update(product);
            }

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();
            throw new Exception("Update all method was filed", e);
        }
    }

    public void deleteAll(List<Product> products) throws Exception {

        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            for (Product product : products) {
                session.delete(product);
            }

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();
            throw new Exception("Update all method was filed", e);
        }
    }

    private SessionFactory createSessionFactory() {

        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
