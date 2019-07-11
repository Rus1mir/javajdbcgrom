package hibernate.lesson3.dao;
import hibernate.lesson3.model.Entity_;
import hibernate.lesson3.model.Room;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public abstract class GeneralDAO<T extends Entity_> {

    private SessionFactory sessionFactory;
    private final Class<T> type;

    protected GeneralDAO(Class<T> type) {
        this.type = type;
    }

    protected T findEntityById(long id) throws Exception {

        try (Session session = createSessionFactory().openSession()) {

            return session.get(type, id);

        } catch (HibernateException e) {

            throw new Exception("Operation getById with entity " + type.getName() +
                    " id: " + id + " was filed", e);
        }
    }

    protected T saveEntity(T entity) throws Exception {

        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {

            tr = session.beginTransaction();

            session.save(entity);

            tr.commit();

            return entity;

        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();

            throw new Exception("Operation save with entity " + type.getName() +
                    " was filed", e);
        }
    }

    protected T updateEntity(T entity) throws Exception {
        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {

            tr = session.beginTransaction();

            session.update(entity);

            tr.commit();

            return entity;

        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();

            throw new Exception("Operation update with entity " + type.getName() +
                    " id: " + entity.getId() + " was filed", e);
        }
    }

    protected void deleteEntity(long id) throws Exception {
        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {

            tr = session.beginTransaction();

            Entity_ entity = session.load(type, id);
            session.delete(entity);

            tr.commit();

        } catch (HibernateException e) {
            if (tr != null)
                tr.rollback();

            throw new Exception("Operation delete with entity " + type.getName() +
                    " id: " + id + " was filed", e);
        }
    }

    protected T testGet (long id) {

        Session session = createSessionFactory().openSession();
        Query<T> query = session.createQuery("SELECT r FROM Room r join fetch r.hotel where r.id = :Id ", type);
        query.setParameter("Id", id);

        T room = query.getSingleResult();
        session.close();
        return room;
    }

    private SessionFactory createSessionFactory() {

        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
