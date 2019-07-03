package hibernate.lesson1;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductRepository {

    public Product findById(int id) {

        return new HibernateUtils().createSessionFactory().openSession().get(Product.class, "id");
    }

    public void save(Product product) {

        Session session = new HibernateUtils().createSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        session.close();
    }

    public void update(Product product)
    {
        Session session = new HibernateUtils().createSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(product);
        transaction.commit();
        session.close();
    }

    public void delete(Long id) {

        Session session = new HibernateUtils().createSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Product product =  session.load(Product.class, id);
        session.delete(product);
        transaction.commit();
        session.close();
    }

}
