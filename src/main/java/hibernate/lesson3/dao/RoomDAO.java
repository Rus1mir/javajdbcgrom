package hibernate.lesson3.dao;

import hibernate.lesson3.model.Room;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class RoomDAO {
    
    private SessionFactory sessionFactory;

    public Room save(Room room) throws Exception {

        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            session.save(room);

            tr.commit();
            return room;
        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();
            throw new Exception("Save room method was filed", e);
        }
    }

    public void delete(long id) throws Exception {

        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            Room room = session.load(Room.class, id);
            session.delete(room);

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();
            throw new Exception("Delete room method was filed for id: " + id, e);
        }
    }

    public Room update(Room room) throws Exception {

        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            session.update(room);

            tr.commit();
            return room;
        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();
            throw new Exception("Update room method was filed for id: " + room.getId(), e);
        }
    }

    public Room findById(long id) throws Exception {

        try (Session session = createSessionFactory().openSession()) {

            Room room = session.get(Room.class, id);
            Hibernate.initialize(room.getHotel());
            return room;

        } catch (HibernateException e) {
            throw new Exception("Find room id: " + id + " was filed", e);
        }
    }

    private SessionFactory createSessionFactory() {

        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
