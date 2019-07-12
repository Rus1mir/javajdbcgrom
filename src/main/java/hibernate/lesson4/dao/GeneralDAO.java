package hibernate.lesson4.dao;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public abstract class GeneralDAO <T> {

    private final Class<T> type;
    private SessionFactory sessionFactory;

    public GeneralDAO(Class<T> type) {
        this.type = type;
    }

    protected T saveEntity(T entity) throws Exception {

        Transaction tr = null;

        try(Session session = getSessionFactory().openSession()) {

            tr = session.beginTransaction();

            session.save(entity);

            tr.commit();
            return entity;

        }catch(HibernateException e) {
            if(tr != null)
                tr.rollback();
            throw new Exception("Save failed");
        }
    }

    protected void deleteEntity(long id) {

    }

    protected T updateEntity(Object object) {
        return null;
    }

    protected T findEntityById(long id) throws Exception{
        try(Session session = getSessionFactory().openSession()) {

            return session.load(type, id);
        }catch (HibernateException e) {
            throw new Exception("Find operation filed");
        }
    }

    protected SessionFactory getSessionFactory () {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
