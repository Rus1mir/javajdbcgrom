package hibernate.lesson4.dao;

import hibernate.lesson4.model.Room;

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
}
