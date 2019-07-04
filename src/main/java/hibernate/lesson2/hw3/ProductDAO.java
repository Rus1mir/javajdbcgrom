package hibernate.lesson2.hw3;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO {

    private SessionFactory sessionFactory;

    public hibernate.lesson2.hw3.Product findById(Long id) throws Exception {
        //- поиск продукта по id

        try (Session session = createSessionFactory().openSession()) {

            Query<Product> query = session.createNativeQuery("SELECT * FROM PRODUCT WHERE ID = ?", Product.class);
            query.setParameter(1, id);

            return query.getSingleResult();

        } catch (HibernateException e) {
            throw new Exception("Method findByID was failed with product id: " + id, e);
        }
    }

    public List<Product> findByName(String name) throws Exception {
        //-поиск продуктов по имени

        try (Session session = createSessionFactory().openSession()) {

            Query<Product> query = session.createNativeQuery("SELECT * FROM PRODUCT WHERE NAME LIKE ?", Product.class);
            query.setParameter(1, name);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("Method findByName was failed with product name: " + name, e);
        }
    }

    public List<Product> findByContainedName(String name) throws Exception {
        //- поиск продуктов, которые в своем имени содержать слово name

        try (Session session = createSessionFactory().openSession()) {

            Query<Product> query = session.createNativeQuery("SELECT * FROM PRODUCT WHERE NAME LIKE ?", Product.class);
            query.setParameter(1, "%" + name + "%");

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("Method findByContainedName was failed with contained product name: " + name, e);
        }
    }

    public List<Product> findByPrice(int price, int delta) throws Exception {
        // - поиск продуктов по вилке цен price+-delta включительно
        try (Session session = createSessionFactory().openSession()) {

            Query<hibernate.lesson2.hw3.Product> query = session.createNativeQuery("SELECT * FROM PRODUCT " +
                    "WHERE PRICE BETWEEN ? AND ?", Product.class);
            query.setParameter(1, price - delta);
            query.setParameter(2, price + delta);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("Method findByPrice was failed", e);
        }
    }

    public List<Product> findByNameSortedAsc(String name) throws Exception {
        //- поиск продуктов по имени, результат отсортирован по алфавитному порядку колонки name

        try (Session session = createSessionFactory().openSession()) {

            Query<hibernate.lesson2.hw3.Product> query = session.createNativeQuery("SELECT * FROM PRODUCT WHERE NAME LIKE ? " +
                    "ORDER BY NAME ASC", Product.class);
            query.setParameter(1, name);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("Method findByNameSortedAsc was failed with product name: " + name, e);
        }
    }

    public List<Product> findByNameSortedDesc(String name) throws Exception {
        //- поиск продуктов по имени, результат отсортирован начиная с конца алфавита колонки name

        try (Session session = createSessionFactory().openSession()) {

            Query<hibernate.lesson2.hw3.Product> query = session.createNativeQuery("SELECT * FROM PRODUCT WHERE NAME LIKE ? " +
                    "ORDER BY NAME DESC", Product.class);
            query.setParameter(1, name);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("Method findByNameSortedDesc was failed with product name: " + name, e);
        }
    }

    public List<Product> findByPriceSortedDesc(int price, int delta) throws Exception {
        //- поиск продуктов по вилке цен price+-delta включительно, результат отсортирован по убыванию цен

        try (Session session = createSessionFactory().openSession()) {

            Query<hibernate.lesson2.hw3.Product> query = session.createNativeQuery("SELECT * FROM PRODUCT " +
                    "WHERE PRICE BETWEEN ? AND ? ORDER BY PRICE DESC", Product.class);

            query.setParameter(1, price - delta);
            query.setParameter(2, price + delta);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("Method findByPriceDesc was failed", e);
        }
    }

    private SessionFactory createSessionFactory() {

        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
