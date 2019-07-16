package hibernate.lesson4.dao;

import hibernate.lesson4.model.Room;

import java.util.List;


public class RoomDAO extends GeneralDAO<Room> {

    public RoomDAO() {
        super(Room.class);
    }

    public Room save(Room room) throws Exception {

        return saveEntity(room);
    }

    public Room update(Room room) throws Exception {

        return updateEntity(room);
    }

    public Room findById(long id) throws Exception {

        return findEntityById(id);
    }

    public void delete(long id) throws Exception {

        deleteEntity(id);
    }

    public List<Room> findRooms(Filter filter) throws Exception {

        String hql = "from Room r join fetch r.hotel as h where " +
                "(r.numberOfGuests = :numberOfGuests or :numberOfGuests = null) and " +
                "(r.price <= :price or :price = null) and " +
                "(r.breakfastIncluded = :breakfastIncluded or :breakfastIncluded = null) and " +
                "(r.petsAllowed = :petsAllowed or :petsAllowed = null) and " +
                "(trunc(r.dateAvailableFrom) <= trunc(:dateAvailableFrom) or :dateAvailableFrom = null) and" +
                "(h.country like :country or :country = null) and " +
                "(h.city like :city or :city = null)";

        return getEntitiesByQuery(hql, filter.getParamMap());
    }
}
