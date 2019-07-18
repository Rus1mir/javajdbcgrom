package hibernate.lesson4.dao;

import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;

public class OrderDAO extends GeneralDAO<Order> {

    public OrderDAO() {
        super(Order.class);
    }

    public Order save(Order order) throws Exception {
        return saveEntity(order);
    }

    public Order update(Order order) throws Exception {
        return updateEntity(order);
    }

    public Order findById(long id) throws Exception {
        return findEntityById(id);
    }

    public void delete(long id) throws Exception {
        deleteEntity(id);
    }

    public List<Order> findByRoomUser(long roomId, long userId) throws Exception {

        String hql = "from Order o join fetch o.room r join fetch o.userOrdered u " +
                "where r.id = :roomId and " +
                "u.id = :userId " +
                "order by o.dateFrom desc";

        HashMap<String, Object> params = new HashMap<>(2);
        params.put("roomId", roomId);
        params.put("userId", userId);

        return getEntitiesByQuery(hql, params);
    }

    public void cancelOrder(Order order, Room room) throws Exception {

        Transaction tr = null;

        try (Session session = getSessionFactory().openSession()) {

            tr = session.beginTransaction();

            session.delete(order);
            session.update(room);

            tr.commit();
        } catch (HibernateException e) {
            if (tr != null)
                throw new Exception("Cancelling booking room id: " +
                        room.getId() + " order id: " + order.getId() + " was filed", e);
        }
    }

    public void processBooking(Order order, Room room) throws Exception {
        Transaction tr = null;

        try (Session session = getSessionFactory().openSession()) {

            tr = session.beginTransaction();

            session.save(order);
            session.update(room);

            tr.commit();
        } catch (HibernateException e) {
            if (tr != null)
                throw new Exception("Booking room id: " + room.getId() + " was filed", e);
        }
    }
}
