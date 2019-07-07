package hibernate.lesson3.dao;

import hibernate.lesson3.model.Hotel;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HotelDAO {

    private SessionFactory sessionFactory;

    public Hotel save(Hotel hotel) throws Exception {

        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            session.save(hotel);

            tr.commit();
            return hotel;
        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();
            throw new Exception("Save hotel method was filed", e);
        }
    }

    public void delete(long id) throws Exception {

        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            Hotel hotel = session.load(Hotel.class, id);
            session.delete(hotel);

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();
            throw new Exception("Delete hotel method was filed for id: " + id, e);
        }
    }

    public Hotel update(Hotel hotel) throws Exception {

        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            session.update(hotel);

            tr.commit();
            return hotel;
        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();
            throw new Exception("Update hotel method was filed for id: " + hotel.getId(), e);
        }
    }

    public Hotel findById(long id) throws Exception {

        try (Session session = createSessionFactory().openSession()) {

            return session.get(Hotel.class, id);

        } catch (HibernateException e) {
            throw new Exception("Find hotel by id: " + id + " was filed", e);
        }
    }

    private SessionFactory createSessionFactory() {

        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
