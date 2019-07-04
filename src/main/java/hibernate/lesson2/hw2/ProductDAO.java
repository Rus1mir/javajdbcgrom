package hibernate.lesson2.hw2;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO {

    private SessionFactory sessionFactory;

    public Product findById(Long id) throws Exception {
        //- поиск продукта по id

        try (Session session = createSessionFactory().openSession()) {

            return session.get(Product.class, id);

        } catch (HibernateException e) {
            throw new Exception("Method findByID was failed with product id: " + id, e);
        }
    }

    public List<Product> findByName(String name) throws Exception {
        //-поиск продуктов по имени

        try (Session session = createSessionFactory().openSession()) {

            Query<Product> query = session.createQuery("from Product where name = :paramName", Product.class);
            query.setParameter("paramName", name);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("Method findByName was failed with product name: " + name, e);
        }
    }

    public List<Product> findByContainedName(String name) throws Exception {
        //- поиск продуктов, которые в своем имени содержать слово name

        try (Session session = createSessionFactory().openSession()) {

            Query<Product> query = session.createQuery("from Product where name like :paramName", Product.class);
            query.setParameter("paramName", "%" + name + "%");

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("Method findByContainedName was failed with contained product name: " + name, e);
        }
    }

    public List<Product> findByPrice(int price, int delta) throws Exception {
        // - поиск продуктов по вилке цен price+-delta включительно
        try (Session session = createSessionFactory().openSession()) {

            Query<Product> query = session.createQuery("from Product where price " +
                    "between :paramPriceFrom and :paramPriceTo", Product.class);
            query.setParameter("paramPriceFrom", price - delta);
            query.setParameter("paramPriceTo", price + delta);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("Method findByPrice was failed", e);
        }
    }

    public List<Product> findByNameSortedAsc(String name) throws Exception {
        //- поиск продуктов по имени, результат отсортирован по алфавитному порядку колонки name

        try (Session session = createSessionFactory().openSession()) {

            Query<Product> query = session.createQuery("from Product where name = :paramName order by name asc "
                    , Product.class);
            query.setParameter("paramName", name);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("Method findByNameSortedAsc was failed with product name: " + name, e);
        }
    }

    public List<Product> findByNameSortedDesc(String name) throws Exception {
        //- поиск продуктов по имени, результат отсортирован начиная с конца алфавита колонки name

        try (Session session = createSessionFactory().openSession()) {

            Query<Product> query = session.createQuery("from Product where name = :paramName order by name desc "
                    , Product.class);
            query.setParameter("paramName", name);

            return query.list();

        } catch (HibernateException e) {
            throw new Exception("Method findByNameSortedDesc was failed with product name: " + name, e);
        }
    }

    public List<Product> findByPriceSortedDesc(int price, int delta) throws Exception {
        //- поиск продуктов по вилке цен price+-delta включительно, результат отсортирован по убыванию цен

        try (Session session = createSessionFactory().openSession()) {

            Query<Product> query = session.createQuery("from Product where price " +
                    "between :paramPriceFrom and :paramPriceTo order by price desc", Product.class);
            query.setParameter("paramPriceFrom", price - delta);
            query.setParameter("paramPriceTo", price + delta);

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
