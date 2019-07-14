package hibernate.lesson4.dao;

import hibernate.lesson4.model.Identifiable;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;


public abstract class GeneralDAO <T extends Identifiable> {

    private final Class<T> type;
    private SessionFactory sessionFactory;

    public GeneralDAO(Class<T> type) {
        this.type = type;
    }

    protected T saveEntity(T entity) throws Exception {

        Transaction tr = null;

        try(Session session = getSessionFactory().openSession()) {

            tr = session.beginTransaction();

            session.persist(entity);

            tr.commit();
            return entity;

        }catch(HibernateException e) {
            if(tr != null)
                tr.rollback();
            throw new Exception("Save failed for " + type.getName() + " with id: " + entity.getId(), e);
        }
    }

    protected void deleteEntity(long id) throws Exception {

        Transaction tr = null;

        try(Session session = getSessionFactory().openSession()) {

            tr = session.beginTransaction();

            session.delete(session.load(type, id));

            tr.commit();

        }catch(HibernateException e) {
            if(tr != null)
                tr.rollback();
            throw new Exception("Delete failed for " + type.getName() + " with id: " + id, e);
        }
    }

    protected T updateEntity(T entity) throws Exception {

        Transaction tr = null;

        try(Session session = getSessionFactory().openSession()) {

            tr = session.beginTransaction();

            session.merge(entity);

            tr.commit();
            return entity;

        }catch(HibernateException e) {
            if(tr != null)
                tr.rollback();
            throw new Exception("Update failed for " + type.getName() + " with id: " + entity.getId(), e);
        }
    }

    protected T findEntityById(long id) throws Exception{

        try(Session session = getSessionFactory().openSession()) {

            return session.get(type, id);
        }catch (HibernateException e) {
            throw new Exception("Find failed for " + type.getName() + " with id: " + id, e);
        }
    }

    protected SessionFactory getSessionFactory () {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
