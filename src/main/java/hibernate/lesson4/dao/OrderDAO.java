package hibernate.lesson4.dao;

import hibernate.lesson4.model.Order;

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

    public Order findByRoomUser(long roomId, long userId) {

        String hql = "from Order o join fetch o.room r  where " +
                "r.id = :roomId and " +
                "h.id = :hotelId";
        //TODO
        return null;
    }
}
