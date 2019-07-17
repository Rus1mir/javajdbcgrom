package hibernate.lesson4.dao;

import hibernate.lesson4.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Map;


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

        /*
        Old realisation

        String hql = "from Room r join fetch r.hotel as h where " +
                "(r.numberOfGuests = :numberOfGuests or :numberOfGuests = null) and " +
                "(r.price <= :price or :price = null) and " +
                "(r.breakfastIncluded = :breakfastIncluded or :breakfastIncluded = null) and " +
                "(r.petsAllowed = :petsAllowed or :petsAllowed = null) and " +
                "(trunc(r.dateAvailableFrom) <= trunc(:dateAvailableFrom) or :dateAvailableFrom = null) and" +
                "(h.country like :country or :country = null) and " +
                "(h.city like :city or :city = null)";

        return getEntitiesByQuery(hql, filter.getParamMap());
        */

        //With Criteria API
        List<Room> rooms;

        try (Session session = getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Room> criteriaQuery = builder.createQuery(Room.class);

            Root<Room> roomRoot = criteriaQuery.from(Room.class);
            Join<Hotel, Room> hotelRoomJoin = roomRoot.join("hotel");

            Map<String, Object> params = filter.getParamMap();
            Predicate predicate = builder.conjunction();

            for (String pKey : params.keySet()) {
                if (params.get(pKey) == null) continue;

                if ("country".equals(pKey) || "city".equals(pKey)) {

                    predicate = builder.and(predicate, builder.equal(hotelRoomJoin.get(pKey), params.get(pKey)));
                } else {

                    predicate = builder.and(predicate, builder.equal(roomRoot.get(pKey), params.get(pKey)));
                }
            }

            criteriaQuery.select(roomRoot).where(predicate);

            rooms = session.createQuery(criteriaQuery).getResultList();

        } catch (HibernateException e) {

            throw new Exception("Filter query was filed", e);
        }
        return rooms;
    }
}
