package hibernate.lesson4.dao;

import hibernate.lesson4.model.Room;

import java.util.ArrayList;
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

    public Room getRoomByFilter(Filter filter) {



        String sql = "SELECT * FROM ROOMS JOIN FETCH HOTELS WHERE " +
        "ROOMS.PRICE < ? " +
        "ROOMS."
        List<Object> params = new ArrayList<>();

        params.add(filter.getPrice());
        params.add(filter.getBreakfastIncluded());
        params.add(filter.getCity());
        params.add(filter.getCountry());
        params.add(filter.getDateAvailableFrom());
        params.add(filter.getNumberOfGuests());
        params.add(filter.getPetsAllowed());
    }
}
