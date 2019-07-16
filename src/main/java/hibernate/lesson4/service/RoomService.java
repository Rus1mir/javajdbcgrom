package hibernate.lesson4.service;

import hibernate.lesson4.dao.Filter;
import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.model.Room;

import java.util.List;

public class RoomService {

    RoomDAO roomDAO = new RoomDAO();

    //CRUD
    public Room save(Room room) throws Exception {

        return roomDAO.save(room);
    }

    public Room update(Room room) throws Exception {

        return roomDAO.update(room);
    }

    public Room findById(long id) throws Exception {

        return roomDAO.findById(id);
    }

    public void delete(long id) throws Exception {

        roomDAO.delete(id);
    }

    //Other
    public List<Room> findRooms(Filter filter) throws Exception {

        return roomDAO.findRooms(filter);
    }
}
